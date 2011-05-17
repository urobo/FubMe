/**
 * 
 */
package org.fubme.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.fubme.models.User;
import org.fubme.persistency.DBConnection;
import org.fubme.persistency.mappings.UserMapper;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author riccardo
 * 
 */
public class UserMapperTest {
	private static final String testBody = "usermappertest";
	private static User user = null;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		user = new User(testBody, "password", testBody + "@" + testBody
				+ ".com");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		Connection connection = DBConnection.getConnection();
		Statement stmt = null;
		String sql = "DELETE FROM fuser where id = '" + user.getId() + "'";
		stmt = connection.createStatement();
		stmt.executeUpdate(sql);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link org.fubme.persistency.mappings.UserMapper#createUser(org.fubme.models.User)}
	 * .
	 */
	@Test
	public void testCreateUser() {
		UserMapper.createUser(user);

		Connection connection = DBConnection.getConnection();
		Statement stmt = null;
		final String sql = "SELECT FROM * fuser where id = '" + user.getId()
				+ "'";
		final String sql1 = "SELECT FROM * luser where id = '" + user.getId()
				+ "'";
		try {
			stmt = connection.createStatement();
			ResultSet rFuser = stmt.executeQuery(sql);
			rFuser.next();
			assertEquals(rFuser.getString("id"), user.getId());
			assertEquals(rFuser.getString("pswd"), user.getPswd());
			assertEquals(rFuser.getString("email"), user.getEmail());

			stmt = connection.createStatement();
			ResultSet rLuser = stmt.executeQuery(sql1);
			rLuser.next();
			assertEquals(rLuser.getString("id"), user.getId());

			if (user.getBio() instanceof String)
				assertEquals(rLuser.getString("bio"), user.getBio());

			if (user.getBirthdate() instanceof Timestamp)
				assertEquals(rLuser.getTimestamp("birthdate"),
						user.getBirthdate());

			if (user.getFirstname() instanceof String)
				assertEquals(rLuser.getString("firstname"), user.getFirstname());

			if (user.getLastname() instanceof String)
				assertEquals(rLuser.getString("lastname"), user.getLastname());

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