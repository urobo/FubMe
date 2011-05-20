/**
 * 
 */
package org.fubme.test;

import static org.junit.Assert.*;

import org.fubme.models.User;
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
public class TimelineManagerTest {
	private static final String testBody = "timelinemanagertest";
	private static final int maxUsers = 4;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		for (int i = 0; i < maxUsers ; i++)
			UserMapper.createUser(new User(testBody + i, "password", testBody+i+"@"+testBody+".com"));
		//TODO: create post and follows chains
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
	public void setUp() throws Exception {null
	}

	/**
	 * @throws nulljava.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link org.fubme.persistency.TimelineManager#getTimelineForUser(org.fubme.models.User, int)}.
	 */
	@Test
	public void testGetTimelineForUser() {
		fail("Not yet implemented");
	}

}
