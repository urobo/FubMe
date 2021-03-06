/**
 * 
 */
package org.fubme.models;

import java.net.URL;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author riccardo
 * 
 */
/**
 * @author riccardo
 * 
 */
public abstract class Post {
	public static final String PTIME = "ptime";
	public static final String ID = "id";
	public static final String LUSER_ID = "luser_id";
	public static final String BODY = "body";
	public static final String LINK = "link";
	public static final String MIME = "mime";
	public static final String VIA_USER_ID = "via_luser_id";

	public static final String TEXT = "text/plain";

	protected Timestamp ptime;
	protected int id;
	protected String user_id;
	protected String via_user_id;
	protected String body;
	protected URL link;
	protected String mime;
	protected List<Comment> comments;
	protected List<Tag> tags;

	/**
	 * @return the ptime
	 */
	public Timestamp getPtime() {
		return ptime;
	}

	/**
	 * @param ptime
	 *            the ptime to set
	 */
	public void setPtime(Timestamp ptime) {
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

	public class TextPost extends Post {

	}

	public void setId(String id) {
		this.id = Integer.parseInt(id);
	}

	/**
	 * @return the via_user_id
	 */
	public String getVia_user_id() {
		return via_user_id;
	}

	/**
	 * @param via_user_id
	 *            the via_user_id to set
	 */
	public void setVia_user_id(String via_user_id) {
		this.via_user_id = via_user_id;
	}

}
