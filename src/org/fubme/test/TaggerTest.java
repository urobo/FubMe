/**
 * 
 */
package org.fubme.test;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.fubme.factories.PostFactory;
import org.fubme.models.Post;
import org.fubme.models.Tag;
import org.fubme.models.User;
import org.fubme.persistency.DBConnection;
import org.fubme.persistency.mappings.PostMapper;
import org.fubme.persistency.mappings.Tagger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author riccardo
 * 
 */
public class TaggerTest {
	private static final String testBody = "taggertest";
	private static Post post = null;
	private static final String[] tagList = { "tagname1", "tagname2",
			"tagname3", "tagname4", "tagname5", };

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		post = PostFactory.getPost("daniel", testBody, null, Post.TEXT);
		PostMapper.createPost(post);

		Connection connection = DBConnection.getConnection();
		Statement stmt = null;
		String sql = "SELECT * FROM post where body = '" + testBody + "'";
		stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
		ResultSet idSet = stmt.executeQuery(sql);
		idSet.next();

		int id = idSet.getInt(Post.ID);
		post.setId(id);

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

		stmt = null;
		String sql1 = "DELETE FROM post_tagged_as where post_id = "
				+ post.getId();
		stmt = connection.createStatement();
		stmt.executeUpdate(sql1);

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
	 * {@link org.fubme.persistency.mappings.Tagger#tagAs(org.fubme.models.Post, java.util.List)}
	 * .
	 */
	@Test
	public void testTagAs() {
		List<Tag> tags = new ArrayList<Tag>();
		for (int i = 0; i < tagList.length; i++)
			tags.add(new Tag(tagList[i]));
		post.setTags(tags);
		Tagger.tagAs(post);

		Connection connection = null;
		Statement stmt = null;
		String sql = "SELECT * from post_tagged_as where post_id = "
				+ post.getId();
		try {
			connection = DBConnection.getConnection();
			stmt = connection.createStatement();
			ResultSet result = stmt.executeQuery(sql);
			HashMap<String, String> fastCheck = new HashMap<String, String>();
			while (result.next()) {
				fastCheck.put(result.getString("tag_name"),
						result.getString("tag_name"));
			}
			for (int i = 0; i < tags.size(); i++) {
				assertTrue(fastCheck.containsKey(tags.get(i).getName()));
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
