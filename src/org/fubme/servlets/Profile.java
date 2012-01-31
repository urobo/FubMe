package org.fubme.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.fubme.helper.Credentials;
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

		User user = null;
		Cookie[] cookies = request.getCookies();
		String username = null;
		String password = null;
		for (int i = 0; i < cookies.length; i++) {
			if (cookies[i].getName().equals("username"))
				username = cookies[i].getValue();
			if (cookies[i].getName().equals("password"))
				password = cookies[i].getValue();
		}
		user = Credentials.validateUserCredentials(username, password);
		if (user != null) {
			HttpSession session = request.getSession();
			if (session == null)
				session = request.getSession(true);
			User tmp = (User) request.getSession().getAttribute("loggedUser");
			if (tmp == null)
				session.setAttribute("loggedUser", user);
			String profileRequested = request.getParameter("user");
			User requested = new User(profileRequested, null);
			List<User> followers = UserMapper.getFollowers(requested);
			List<User> following = UserMapper.getFollowing(requested);
			List<org.fubme.models.Post> posts = TimelineManager
					.getProfileForUser(requested, 25);
			User info = UserMapper.getUserInfo(requested);
			request.setAttribute("followers", followers);
			request.setAttribute("following", following);
			request.setAttribute("posts", posts);
			request.setAttribute("info", info);
			request.getRequestDispatcher("profile.jsp").forward(request,
					response);
			return;

		}
		request.setAttribute("Error", "Invalid Credentials! Try again");
		RequestDispatcher view = request.getRequestDispatcher("login.jsp");
		view.forward(request, response);
		return;

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
