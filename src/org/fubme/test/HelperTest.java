/**
 * 
 */
package org.fubme.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.fubme.factories.PostFactory;
import org.fubme.models.Comment;
import org.fubme.models.Post;
import org.fubme.models.Tag;
import org.fubme.models.User;
import org.fubme.persistency.DBConnection;
import org.fubme.persistency.Helper;
import org.fubme.persistency.mappings.CommentMapper;
import org.fubme.persistency.mappings.PostMapper;
import org.fubme.persistency.mappings.Tagger;
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
public class HelperTest {
	private static User testUser = null;
	private static Post post = null;
	private static List<Comment> comments = null;
	private static List<Tag> tags = null;
	private static final String testBody = "helpertest";

	private static final String[] tagList = { "taghelper1", "taghelper2",
			"taghelper3", "taghelper4", "taghelper5", };
	private static final int POSTS_NUMBER = 10;

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
		String sql = "SELECT * FROM post where body = '" + testBody + "'";
		stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
		ResultSet idSet = stmt.executeQuery(sql);
		idSet.next();

		int id = idSet.getInt(Post.ID);
		post.setId(id);

		tags = new ArrayList<Tag>();
		for (int i = 0; i < tagList.length; i++)
			tags.add(new Tag(tagList[i]));
		post.setTags(tags);
		Tagger.tagAs(post);

		comments = new ArrayList<Comment>();
		for (int i = 0; i < user_ids.length; i++) {
			comments.add(new Comment("" + post.getId(), user_ids[i], testBody));
			CommentMapper.createCommentToPost(comments.get(i));
		}

		testUser = new User(testBody, "password", testBody + "@" + testBody
				+ ".com");
		UserMapper.createUser(testUser);
		for (int i = 0; i < POSTS_NUMBER; i++) {
			PostMapper.createPost(PostFactory.getPost(testUser.getId(),
					testBody, null, Post.TEXT));
		}
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		Connection connection = DBConnection.getConnection();
		Statement stmt = null;
		final String sql = "DELETE FROM post where post.body = '" + testBody
				+ "'";
		stmt = connection.createStatement();
		stmt.executeUpdate(sql);

		stmt = null;
		final String sql1 = "DELETE FROM post_tagged_as where post_id = "
				+ post.getId();
		stmt = connection.createStatement();
		stmt.executeUpdate(sql1);

		stmt = null;
		final String sql2 = "DELETE FROM fuser where id = '" + testUser.getId()
				+ "'";
		stmt = connection.createStatement();
		stmt.executeUpdate(sql2);
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
		List<Comment> rComments = Helper.getComments(post, new User("urobo",
				"password"));
		assertNotNull(rComments);
		assertEquals(rComments.size(), comments.size());
		HashMap<String, Comment> analyzer = new HashMap<String, Comment>();
		for (int i = 0; i < rComments.size(); i++) {
			analyzer.put(rComments.get(i).getLuser_id(), rComments.get(i));
		}

		for (int i = 0; i < comments.size(); i++) {
			assertTrue(analyzer.containsKey((comments.get(i).getLuser_id())));
			Comment tmp = analyzer.get((comments.get(i).getLuser_id()));
			assertEquals(tmp.getBody(), testBody);
			assertEquals(tmp.getPost_id(), comments.get(i).getPost_id());
		}
	}

	/**
	 * Test method for
	 * {@link org.fubme.persistency.Helper#getTags(org.fubme.models.Post)}.
	 */
	@Test
	public void testGetTags() {
		List<Tag> rTags = Helper.getTags(post);
		assertEquals(rTags.size(), tagList.length);
		HashMap<String, String> fastCheck = new HashMap<String, String>();
		for (int i = 0; i < rTags.size(); i++)
			fastCheck.put(rTags.get(i).getName(), rTags.get(i).getName());
		for (int i = 0; i < tagList.length; i++) {
			assertTrue(fastCheck.containsKey(tagList[i]));
			System.out.println(tagList[i]);
		}
	}

	/**
	 * Test method for
	 * {@link org.fubme.persistency.Helper#getPostsFromUser(org.fubme.models.User, int)}
	 * .
	 */
	@Test
	public void testGetPostsFromUser() {
		List<Post> rPost = Helper.getPostsFromUser(testUser, 100);
		assertEquals(rPost.size(), POSTS_NUMBER);
		for (int i = 0; i < POSTS_NUMBER; i++)
			assertEquals(rPost.get(i).getBody(), testBody);
	}

}
