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
public abstract class Tagger {
	public static final void tagAs(Post post) {
		Connection connection = null;
		Statement stmt = null;

		String sql = "INSERT INTO post_tagged_as (post_id,tag_Name) VALUES ";
		for (int i = 0; i < post.getTags().size(); i++) {
			sql += "(" + post.getId() + ",'" + post.getTags().get(i).getName()
					+ "')";
			if (i != post.getTags().size() - 1)
				sql += ", ";
		}
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
