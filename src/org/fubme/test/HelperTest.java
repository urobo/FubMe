/**
 * 
 */
package org.fubme.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.fubme.factories.PostFactory;
import org.fubme.models.Comment;
import org.fubme.models.Post;
import org.fubme.models.User;
import org.fubme.persistency.DBConnection;
import org.fubme.persistency.Helper;
import org.fubme.persistency.mappings.CommentMapper;
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
public class HelperTest {
	private static Post post = null;
	private static List<Comment> comments = null;
	private static final String testBody = "helpertest";
	
	private static String[] user_ids = { "urobo", "ozzy", "dio", "edavia" };

	/**
	 * org.fubme.persistency.mappings
	 * 
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		post = PostFactory.getPost("urobo", testBody, null, Post.TEXT);
		PostMapper.createPost(post);
		
		Connection connection = DBConnection.getConnection();
		Statement stmt = null;
		String sql = "SELECT * FROM post where body = '"+testBody+"'";
		stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
		ResultSet idSet = stmt.executeQuery(sql);
		idSet.next();
		
		int id = idSet.getInt(Post.ID);
		post.setId(id);
		
		comments = new ArrayList<Comment>();
		for (int i = 0; i<user_ids.length; i++){
			comments.add(new Comment(post.getId(), user_ids[i], testBody));
			CommentMapper.createCommentToPost(comments.get(i));
		}
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		Connection connection = DBConnection.getConnection();
		Statement stmt = null;
		String sql = "DELETE FROM post where post.body = '"+testBody+"'";
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
	 * {@link org.fubme.persistency.Helper#getComments(org.fubme.models.Post, org.fubme.models.User)}
	 * .
	 */
	@Test
	public void testGetComments() {
		List<Comment> rComments = Helper.getComments(post, new User("urobo", "password"));
		assertNotNull(rComments);
		assertEquals(rComments.size(),comments.size());
		HashMap<String,Comment> analyzer = new HashMap<String,Comment>();
		for (int i = 0; i < rComments.size(); i++){
			analyzer.put(rComments.get(i).getLuser_id(), rComments.get(i));
		}
		
		for (int i = 0; i < comments.size(); i++){
			assertTrue(analyzer.containsKey((comments.get(i).getLuser_id())));
			Comment tmp = analyzer.get((comments.get(i).getLuser_id()));
			assertEquals(tmp.getBody(),testBody);
			assertEquals(tmp.getPost_id(),comments.get(i).getPost_id());
		}
	}

	/**
	 * Test method for
	 * {@link org.fubme.persistency.Helper#getTags(org.fubme.models.Post)}.
	 */
	@Test
	public void testGetTags() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link org.fubme.persistency.Helper#getPostsFromUser(org.fubme.models.User, int)}
	 * .
	 */
	@Test
	public void testGetPostsFromUser() {
		fail("Not yet implemented");
	}

}
