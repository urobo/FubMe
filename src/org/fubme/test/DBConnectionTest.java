/**
 * 
 */
package org.fubme.test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.fubme.persistency.DBConnection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author riccardo
 * 
 */
public class DBConnectionTest {

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
	 * {@link org.fubme.persistency.DBConnection#getConnection()}.
	 */
	@Test
	public void testGetConnection() {
		Statement stmt = null;
		Connection connection = null;
		String sql = "SELECT * FROM fuser;";
		try {
			connection = DBConnection.getConnection();
			stmt = connection.createStatement();
			ResultSet set = stmt.executeQuery(sql);
			while (set.next()) {

				assertTrue((set.getString("id")) instanceof String);
				System.out.println(set.getString("id"));
			}
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			fail();
			e.printStackTrace();
		}

	}

}
