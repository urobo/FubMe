/**
 * 
 */
package org.fubme.models.Interfaces;

import org.fubme.models.Post;
import org.fubme.models.User;

/**
 * @author riccardo
 * 
 */
public interface HighPrivilegedUser extends ProtoUser {
	public void blocks(User user, String reason, int category);

	public void censors(Post post, String reason, int category);
}
