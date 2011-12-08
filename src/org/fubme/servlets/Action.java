package org.fubme.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.fubme.factories.PostFactory;
import org.fubme.helper.Credentials;
import org.fubme.models.Comment;
import org.fubme.models.User;
import org.fubme.persistency.mappings.CommentMapper;
import org.fubme.persistency.mappings.PostMapper;
import org.fubme.persistency.mappings.UserMapper;

/**
 * Servlet implementation class Action
 */
public class Action extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private void processRequest(HttpServletRequest request,
			HttpServletResponse response, String action, User user)
			throws ServletException, IOException {

		if (action.equals("comments")) {
			String post_id = request.getParameter("post_id");
			String user_id = request.getParameter("user_id");
			String body = request.getParameter("comment_text");
			CommentMapper.createCommentToPost(new Comment(post_id, user_id,
					body));
		} else if (action.equals("likes")) {
			String post_id = request.getParameter("post_id");

			PostMapper.likes(user, PostFactory.getPost(null,
					Integer.parseInt(post_id), null, null, null,
					org.fubme.models.Post.TEXT));
		} else if (action.equals("unlikes")) {
			String post_id = request.getParameter("post_id");
			PostMapper.unlikes(user, PostFactory.getPost(null,
					Integer.parseInt(post_id), null, null, null,
					org.fubme.models.Post.TEXT));
		} else if (action.equals("reports")) {

		} else if (action.equals("follows")) {

		} else if (action.equals("unfollows")) {

		} else if (action.equals("updateinfo")) {
			try {
				String oldPswd = request.getParameter("oldpassword");
				String newPswd = request.getParameter("newpassword");
				String cNewPswd = request.getParameter("retypepassword");

				if (oldPswd != "" && newPswd != "" && cNewPswd != "") {
					if (oldPswd.equals(UserMapper
							.checkUserData("pswd", oldPswd))) {
						if (newPswd.equals(cNewPswd)) {
							UserMapper.updatePassword(newPswd, user);
						} else {
							throw new Exception(
									"New Password and Confirmation does not match");
						}
					} else {
						throw new Exception("Wrong Password");
					}
				}

				String oldEmail = request.getParameter("oldemail");
				String newEmail = request.getParameter("newemail");
				String cNewEmail = request.getParameter("retypemail");

				if (oldEmail != "" && newEmail != "" && cNewEmail != "") {
					if (oldEmail.equals(UserMapper.checkUserData("email",
							oldEmail))) {
						if (newEmail.equals(cNewEmail)) {
							UserMapper.updateEmail(newEmail, user);
						} else {
							throw new Exception(
									"New Email address and Confirmation does not match");

						}
					} else {
						throw new Exception(
								"Old Email address provided is wrong");
					}

				}

			} catch (Exception e) {
				request.setAttribute("error", e.getMessage());
				RequestDispatcher view = request
						.getRequestDispatcher("settings.jsp");
				view.forward(request, response);
				return;
			}

		} else if (action.equals("updatedetails")) {

		}
		response.sendRedirect(request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath() + "/Home");
		return;
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Action() {
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
		User user = null;
		String username = null;
		String password = null;
		if (session != null)
			user = (User) session.getAttribute("loggedUser");
		if (user == null) {
			if (session == null)
				session = request.getSession(true);
			Cookie[] cookies = request.getCookies();

			if (cookies.length > 0) {
				for (int i = 0; i < cookies.length; i++) {
					if (cookies[i].getName().equals("username"))
						username = cookies[i].getValue();
					if (cookies[i].getName().equals("password"))
						password = cookies[i].getValue();
				}
			}
			user = Credentials.validateUserCredentials(username, password);
		}

		if (user != null) {
			session.setAttribute("loggedUser", user);
			String action = request.getParameter("action");
			processRequest(request, response, action, user);
			return;
		}

		request.setAttribute("error", "You have to login first");
		request.getRequestDispatcher("login.jsp").forward(request, response);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = null;
		String username = null;
		String password = null;
		if (session != null)
			user = (User) session.getAttribute("loggedUser");
		if (user == null) {
			if (session == null)
				session = request.getSession(true);
			Cookie[] cookies = request.getCookies();

			if (cookies.length > 0) {
				for (int i = 0; i < cookies.length; i++) {
					if (cookies[i].getName().equals("username"))
						username = cookies[i].getValue();
					if (cookies[i].getName().equals("password"))
						password = cookies[i].getValue();
				}
			}
			user = Credentials.validateUserCredentials(username, password);
		}

		if (user != null) {
			session.setAttribute("loggedUser", user);
			String action = request.getParameter("action");
			processRequest(request, response, action, user);
			return;
		}

		request.setAttribute("error", "You have to login first");
		request.getRequestDispatcher("login.jsp").forward(request, response);
		return;
	}
}
