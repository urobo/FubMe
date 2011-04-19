/**
 * 
 */
package org.fubme.models.builders;

import java.net.URL;
import java.sql.Timestamp;

import org.fubme.models.Post;

/**
 * @author riccardo
 * 
 */
public class GeneralPost extends Post {

	public GeneralPost(Timestamp ptime, int id, String user_id, URL url,
			String body, String mime) {
		this.ptime = ptime;
		this.id = id;
		this.user_id = user_id;
		this.link = url;
		this.mime = mime;
	}

	public GeneralPost(String user_id, URL url, String body, String mime) {
		this.user_id = user_id;
		this.link = url;
		this.body = body;
		this.mime = mime;
	}

}
