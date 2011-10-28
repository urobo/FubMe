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
		Connection connection = null;
		Statement stmt = null;
		Statement stmt1 = null;
		String sql = "INSERT INTO fuser (id,pswd,email) VALUES ('"
				+ user.getId() + "','" + user.getPswd() + "','"
				+ user.getEmail() + "');";

		String sql1 = getUserAttributesSqlString(user);
		try {
			connection = DBConnection.getConnection();
			stmt = connection.createStatement();
			stmt.executeUpdate(sql);

			stmt1 = connection.createStatement();
			stmt1.executeUpdate(sql1);
		} catch (SQLException ex) {
			Logger.getLogger(UserMapper.class.getName()).log(Level.SEVERE,
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

	public static final boolean checkUserData(String attribute, String value) {
		boolean exist = true;
		Connection connection = null;
		Statement stmt = null;
		ResultSet resultset = null;
		String sql = "SELECT " + attribute + " FROM fuser where " + attribute
				+ "='" + value + "'";
		try {
			connection = DBConnection.getConnection();
			stmt = connection.createStatement();
			resultset = stmt.executeQuery(sql);
			System.out.println(attribute + "\t" + value);
			if (resultset.next())
				return true;
			return false;
		} catch (SQLException ex) {
			Logger.getLogger(UserMapper.class.getName()).log(Level.SEVERE,
					null, ex);
		} finally {
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			if (resultset != null) {
				try {
					resultset.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return exist;
	}

	public static final void follows(User follower, User toBeFollowed) {
		Connection connection = null;
		Statement stmt = null;
		String sql = "INSERT INTO luser_follows_luser (luser_id_follower,luser_id_followed) VALUES ('"
				+ follower.getId() + "','" + toBeFollowed.getId() + "')";
		try {
			connection = DBConnection.getConnection();
			stmt = connection.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException ex) {
			Logger.getLogger(UserMapper.class.getName()).log(Level.SEVERE,
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

	public static final void unfollows(User follower, User toBeUnfollowed) {
		Connection connection = null;
		Statement stmt = null;
		String sql = "DELETE FROM luser_follows_luser where luser_id_follower = '"
				+ follower.getId()
				+ "' and luser_id_followed = '"
				+ toBeUnfollowed.getId() + "')";
		try {
			connection = DBConnection.getConnection();
			stmt = connection.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException ex) {
			Logger.getLogger(UserMapper.class.getName()).log(Level.SEVERE,
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

	public static final void addAUserToUserList(User listOwner, UserList list,
			User toBeAdded) {
		Connection connection = null;
		Statement stmt = null;
		String sql = "SELECT * from luser_lists_luser where id = '"
				+ list.getId() + "' and luser_id_listed = '"
				+ toBeAdded.getId() + "'";

		try {
			connection = DBConnection.getConnection();
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

	public static final List<User> getFollowers(User user) {
		List<User> followers = new ArrayList<User>();
		Connection connection = null;
		Statement stmt = null;
		ResultSet resultset = null;
		String sql = "select * from luser where id in (select luser_id_follower from luser_follows_luser where luser_id_followed = '"
				+ user.getId() + "'";
		try {
			connection = DBConnection.getConnection();
			stmt = connection.createStatement();
			resultset = stmt.executeQuery(sql);
			while (resultset.next()) {
				followers.add(new User(resultset.getString("id"), null));
			}
			return followers;
		} catch (SQLException ex) {
			Logger.getLogger(UserMapper.class.getName()).log(Level.SEVERE,
					ex.getMessage(), ex);
		} finally {
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			if (resultset != null) {
				try {
					resultset.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return null;

	}

	public static final List<User> getFollowing(User user) {
		List<User> following = new ArrayList<User>();
		Connection connection = null;
		Statement stmt = null;
		ResultSet resultset = null;
		String sql = "select * from luser where id in (select luser_id_followed from luser_follows_luser where luser_id_follower = '"
				+ user.getId() + "'";
		try {
			connection = DBConnection.getConnection();
			stmt = connection.createStatement();
			resultset = stmt.executeQuery(sql);
			while (resultset.next()) {
				following.add(new User(resultset.getString("id"), null));
			}
			return following;
		} catch (SQLException ex) {
			Logger.getLogger(UserMapper.class.getName()).log(Level.SEVERE,
					null, ex);
		} finally {
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			if (resultset != null) {
				try {
					resultset.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return null;

	}

	public static final User getUserInfo(User user) {
		Connection connection = null;
		Statement stmt = null;
		ResultSet resultset = null;
		String sql = "select * from luser where id = '" + user.getId() + "'";

		try {
			connection = DBConnection.getConnection();
			stmt = connection.createStatement();
			resultset = stmt.executeQuery(sql);
			while (resultset.next()) {
				return new User(resultset.getString("id"), null, null,
						resultset.getString("bio"),
						resultset.getString("firstname"),
						resultset.getString("lastname"),
						resultset.getTimestamp("birthdate"),
						resultset.getString("location"));
			}
		} catch (SQLException ex) {
			Logger.getLogger(UserMapper.class.getName()).log(Level.SEVERE,
					null, ex);
		} finally {
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			if (resultset != null) {
				try {
					resultset.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}
