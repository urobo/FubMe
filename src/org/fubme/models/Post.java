/**
 * 
 */
package org.fubme.models;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;

/**
 * @author riccardo
 * 
 */
public class Post {
	public static final String PTIME = "ptime";
	public static final String ID = "id";
	public static final String LUSER_ID = "luser_id";
	public static final String BODY = "body";
	public static final String LINK = "link";
	public static final String MIME = "mime";

	public static final String TEXT = "text/plain";

	protected Date ptime;
	protected int id;
	protected String user_id;
	protected String body;
	protected URL link;
	protected String mime;
	protected List<Comment> comments;
	protected List<Tag> tags;

	public static Post getPost(Date ptime, int id, String user_id, String body,
			String link, String mime) {
		Post post = null;
		try {

			if (mime == TEXT) {
				post = new Post(ptime, id, user_id, body, mime);
			} else {
				post = new Post(ptime, id, user_id, new URL(link), body, mime);
			}
			return post;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static Post createPost(String user_id, String body, String link,
			String mime) {
		return new Post(user_id, body, link, mime);
	}

	/**
	 * @param ptime
	 * @param id
	 * @param user_id
	 * @param body
	 * @param mime
	 */
	protected Post(Date ptime, int id, String user_id, String body, String mime) {
		super();
		this.ptime = ptime;
		this.id = id;
		this.user_id = user_id;
		this.body = body;
		this.mime = mime;
	}

	/**
	 * @param ptime
	 * @param id
	 * @param user_id
	 * @param link
	 * @param mime
	 * @param body
	 */
	protected Post(Date ptime, int id, String user_id, URL link, String mime,
			String body) {
		super();
		this.ptime = ptime;
		this.id = id;
		this.user_id = user_id;
		this.link = link;
		this.mime = mime;
		this.body = body;
	}

	public Post(String user_id, String body, String link, String mime) {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the ptime
	 */
	public Date getPtime() {
		return ptime;
	}

	/**
	 * @param ptime
	 *            the ptime to set
	 */
	public void setPtime(Date ptime) {
		this.ptime = ptime;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the user_id
	 */
	public String getUser_id() {
		return user_id;
	}

	/**
	 * @param user_id
	 *            the user_id to set
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
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

	/**
	 * @return the link
	 */
	public URL getLink() {
		return link;
	}

	/**
	 * @param link
	 *            the link to set
	 */
	public void setLink(URL link) {
		this.link = link;
	}

	/**
	 * @return the mime
	 */
	public String getMime() {
		return mime;
	}

	/**
	 * @param mime
	 *            the mime to set
	 */
	public void setMime(String mime) {
		this.mime = mime;
	}

	/**
	 * @return the comments
	 */
	public List<Comment> getComments() {
		return comments;
	}

	/**
	 * @param comments
	 *            the comments to set
	 */
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	/**
	 * @return the tags
	 */
	public List<Tag> getTags() {
		return tags;
	}

	/**
	 * @param tags
	 *            the tags to set
	 */
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

}
