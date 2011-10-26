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

/**
 * Servlet implementation class Home
 */
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final int maxPosts = 25;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Home() {
		super();
	}

	private void setLoginCookies(HttpServletRequest request,
			HttpServletResponse response, String username, String password) {

		Cookie userNameCookie = new Cookie("username", username);
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
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			String username = null;
			String password = null;
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("username"))
					username = cookies[i].getValue();
				else if (cookies[i].getName().equals("password"))
					password = cookies[i].getValue();
			}
			if (username != null && password != null) {
				User user = Credentials.validateUserCredentials(username,
						password);
				if (user != null) {
					if (session == null)
						session = request.getSession(true);
					if (session.getAttribute("loggedUser") == null)
						session.setAttribute("loggedUser", user);
					List<org.fubme.models.Post> timeline = TimelineManager
							.getTimelineForUser(user, maxPosts);
					request.setAttribute("timeline", timeline);
					RequestDispatcher view = request
							.getRequestDispatcher("home.jsp");
					view.forward(request, response);
					return;
				}

			}
		}
		request.setAttribute("Error", "You have to login first!");
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
		User user = null;
		HttpSession session = null;
		String password = null;
		String username = null;
		username = request.getParameter("username");
		password = request.getParameter("password");
		if (username != null && password != null) {
			user = Credentials.validateUserCredentials(username, password);
			if (user != null) {
				session = request.getSession();
				if (session == null)
					session = request.getSession(true);
				if (session.getAttribute("loggedUser") == null)
					session.setAttribute("loggedUser", user);
				setLoginCookies(request, response, username, password);
				List<org.fubme.models.Post> timeline = TimelineManager
						.getTimelineForUser(new User(username, password),
								maxPosts);
				request.setAttribute("timeline", timeline);
				RequestDispatcher view = request
						.getRequestDispatcher("home.jsp");
				view.forward(request, response);
				return;
			}
		}
		request.setAttribute("Error", "Invalid Credentials! Try again");
		RequestDispatcher view = request.getRequestDispatcher("login.jsp");
		view.forward(request, response);
		return;
	}
}
