/**
 * 
 */
package org.fubme.persistency;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.fubme.factories.PostFactory;
import org.fubme.models.Comment;
import org.fubme.models.Post;
import org.fubme.models.Tag;
import org.fubme.models.User;

/**
 * @author riccardo
 * 
 */

public abstract class Helper {

	/**
	 * Retrieves all comments given a post filtering out all comments from those
	 * users blocked by the user passed as parameter
	 * 
	 * @param post
	 * @param user
	 * @return a list of comments
	 */
	public static final List<Comment> getComments(Post post, User user) {
		List<Comment> comments = new ArrayList<Comment>();
		Connection connection = DBConnection.getConnection();
		Statement stmt = null;
		ResultSet resultset = null;
		String sql = "SELECT lcp.id,lcp.body,lcp.luser_id,lcp.time,lcp.post_id from luser_comments_post as lcp join post as p on p.id = lcp.post_id where p.id = "
				+ post.getId()
				+ " and lcp.luser_id not in (select wrongdoing_id from luser_reports_luser where whistleblower_id = '"
				+ user.getId() + "')";
		try {
			stmt = connection.createStatement();
			resultset = stmt.executeQuery(sql);
			while (resultset.next()) {
				Comment comment = new Comment(
						resultset.getInt(Comment.POST_ID),
						resultset.getString(Comment.LUSER_ID),
						resultset.getTimestamp(Comment.TIME),
						resultset.getString(Comment.BODY));
				comments.add(comment);
			}
			stmt.close();
			resultset.close();
		} catch (SQLException ex) {
			comments = null;
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			if (stmt != null)
				stmt = null;
			if (resultset != null)
				resultset = null;
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {

				}
				connection = null;
			}
		}
		return comments;
	}

	public static final List<Tag> getTags(Post post) {
		List<Tag> tags = new ArrayList<Tag>();
		Connection connection = DBConnection.getConnection();
		Statement stmt = null;
		ResultSet resultset = null;
		String sql = "SELECT tag_name from post_tagged_as where post_id = "
				+ post.getId();
		try {
			stmt = connection.createStatement();
			resultset = stmt.executeQuery(sql);
			while (resultset.next()) {
				tags.add(new Tag(resultset.getString(Tag.TAG_NAME)));
			}
			stmt.close();
			resultset.close();
		} catch (SQLException ex) {
			tags = null;
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			if (stmt != null)
				stmt = null;
			if (resultset != null)
				resultset = null;
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {

				}
				connection = null;
			}
		}
		return tags;
	}

	public static final List<Post> getPostsFromUser(User user, int limit) {
		List<Post> posts = new ArrayList<Post>();
		Connection connection = DBConnection.getConnection();
		Statement stmt = null;
		ResultSet resultset = null;
		String sql = "SELECT * FROM post WHERE " + Post.LUSER_ID + " = '"
				+ user.getId() + "' ORDER BY ptime limit " + limit;
		try {
			stmt = connection.createStatement();
			resultset = stmt.executeQuery(sql);
			while (resultset.next()) {
				Post post = PostFactory.getPost(
						resultset.getTimestamp(Post.PTIME),
						resultset.getInt(Post.ID),
						resultset.getString(Post.LUSER_ID),
						resultset.getString(Post.BODY),
						resultset.getString(Post.LINK),
						resultset.getString(Post.MIME));
				post.setComments(Helper.getComments(post, user));
				post.setTags(Helper.getTags(post));
				posts.add(post);
			}
			stmt.close();
			resultset.close();
		} catch (SQLException ex) {
			posts = null;
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			if (stmt != null)
				stmt = null;
			if (resultset != null)
				resultset = null;
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {

				}
				connection = null;
			}
		}
		return posts;
	}
}
