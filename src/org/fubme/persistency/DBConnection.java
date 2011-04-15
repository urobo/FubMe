/**
 * 
 */
package org.fubme.persistency;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author riccardo
 * 
 */
public abstract class DBConnection {
	private static Connection connection = null;

	public static void connect() {
		try {
			if (connection == null) {
				String host = "localhost";
				String database = "fubme";
				String username = "fubme";
				String password = "fubme";
				String url = "jdbc:postgresql://" + host + "/" + database;
				String driverJDBC = "org.postgresql.Driver";
				Class.forName(driverJDBC);
				connection = DriverManager.getConnection(url, username,
						password);

			} else if (connection.isClosed()) {
				connection = null;
				connect();
			}
		} catch (SQLException e) {
			e.printStackTrace(System.err);
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}

	public static void disconnect() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				Logger.getLogger(DBConnection.class.getName()).log(
						Level.SEVERE, null, e);
			}
		}
	}

	public static Connection getConnection() {

		try {
			if (connection != null && !connection.isClosed()) {
				return connection;
			} else {
				connect();
				return connection;
			}
		} catch (SQLException e) {
			Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE,
					null, e);
			return null;
		}
	}

	@Override
	public void finalize() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				Logger.getLogger(DBConnection.class.getName()).log(
						Level.SEVERE, null, e);
			}
		}
	}

}
