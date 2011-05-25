/**
 * 
 */
package org.fubme.persistency.mappings;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.fubme.models.Post;
import org.fubme.models.User;
import org.fubme.persistency.DBConnection;

/**
 * @author riccardo
 * 
 */
public abstract class PostMapper {

	public static final void createPost(Post post) {
		Connection connection = DBConnection.getConnection();
		Statement stmt = null;
		String sql = "INSERT INTO post ("
				+ Post.LUSER_ID
				+ ","
				+ Post.LINK
				+ " ,"
				+ Post.BODY
				+ ","
				+ Post.MIME
				+ ") VALUES ('"
				+ post.getUser_id()
				+ "','"
				+ ((post.getLink() != null) ? post.getLink().toString()
						: "null") + "','" + post.getBody() + "','"
				+ post.getMime() + "')";
		try {
			stmt = connection.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException ex) {
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			if (stmt != null)
				stmt = null;
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {

				}
				connection = null;
			}
		}
	}

	public static final void likes(User user, Post post) {
		Connection connection = DBConnection.getConnection();
		Statement stmt = null;
		String sql = "INSERT INTO luser_likes_post (luser_id , post_id) VALUES ('"
				+ user.getId() + "'," + post.getId() + ")";
		try {
			stmt = connection.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException ex) {
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			if (stmt != null)
				stmt = null;
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {

				}
				connection = null;
			}
		}
	}
}
