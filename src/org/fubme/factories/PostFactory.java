/**
 * 
 */
package org.fubme.factories;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import org.fubme.models.Post;
import org.fubme.models.builders.GeneralPost;
import org.fubme.models.builders.TextPost;

/**
 * @author riccardo
 * 
 */
public abstract class PostFactory {
	public static final Post getPost(Date ptime, int id, String user_id,
			String body, String link, String mime) {
		Post post = null;
		try {

			if (mime == Post.TEXT) {
				post = new TextPost(ptime, id, user_id, body, mime);
			} else {
				post = new GeneralPost(ptime, id, user_id, new URL(link), body, mime);
			}
			return post;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
