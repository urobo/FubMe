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
import org.fubme.models.Post;
import org.fubme.models.User;

/**
 * @author riccardo
 * 
 */
public abstract class TimelineManager {
	public static final List<Post> getTimelineForUser(User user, int limit) {
		List<Post> result = new ArrayList<Post>();
		Connection connection = null;
		Statement stmt = null;
		ResultSet resultset = null;
		String sql = "SELECT * from post where luser_id in (select luser_id_followed from luser_follows_luser where luser_id_follower = '"
				+ user.getId()
				+ "') or luser_id = '"
				+ user.getId()
				+ "' order by id desc limit " + limit;

		try {
			connection = DBConnection.getConnection();
			stmt = connection.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
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
				for (int i = 0; i < post.getTags().size(); i++) {
					System.out.println(post.getId() + "\t"
							+ post.getTags().get(i).getName());
				}
				result.add(post);
			}
		} catch (SQLException ex) {
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			if (resultset != null)
				try {
					resultset.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	public static final List<Post> getProfileForUser(User user, int limit) {
		List<Post> result = new ArrayList<Post>();
		Connection connection = null;
		Statement stmt = null;
		ResultSet resultset = null;
		String sql = "SELECT * from post where luser_id = '" + user.getId()
				+ "' order by id desc limit " + limit;
		try {
			connection = DBConnection.getConnection();
			stmt = connection.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
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
				for (int i = 0; i < post.getTags().size(); i++) {
					System.out.println(post.getId() + "\t"
							+ post.getTags().get(i).getName());
				}
				result.add(post);
			}
		} catch (SQLException ex) {
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			if (resultset != null)
				try {
					resultset.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
}
