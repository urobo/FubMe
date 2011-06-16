package org.fubme.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.fubme.models.User;
import org.fubme.persistency.mappings.UserMapper;

/**
 * Servlet implementation class Signup
 */
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
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
	 * @see HttpServlet#HttpServlet()
	 */
	public Signup() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession() == null){
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("newusername");
		String password = request.getParameter("newpassword");
		String passwordConfirmation = request.getParameter("retypepswd");
		String email = request.getParameter("email");
		String emailConfirmation = request.getParameter("retypemail");
		RequestDispatcher view = null;
		String msg = "Sorry ";
		boolean error = false;
		if (password.equals(passwordConfirmation)
				&& email.equals(emailConfirmation))
			if (!UserMapper.checkUserData("id", username)) {
				if (!UserMapper.checkUserData("email", email)) {
					User user = new User(username, password, email);
					UserMapper.createUser(user);
					HttpSession session = request.getSession(true);
					setLoginCookies(request,response,username,password);
					session.setAttribute("loggedUser", user );
					view = request.getRequestDispatcher("home.jsp");
					view.forward(request, response);
				} else {
					msg += "the email provided already exists ";
					error = true;
				}
			} else {
				msg += "the username provided already exists";
				error = true;
			}
		if (error) {
			view = request.getRequestDispatcher("login.jsp");
			request.setAttribute("error", msg);
			view.forward(request, response);
		}
	}

}
