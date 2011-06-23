package org.fubme.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.fubme.factories.PostFactory;
import org.fubme.models.Comment;
import org.fubme.models.User;
import org.fubme.persistency.mappings.CommentMapper;
import org.fubme.persistency.mappings.PostMapper;

/**
 * Servlet implementation class Action
 */
public class Action extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private void processRequest(HttpServletRequest request,
			HttpServletResponse response, String action)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (action.equals("comments")) {
			String post_id = request.getParameter("post_id");
			String user_id = request.getParameter("user_id");
			String body = request.getParameter("comment_text");
			CommentMapper.createCommentToPost(new Comment(post_id, user_id,
					body));
		} else if (action.equals("likes")) {
			String post_id = request.getParameter("post_id");
			User user = (User) session.getAttribute("loggedUser");
			PostMapper.likes(user, PostFactory.getPost(null,
					Integer.parseInt(post_id), null, null, null,
					org.fubme.models.Post.TEXT));
		} else if (action.equals("shares")) {

		} else if (action.equals("reports")) {

		} else if (action.equals("follows")) {

		} else if (action.equals("unfollows")) {

		}
		response.sendRedirect(request.getScheme() + "://" +
		        request.getServerName() + ":" + request.getServerPort() +
		        request.getContextPath()+ "/Home");
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
		if (session == null) {
			request.setAttribute("error", "You have to login first");
			request.getRequestDispatcher("login.jsp")
					.forward(request, response);
		} else {
			String action = request.getParameter("action");
			processRequest(request, response, action);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session == null) {
			request.setAttribute("error", "You have to login first");
			request.getRequestDispatcher("login.jsp")
					.forward(request, response);
		} else {
			String action = request.getParameter("action");
			processRequest(request, response, action);
		}
	}

}
