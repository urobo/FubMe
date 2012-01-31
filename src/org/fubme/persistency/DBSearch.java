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
import org.fubme.persistency.mappings.UserMapper;

/**
 * @author riccardo
 * 
 */
public abstract class DBSearch {
	public static final List<Post> searchPost(String keyword) {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet resultset = null;
		String sql = "select * from post where body ilike ?";
		List<Post> result = new ArrayList<Post>();
		try {
			connection = DBConnection.getConnection();
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, "%" + keyword + "%");
			resultset = stmt.executeQuery();
			while (resultset.next()) {
				result.add(PostFactory.getPost(resultset.getTimestamp("ptime"),
						resultset.getInt("id"),
						resultset.getString("luser_id"),
						resultset.getString("body"), null,
						resultset.getString("mime")));
			}
			return result;
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

	public static final List<User> searchPeople(String keyword) {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet resultset = null;
		String sql = "select * from luser where id ilike ?";
		List<User> result = new ArrayList<User>();
		try {
			connection = DBConnection.getConnection();
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, "%" + keyword + "%");
			resultset = stmt.executeQuery();
			while (resultset.next()) {
				result.add(new User(resultset.getString("id"), null, null,
						resultset.getString("bio"), resultset
								.getString("firstname"), resultset
								.getString("lastname"), resultset
								.getTimestamp("birthdate"), resultset
								.getString("location")));
			}
			return result;
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
