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
public abstract class TrackTag {
	public static final List<Post> searchPostTaggedAs(User user,
			List<String> tags, int limit) {
		List<Post> posts = new ArrayList<Post>();
		Connection connection = DBConnection.getConnection();
		Statement stmt = null;
		String sql="";
		for (int i = 0; i < tags.size(); i++){
		if (!sql.isEmpty()) sql += " union";
		 sql += "SELECT * from post where id in (select post_id from post_tagged_as where tag_name = '"
				+ tags.get(i)
				+ "') and id not in (SELECT post.id from post,luser_reports_luser where whistleblower_id = '"
				+ user.getId()
				+ "' and wrongdoing_id = post.luser_id) ";
		}
		sql += "limit "+ limit;
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
				for (int i = 0; i < post.getTags().size(); i++) {
					System.out.println(post.getId() + "\t"
							+ post.getTags().get(i).getName());
				}
				System.out.println(post.getId()+"\t"+post.getBody());
				posts.add(post);
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

		return posts;
	}
}
