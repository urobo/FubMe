package org.fubme.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fubme.models.User;
import org.fubme.persistency.TimelineManager;
import org.fubme.persistency.mappings.UserMapper;

/**
 * Servlet implementation class Profile
 */
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Profile() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession() == null) {
			request.getRequestDispatcher("login.jsp")
					.forward(request, response);
			return;
		} else {
			User user = new User(request.getParameter("user"), null);
			List<org.fubme.models.Post> posts = TimelineManager
					.getProfileForUser(user, 25);
			List<User> followers = UserMapper.getFollowers(user);
			List<User> following = UserMapper.getFollowing(user);
			User info = UserMapper.getUserInfo(user);
			request.setAttribute("followers", followers);
			request.setAttribute("following", following);
			request.setAttribute("posts", posts);
			request.setAttribute("info", info);
			request.getRequestDispatcher("profile.jsp").forward(request,
					response);
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
