/**
 * 
 */
package org.fubme.persistency;

import java.util.ArrayList;
import java.util.List;

import org.fubme.models.Post;
import org.fubme.models.User;

/**
 * @author riccardo
 * 
 */
public abstract class TimelineManager {
	static final List<Post> getTimelineForUser(User user) {
		List<Post> result = new ArrayList<Post>();
		return result;
	}
}
