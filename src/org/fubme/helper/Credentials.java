/**
 * 
 */
package org.fubme.helper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.fubme.models.User;
import org.fubme.persistency.DBConnection;

/**
 * @author riccardo
 * 
 */
public abstract class Credentials {
	public static final User validateUserCredentials(String id, String pswd) {
		Connection connection = DBConnection.getConnection();
		Statement stmt = null;
		String sql = "Select * from fuser where id = '" + id + "'";
		ResultSet resultset = null;
		try {
			stmt = connection.createStatement();
			resultset = stmt.executeQuery(sql);
			resultset.next();
			String password = resultset.getString("pswd");
			if (pswd.equals(password))
				return new User(id, pswd);
		} catch (SQLException ex) {

			Logger.getLogger(Credentials.class.getName()).log(Level.SEVERE,
					null, ex);
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
		return null;
	}
}
