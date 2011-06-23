/**
 * 
 */
package org.fubme.persistency.mappings;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.fubme.models.Post;
import org.fubme.models.User;
import org.fubme.persistency.DBConnection;

/**
 * @author riccardo
 * 
 */
public abstract class PostMapper {

	public static final void createPost(Post post) {
		Connection connection = DBConnection.getConnection();
		Statement stmt = null;
		String sql = "INSERT INTO post ("
				+ Post.LUSER_ID
				+ ","
				+ Post.LINK
				+ " ,"
				+ Post.BODY
				+ ","
				+ Post.MIME
				+ ") VALUES ('"
				+ post.getUser_id()
				+ "','"
				+ ((post.getLink() != null) ? post.getLink().toString()
						: "null") + "','" + post.getBody() + "','"
				+ post.getMime() + "') RETURNING id";
		try {
			stmt = connection.createStatement();
			ResultSet id = stmt.executeQuery(sql);
			id.next();
			Logger.getLogger(PostMapper.class.getName()).log(Level.SEVERE,
					"post number " + id.getInt("id"));
			if (post.getTags() != null) {
				post.setId(id.getInt("id"));
				Tagger.tagAs(post);
			}
			stmt.close();
		} catch (SQLException ex) {
			Logger.getLogger(PostMapper.class.getName()).log(Level.SEVERE,
					null, ex);
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
	}
	
	public static final void shares (User viaUser, User byUser, Post post){
		Connection connection = DBConnection.getConnection();
		Statement stmt = null;
		String sql = "INSERT INTO luser_shares_post (post_id,luser_id,via_luser_id) values ("+post.getId()+",'"+byUser.getId()+"','"+viaUser.getId()+"')";
		try {
			stmt = connection.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException ex) {
			Logger.getLogger(PostMapper.class.getName()).log(Level.SEVERE, null, ex);
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
		
	}
	
	public static final void likes(User user, Post post) {
		Connection connection = DBConnection.getConnection();
		Statement stmt = null;
		String sql = "INSERT INTO luser_likes_post (luser_id , post_id) VALUES ('"
				+ user.getId() + "'," + post.getId() + ")";
		try {
			stmt = connection.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException ex) {
			Logger.getLogger(PostMapper.class.getName()).log(Level.SEVERE, null, ex);
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
	}
}
