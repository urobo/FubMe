package org.fubme.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.fubme.helper.Credentials;
import org.fubme.models.User;
import org.fubme.persistency.mappings.UserMapper;

/**
 * Servlet implementation class Settings
 */
public class Settings extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Settings() {
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

			String pathToImg = UserMapper.getPathToImg(user);
			request.setAttribute("img", pathToImg);
			request.getRequestDispatcher("settings.jsp").forward(request,
					response);
			return;

		}
		request.getRequestDispatcher("login.jsp").forward(request, response);
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
