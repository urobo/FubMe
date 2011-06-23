/**
 * 
 */
package org.fubme.test;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
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
public class CommentMapperTest {

	private static Post post = null;
	private static List<Comment> comments = null;
	private static int post_id = -1;
	private static User user = null;
	private static String testBody = "commentmappertest";

	private static String[] user_ids = { "urobo", "ozzy", "dio", "edavia" };

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		post = PostFactory.getPost("daniel", testBody, null, Post.TEXT);
		PostMapper.createPost(post);
		user = new User("daniel", "password");
		List<Post> posts = Helper.getPostsFromUser(user, 5);

		for (int i = 0; i < posts.size(); i++) {
			if (posts.get(i).getBody().equals(testBody))
				post_id = posts.get(i).getId();
		}
		comments = new ArrayList<Comment>();
		for (int i = 0; i < 10; i++) {
			comments.add(new Comment("" + post_id, user_ids[(i % 4)], testBody));
		}
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
	 * {@link org.fubme.persistency.mappings.CommentMapper#createCommentToPost(org.fubme.models.Comment)}
	 * .
	 */
	@Test
	public void testCreateCommentToPost() {
		// sending comments

		for (int i = 0; i < comments.size(); i++) {
			CommentMapper.createCommentToPost(comments.get(i));
		}

		List<Comment> dbComments = Helper.getComments(post, user);

		for (int i = 0; i < dbComments.size(); i++) {
			boolean check = false;
			for (int j = 0; i < comments.size(); i++) {
				if (dbComments.get(i).getLuser_id()
						.equals(comments.get(j).getLuser_id())
						&& dbComments.get(i).getBody()
								.equals(comments.get(j).getBody()))
					check = true;
			}
			assertTrue(check);
		}
	}

}
