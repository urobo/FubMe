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

import org.fubme.models.User;
import org.fubme.persistency.TimelineManager;

/**
 * Servlet implementation class Home
 */
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int maxPosts = 25;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Home() {
		super();
		// TODO Auto-generated constructor stub
	}

	private void setLoginCookies(HttpServletRequest request,
			HttpServletResponse response, String nickname, String password) {

		Cookie userNameCookie = new Cookie("username", nickname);
		Cookie passwordCookie = new Cookie("password", password);
		// Cookie age in seconds: 30 days * 24 hours * 60 minutes * 60 seconds
		int maxAge = 30 * 24 * 60 * 60;
		
		userNameCookie.setMaxAge(maxAge);
		passwordCookie.setMaxAge(maxAge);
		userNameCookie.setPath("/");
		passwordCookie.setPath("/");
		response.addCookie(userNameCookie);
		response.addCookie(passwordCookie);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		if (session != null) {
			User user = (User) session.getAttribute("loggedUser");
			List<org.fubme.models.Post> timeline = TimelineManager
					.getTimelineForUser(user, maxPosts);
			request.setAttribute("timeline", timeline);
			RequestDispatcher view = request.getRequestDispatcher("home.jsp");
			view.forward(request, response);
			return;

		} else {
			RequestDispatcher view = request.getRequestDispatcher("login.jsp");
			request.setAttribute("error", "Invalid user credentials");
			view.forward(request, response);
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = org.fubme.helper.Credentials.validateUserCredentials(
				username, password);
		if (user != null) {
			HttpSession session = request.getSession(true);
			setLoginCookies(request, response, user.getId(), user.getPswd());
			session.setAttribute("loggedUser", user);
			List<org.fubme.models.Post> timeline = TimelineManager
					.getTimelineForUser(new User(username, password), maxPosts);
			request.setAttribute("timeline", timeline);
			RequestDispatcher view = request.getRequestDispatcher("home.jsp");
			view.forward(request, response);
			return;
		} else {
			RequestDispatcher view = request.getRequestDispatcher("login.jsp");
			request.setAttribute("error", "Invalid user credentials");
			view.forward(request, response);
			return;
		}
	}

}
