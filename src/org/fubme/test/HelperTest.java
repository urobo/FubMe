/**
 * 
 */
package org.fubme.test;

import static org.junit.Assert.fail;

import org.fubme.models.Post;
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

	/**
	 * org.fubme.persistency.mappings
	 * 
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		post = Post.createPost("urobo", "testpost", null, Post.TEXT);
		PostMapper.createPost(post);

	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
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
		fail("Not yet implemented");
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
