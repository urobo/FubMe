/**
 * 
 */
package org.fubme.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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
		stmt.close();
		connection.close();
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
	 * {@link org.fubme.persistency.mappings.UserMapper#checkUserData(java.lang.String,java.lang.String)}
	 * .
	 */
	@Test
	public void testCheckUserData() {
		String attribute = "id";
		String value0 = "urobo";
		String value1 = "kratos";
		boolean result = UserMapper.checkUserData(attribute, value0);
		System.out.println(attribute + "\t" + value0 + "\t" + result);
		assertTrue(result);
		result = UserMapper.checkUserData(attribute, value1);
		System.out.println(attribute + "\t" + value1 + "\t" + result);
		assertFalse(result);

		attribute = "email";
		value0 = "rico.sleeps@gmail.com";
		value1 = testBody + "@" + testBody + ".com";
		result = UserMapper.checkUserData(attribute, value0);
		System.out.println(attribute + "\t" + value0 + "\t" + result);
		assertTrue(result);

		result = UserMapper.checkUserData(attribute, value1);
		System.out.println(attribute + "\t" + value1 + "\t" + result);
		assertFalse(result);
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
		final String sql = "SELECT * FROM fuser where id = '" + user.getId()
				+ "'";
		final String sql1 = "SELECT * FROM luser where id = '" + user.getId()
				+ "'";
		try {
			stmt = connection.createStatement();
			ResultSet rFuser = stmt.executeQuery(sql);
			rFuser.next();
			assertEquals(rFuser.getString("id"), user.getId());
			assertEquals(rFuser.getString("pswd"), user.getPswd());
			assertEquals(rFuser.getString("email"), user.getEmail());
			stmt.close();

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

			stmt.close();
			rFuser.close();
			rLuser.close();

		} catch (SQLException ex) {
			fail(ex.getMessage());
			Logger.getLogger(UserMapperTest.class.getName()).log(Level.SEVERE,
					"UserMapperTest.testCreateUser()", ex);
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
