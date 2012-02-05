package org.fubme.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.fubme.helper.Credentials;
import org.fubme.models.User;
import org.fubme.persistency.DBSearch;
import org.fubme.persistency.TrackTag;

/**
 * Servlet implementation class Search
 */
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Search() {
		super();
		// TODO Auto-generated constructor stub
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
					String tag = request.getParameter("tags");
					List<String> tags = new ArrayList<String>();
					tags.add(tag);
					List<org.fubme.models.Post> result = TrackTag
							.searchPostTaggedAs(user, tags, 25);

					request.setAttribute("posts", result);
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("search.jsp");
					dispatcher.forward(request, response);
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
		// TODO Auto-generated method stub

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

					String searchPeople = request.getParameter("people");
					String searchPostBody = request.getParameter("posts");
					String keywords = request.getParameter("searchkeywords");

					// FIXME just a little bit
					List<org.fubme.models.Post> posts = new ArrayList<org.fubme.models.Post>();
					List<User> people = new ArrayList<User>();

					String[] searchKeywords = keywords.split(" ");

					if (!keywords.toLowerCase().equals("search")) {
						Logger.getLogger(Search.class.getName()).log(
								Level.SEVERE, "searchKeyWords :" + keywords);
						if (searchPostBody instanceof String) {
							Logger.getLogger(Search.class.getName()).log(
									Level.SEVERE,
									"searchPostBody :" + searchPostBody);
							posts = DBSearch.searchPosts(searchKeywords);
						}
						if (searchPeople instanceof String) {
							Logger.getLogger(Search.class.getName()).log(
									Level.SEVERE,
									"searchPeople :" + searchPeople);
							people = DBSearch.searchPeople(searchKeywords);
						}
					}

					request.setAttribute("posts", posts);
					request.setAttribute("people", people);

					RequestDispatcher dispatcher = request
							.getRequestDispatcher("search.jsp");
					dispatcher.forward(request, response);
					return;
				}

			}
		}
		request.setAttribute("Error", "You have to login first!");
		RequestDispatcher view = request.getRequestDispatcher("login.jsp");
		view.forward(request, response);
		return;
	}

}
