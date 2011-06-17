/**
 * 
 */
package org.fubme.persistency.mappings;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.fubme.models.User;
import org.fubme.models.UserList;
import org.fubme.persistency.DBConnection;

/**
 * @author riccardo
 * 
 */
public abstract class UserMapper {

	private static final String getUserAttributesSqlString(User user) {
		String attributes = "id";
		String values = "'" + user.getId() + "'";

		if (user.getBio() instanceof String) {
			attributes += ",bio";
			values += ",'" + user.getBio() + "'";
		}

		if (user.getBirthdate() instanceof Timestamp) {
			attributes += ",birthdate";
			values += "," + user.getBirthdate();
		}

		if (user.getFirstname() instanceof String) {
			attributes += ",firstname";
			values += ",'" + user.getFirstname() + "'";
		}

		if (user.getLastname() instanceof String) {
			attributes += ",lastname";
			values += ",'" + user.getLastname() + "'";
		}

		String sql = "INSERT INTO luser (" + attributes + ") VALUES (" + values
				+ ")";
		return sql;
	}

	public static final void createUser(User user) {
		Connection connection = DBConnection.getConnection();
		Statement stmt = null;
		Statement stmt1 = null;
		String sql = "INSERT INTO fuser (id,pswd,email) VALUES ('"
				+ user.getId() + "','" + user.getPswd() + "','"
				+ user.getEmail() + "');";

		String sql1 = getUserAttributesSqlString(user);
		try {
			stmt = connection.createStatement();
			stmt.executeUpdate(sql);

			stmt1 = connection.createStatement();
			stmt1.executeUpdate(sql1);
		} catch (SQLException ex) {
			Logger.getLogger(UserMapper.class.getName()).log(Level.SEVERE,
					null, ex);
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

	public static final boolean checkUserData(String attribute, String value) {
		boolean exist = true;
		Connection connection = DBConnection.getConnection();
		Statement stmt = null;
		String sql = "SELECT " + attribute + " FROM fuser where " + attribute
				+ "='" + value + "'";
		try {
			stmt = connection.createStatement();
			ResultSet result = stmt.executeQuery(sql);
			System.out.println(attribute + "\t" + value);
			if (result.next())
				return true;
			return false;
		} catch (SQLException ex) {
			Logger.getLogger(UserMapper.class.getName()).log(Level.SEVERE,
					null, ex);
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
		return exist;
	}

	public static final void follows(User follower, User toBeFollowed) {
		Connection connection = DBConnection.getConnection();
		Statement stmt = null;
		String sql = "INSERT INTO luser_follows_luser (luser_id_follower,luser_id_followed) VALUES ('"
				+ follower.getId() + "','" + toBeFollowed.getId() + "')";
		try {
			stmt = connection.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException ex) {
			Logger.getLogger(UserMapper.class.getName()).log(Level.SEVERE,
					null, ex);
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

	public static final void unfollows(User follower, User toBeUnfollowed) {
		Connection connection = DBConnection.getConnection();
		Statement stmt = null;
		String sql = "DELETE FROM luser_follows_luser where luser_id_follower = '"
				+ follower.getId()
				+ "' and luser_id_followed = '"
				+ toBeUnfollowed.getId() + "')";
		try {
			stmt = connection.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException ex) {
			Logger.getLogger(UserMapper.class.getName()).log(Level.SEVERE,
					null, ex);
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

	public static final void addAUserToUserList(User listOwner, UserList list,
			User toBeAdded) {
		Connection connection = DBConnection.getConnection();
		Statement stmt = null;
		String sql = "SELECT * from luser_lists_luser where id = '"
				+ list.getId() + "' and luser_id_listed = '"
				+ toBeAdded.getId() + "'";

		try {
			stmt = connection.createStatement();
			ResultSet rList = stmt.executeQuery(sql);
			if (!rList.next()) {
				sql = "INSERT INTO luser_lists_luser (id, luser_id_list_owner,luser_id_listed) VALUES ('"
						+ list.getId()
						+ "','"
						+ listOwner.getId()
						+ "','"
						+ toBeAdded.getId() + "')";
				stmt.executeUpdate(sql);
			}
		} catch (SQLException ex) {
			Logger.getLogger(UserMapper.class.getName()).log(Level.SEVERE,
					null, ex);
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

	public static final List<User> getFollowers(User user) {
		List<User> followers = new ArrayList<User>();
		Connection connection = DBConnection.getConnection();
		Statement stmt = null;
		String sql = "select * from luser where id in (select luser_id_follower from luser_follows_luser where luser_id_followed = '"
				+ user.getId() + "'";
		try {
			stmt = connection.createStatement();
			ResultSet result = stmt.executeQuery(sql);
			while (result.next()) {
				followers.add(new User(result.getString("id"), null));
			}
			return followers;
		} catch (SQLException ex) {
			Logger.getLogger(UserMapper.class.getName()).log(Level.SEVERE,
					null, ex);
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
		return null;

	}

	public static final List<User> getFollowing(User user) {
		List<User> following = new ArrayList<User>();
		Connection connection = DBConnection.getConnection();
		Statement stmt = null;
		String sql = "select * from luser where id in (select luser_id_followed from luser_follows_luser where luser_id_follower = '"
				+ user.getId() + "'";
		try {
			stmt = connection.createStatement();
			ResultSet result = stmt.executeQuery(sql);
			while (result.next()) {
				following.add(new User(result.getString("id"), null));
			}
			return following;
		} catch (SQLException ex) {
			Logger.getLogger(UserMapper.class.getName()).log(Level.SEVERE,
					null, ex);
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
		return null;

	}

	public static final User getUserInfo(User user) {
		Connection connection = DBConnection.getConnection();
		Statement stmt = null;
		String sql = "select * from luser where id = '" + user.getId() + "'";

		try {
			stmt = connection.createStatement();
			ResultSet result = stmt.executeQuery(sql);
			while (result.next()) {
				return new User(result.getString("id"), null, null,
						result.getString("bio"), result.getString("firstname"),
						result.getString("lastname"),
						result.getTimestamp("birthdate"),
						result.getString("location"));
			}
		} catch (SQLException ex) {
			Logger.getLogger(UserMapper.class.getName()).log(Level.SEVERE,
					null, ex);
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
		return null;
	}
}
