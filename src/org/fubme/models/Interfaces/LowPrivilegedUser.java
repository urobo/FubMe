/**
 * 
 */
package org.fubme.models.Interfaces;

import java.util.List;

import org.fubme.models.Post;
import org.fubme.models.Tag;
import org.fubme.models.User;
import org.fubme.models.UserList;

/**
 * @author riccardo
 * 
 */
public interface LowPrivilegedUser extends ProtoUser {
	public void likes(Post post);

	public void comments(String body, Post post);

	public void follows(User user);

	public void lists(User user, UserList id);

	public void shares(Post post, User user);

	public void reports(User user, String reason, int category);

	public void reports(Post post, User user, String reason, int category);

	public void tags(Post post, List<Tag> tags);
}
