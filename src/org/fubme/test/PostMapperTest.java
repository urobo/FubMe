/**
 * 
 */
package org.fubme.test;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.fubme.factories.PostFactory;
import org.fubme.models.Post;
import org.fubme.models.User;
import org.fubme.persistency.DBConnection;
import org.fubme.persistency.mappings.PostMapper;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author riccardo
 * 
 */
public class PostMapperTest {
	private static List<Post> metaPost = new ArrayList<Post>();
	private static String user_ids[] = { "daniel", "urobo", "edavia", "dio" };
	private static String testBody = "postmappertest";

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		for (int i = 0; i < user_ids.length; i++)
			metaPost.add(PostFactory.getPost(user_ids[i], testBody, null,
					Post.TEXT));

	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		Connection connection = DBConnection.getConnection();
		Statement stmt = null;
		String sql = "DELETE FROM post where post.body = '" + testBody + "'";
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
	 * {@link org.fubme.persistency.mappings.PostMapper#createPost(org.fubme.models.Post)}
	 * .
	 */
	@Test
	public void testCreatePost() {
		for (int i = 0; i < user_ids.length; i++)
			PostMapper.createPost(metaPost.get(i));

		Connection connection = DBConnection.getConnection();

		Statement stmt = null;
		String sql = "SELECT luser_id from post where post.body = '" + testBody
				+ "'";

		try {
			stmt = connection.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet results = stmt.executeQuery(sql);
			Post partial = null;
			while (!metaPost.isEmpty()) {
				if (partial != null || metaPost.get(0).equals(partial))
					fail("some posts have not been submitted");
				partial = metaPost.get(0);
				results.beforeFirst();

				while (results.next()) {
					System.out.println(results.getString(Post.LUSER_ID));
					System.out.println(metaPost.get(0).getUser_id());
					if (results.getString(Post.LUSER_ID).equals(
							metaPost.get(0).getUser_id()))
						metaPost.remove(0);
				}
			}
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
