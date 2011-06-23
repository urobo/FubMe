/**
 * 
 */
package org.fubme.factories;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;

import org.fubme.models.Post;
import org.fubme.models.builders.GeneralPost;
import org.fubme.models.builders.TextPost;

/**
 * @author riccardo
 * 
 */
public abstract class PostFactory {
	public static final Post getPost(Timestamp ptime, int id, String user_id,
			String body, String link, String mime) {
		Post post = null;
		try {

			if (mime.equals(Post.TEXT)) {
				post = new TextPost(ptime, id, user_id, body, mime);
			} else {
				post = new GeneralPost(ptime, id, user_id, new URL(link), body,
						mime);
			}
			return post;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static Post getPost(String user_id, String body, URL link,
			String mime) {
		Post post = null;
		if (mime == Post.TEXT) {
			post = new TextPost(user_id, body, mime);
		} else {
			post = new GeneralPost(user_id, link, body, mime);
		}
		return post;
	}
	
	public static final Post getPost(Timestamp ptime, int id, String user_id,
			String body, String link, String mime,String via_user_id) {
		Post post = null;
		try {

			if (mime.equals(Post.TEXT)) {
				post = new TextPost(ptime, id, user_id, body, mime,via_user_id);
			} else {
				post = new GeneralPost(ptime, id, user_id, new URL(link), body,
						mime,via_user_id);
			}
			return post;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
