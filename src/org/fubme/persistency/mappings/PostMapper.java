/**
 * 
 */
package org.fubme.persistency.mappings;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.fubme.models.Post;
import org.fubme.models.Report;
import org.fubme.models.User;
import org.fubme.persistency.DBConnection;

/**
 * @author riccardo
 * 
 */
public abstract class PostMapper {

	public static final void createPost(Post post) {
		Connection connection = null;
		PreparedStatement stmt = null;
		String sql = "INSERT INTO post (" + Post.LUSER_ID + "," + Post.LINK
				+ " ," + Post.BODY + "," + Post.MIME
				+ ") VALUES ( ?, ?, ?, ? ) RETURNING id";
		try {
			connection = DBConnection.getConnection();
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, post.getUser_id());
			stmt.setString(2, (post.getLink() != null) ? post.getLink()
					.toString() : "null");
			stmt.setString(3, post.getBody());
			stmt.setString(4, post.getMime());
			ResultSet id = stmt.executeQuery();
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
				try {
					stmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static final void likes(User user, Post post) {
		Connection connection = null;
		PreparedStatement stmt = null;
		String sql = "INSERT INTO luser_likes_post (luser_id , post_id) VALUES ( ?, ? )";
		try {
			connection = DBConnection.getConnection();
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, user.getId());
			stmt.setInt(2, post.getId());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(PostMapper.class.getName()).log(Level.SEVERE,
					null, ex);
		} finally {
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static final void unlikes(User user, Post post) {
		Connection connection = null;
		PreparedStatement stmt = null;
		String sql = " DELETE FROM luser_likes_post where luser_id = ? and post_id = ?";
		try {
			connection = DBConnection.getConnection();
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, user.getId());
			stmt.setInt(2, post.getId());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(PostMapper.class.getName()).log(Level.SEVERE,
					null, ex);
		} finally {
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static final void reportPost(Report report) {
		Connection connection = null;
		PreparedStatement stmt = null;
		String sql = "INSERT INTO luser_reports_post (post_id,whistleblower_id,wrongdoing_id,category,reason) VALUES (?,?,?,?,?)";
		try {
			connection = DBConnection.getConnection();
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, report.getPostId());
			stmt.setString(2, report.getWhistleblowerId());
			stmt.setString(3, report.getWrongdoingId());
			stmt.setString(4, report.getCategory());
			stmt.setString(5, report.getReason());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(PostMapper.class.getName()).log(Level.SEVERE,
					null, ex);
		} finally {
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
