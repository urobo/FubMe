/**
 * 
 */
package org.fubme.models.builders;

import java.util.Date;

import org.fubme.models.Post;

/**
 * @author riccardo
 *
 */
public class TextPost extends Post {

	public TextPost(Date ptime, int id, String user_id, String body, String mime) {
		this.ptime = ptime;
		this.id = id;
		this.user_id = user_id;
		this.body = body;
		this.mime = mime;
	}
	
	public TextPost (String user_id,String body, String mime){
		this.user_id = user_id;
		this.body = body;
		this.mime = mime;
	}

}
