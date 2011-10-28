/**
 * 
 */
package org.fubme.persistency.mappings;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.fubme.models.Comment;
import org.fubme.models.User;
import org.fubme.persistency.DBConnection;

/**
 * @author riccardo
 * 
 */
public abstract class CommentMapper {
	public static final void createCommentToPost(Comment comment) {
		Connection connection = null;
		Statement stmt = null;
		String sql = "INSERT INTO luser_comments_post (" + Comment.LUSER_ID
				+ "," + Comment.POST_ID + "," + Comment.BODY + ") VALUES('"
				+ comment.getLuser_id() + "'," + comment.getPost_id() + ",'"
				+ comment.getBody() + "')";
		System.out.println(sql);
		try {
			connection = DBConnection.getConnection();
			stmt = connection.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException ex) {
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			if (stmt != null)
				try {
					stmt.close();
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
	}
}
