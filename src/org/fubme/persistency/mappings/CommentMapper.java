/**
 * 
 */
package org.fubme.persistency.mappings;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.fubme.models.Comment;
import org.fubme.persistency.DBConnection;

/**
 * @author riccardo
 * 
 */
public abstract class CommentMapper {
	public static final void createCommentToPost(Comment comment) {
		Connection connection = null;
		PreparedStatement stmt = null;
		String sql = "INSERT INTO luser_comments_post (" + Comment.LUSER_ID
				+ "," + Comment.POST_ID + "," + Comment.BODY
				+ ") VALUES(? , ? , ?)";

		try {
			connection = DBConnection.getConnection();
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, comment.getLuser_id());
			stmt.setInt(2, comment.getPost_id());
			stmt.setString(3, comment.getBody());
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException ex) {
			Logger.getLogger(CommentMapper.class.getName()).log(Level.SEVERE,
					null, ex);
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
