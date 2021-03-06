/**
 * 
 */
package org.fubme.models;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author riccardo
 * 
 */
public class Comment {
	public static final String ID = "id";
	public static final String POST_ID = "post_id";
	public static final String LUSER_ID = "luser_id";
	public static final String TIME = "time";
	public static final String BODY = "body";

	protected int id;
	protected int post_id;
	protected String luser_id;
	protected Timestamp time;
	protected String body;

	/**
	 * @param post_id
	 * @param luser_id
	 * @param body
	 */
	public Comment(String post_id, String luser_id, String body) {
		this.post_id = Integer.parseInt(post_id);
		this.luser_id = luser_id;
		this.body = body;
	}

	/**
	 * @param id
	 * @param post_id
	 * @param luser_id
	 * @param time
	 * @param body
	 */
	public Comment(int id, String post_id, String luser_id, Timestamp time,
			String body) {
		this.id = id;
		this.post_id = Integer.parseInt(post_id);
		this.luser_id = luser_id;
		this.time = time;
		this.body = body;
	}

	/**
	 * @param post_id
	 * @param luser_id
	 * @param time
	 * @param body
	 */
	public Comment(String post_id, String luser_id, Timestamp time, String body) {
		this.post_id = Integer.parseInt(post_id);
		this.luser_id = luser_id;
		this.time = time;
		this.body = body;
	}

	/**
	 * @return the post_id
	 */
	public int getPost_id() {
		return post_id;
	}

	/**
	 * @param post_id
	 *            the post_id to set
	 */
	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}

	/**
	 * @return the luser_id
	 */
	public String getLuser_id() {
		return luser_id;
	}

	/**
	 * @param luser_id
	 *            the luser_id to set
	 */
	public void setLuser_id(String luser_id) {
		this.luser_id = luser_id;
	}

	/**
	 * @return the time
	 */
	public Date getTime() {
		return time;
	}

	/**
	 * @param time
	 *            the time to set
	 */
	public void setTime(Timestamp time) {
		this.time = time;
	}

	/**
	 * @return the body
	 */
	public String getBody() {
		return body;
	}

	/**
	 * @param body
	 *            the body to set
	 */
	public void setBody(String body) {
		this.body = body;
	}

}
