/**
 * 
 */
package org.fubme.persistency;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public abstract class TrackTag {
	public static final List<Post> searchPostTaggedAs(User user,
			List<String> tags, int limit) {
		List<Post> posts = new ArrayList<Post>();
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet resultset = null;
		String sql = "";

		for (int i = 0; i < tags.size(); i++) {
			if (!sql.isEmpty())
				sql += " union";
			sql += "SELECT * from post where id in (select post_id from post_tagged_as where tag_name = ?)";
		}
		sql += "limit " + limit;

		try {
			connection = DBConnection.getConnection();
			stmt = connection.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			for (int i = 0; i < tags.size(); i++)
				stmt.setString(i + 1, tags.get(i));
			resultset = stmt.executeQuery();
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
					System.out.println("TrackTag\t" + post.getId() + "\t"
							+ post.getTags().get(i).getName());
				}
				posts.add(post);
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

		return posts;
	}
}
