/**
 * 
 */
package org.fubme.persistency.mappings;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Timestamp;

import org.fubme.models.User;
import org.fubme.persistency.DBConnection;

/**
 * @author riccardo
 *
 */
public abstract class UserMapper {
	
	private static final String getUserAttributesSqlString(User user){
		String attributes = "id";
		String values = "'" + user.getId() +"'";
		
		if (user.getBio() instanceof String){
			attributes += ",bio";
			values += ",'"+user.getBio()+"'";
		}
		
		if (user.getBirthdate() instanceof Timestamp){
			attributes += ",birthdate";
			values +=","+ user.getBirthdate();
		}
		
		if (user.getFirstname() instanceof String){
			attributes += ",firstname";
			values += ",'"+user.getFirstname()+"'";
		}
		
		if (user.getLastname() instanceof String){
			attributes += ",lastname";
			values += ",'"+user.getLastname()+"'";
		}
		
		String sql = "INSERT INTO luser ("+attributes+") VALUES (" + values +")";
		return sql;
	}
	
	public static final void createUser(User user){
		Connection connection = DBConnection.getConnection();
		Statement stmt = null;
		Statement stmt1 = null;
		String sql = "INSERT INTO fuser (id,pswd,email) VALUES ('"+user.getId()+"','"+user.getPswd()+"','"+user.getEmail()+"');";
		
		String sql1 = getUserAttributesSqlString(user);
		try {
			stmt = connection.createStatement();
			stmt.executeUpdate(sql);
			
			stmt1 = connection.createStatement();
			stmt1.executeUpdate(sql1);
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
