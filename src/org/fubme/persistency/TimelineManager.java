/**
 * 
 */
package org.fubme.persistency;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.fubme.factories.PostFactory;
import org.fubme.models.Post;
import org.fubme.models.User;

/**
 * @author riccardo
 * 
 */
public abstract class TimelineManager {
	public static final List<Post> getTimelineForUser(User user, int limit) {
		List<Post> result = new ArrayList<Post>();
		Connection connection = DBConnection.getConnection();
		Statement stmt = null;
		String sql = "SELECT * from post where luser_id in (select luser_id_followed from luser_follows_luser where luser_id_follower = '"
				+ user.getId()
				+ "') and luser_id  not in (select wrongdoing_id from luser_reports_luser where whistleblower_id = '"
				+ user.getId() + "') order by ptime,id limit " + limit;
		try {
			stmt = connection.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet timeline = stmt.executeQuery(sql);
			while (timeline.next()) {
				Post post = PostFactory.getPost(
						timeline.getTimestamp(Post.PTIME),
						timeline.getInt(Post.ID),
						timeline.getString(Post.LUSER_ID),
						timeline.getString(Post.BODY),
						timeline.getString(Post.LINK),
						timeline.getString(Post.MIME));
				post.setComments(Helper.getComments(post, user));
				post.setTags(Helper.getTags(post));
				for (int i = 0 ; i < post.getTags().size(); i++){
					System.out.println(post.getId() + "\t"+ post.getTags().get(i).getName());
				}
				result.add(post);
			}
		} catch (SQLException ex) {
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			if (stmt != null)
				stmt = null;
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {

				}
				connection = null;
			}
		}
		return result;
	}
}
