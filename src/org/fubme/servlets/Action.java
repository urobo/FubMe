package org.fubme.servlets;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.fubme.factories.PostFactory;
import org.fubme.factories.ReportFactory;
import org.fubme.helper.Credentials;
import org.fubme.models.Comment;
import org.fubme.models.Report;
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
			String post_id = request.getParameter("post_id");
			String wd_id = request.getParameter("wd_id");
			String wb_id = request.getParameter("wb_id");
			String category = request.getParameter("category");
			String reason = request.getParameter("reason");
			Report report = ReportFactory.getReport(Integer.parseInt(post_id),
					wb_id, wd_id, category, reason);
			PostMapper.reportPost(report);
		} else if (action.equals("follows")) {
			String follow = request.getParameter("follows");
			UserMapper.follows(user, new User(follow, null));
		} else if (action.equals("unfollows")) {
			String unfollow = request.getParameter("unfollows");
			UserMapper.unfollows(user, new User(unfollow, null));
		} else if (action.equals("logmeout")) {
			Cookie userNameCookie = new Cookie("username", null);
			Cookie passwordCookie = new Cookie("password", null);
			userNameCookie.setMaxAge(0);
			passwordCookie.setMaxAge(0);
			userNameCookie.setPath("/");
			passwordCookie.setPath("/");
			response.addCookie(userNameCookie);
			response.addCookie(passwordCookie);
			request.getSession().invalidate();
			request.getSession(false);
			RequestDispatcher view = request.getRequestDispatcher("login.jsp");
			view.forward(request, response);
			return;
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
			String firstname = request.getParameter("firstname");
			String lastname = request.getParameter("lastname");
			String birthdate = request.getParameter("birthdate");
			String bio = request.getParameter("bio");

			if (firstname != "") {
				UserMapper.updateFirstName(firstname, user);
			}

			if (lastname != "") {
				UserMapper.updateLastName(lastname, user);
			}

			if (birthdate != "") {
				try {
					UserMapper.updateBirthDate(birthdate, user);
				} catch (ParseException e) {
					request.setAttribute("error",
							"You provided a malformed date please follow this rule \"(yyyy/MM/dd)\"");
					RequestDispatcher view = request
							.getRequestDispatcher("settings.jsp");
					view.forward(request, response);
					return;
				}
			}

			if (bio != "") {
				UserMapper.updateBio(bio, user);
			}
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
