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
		HttpSession session = request.getSession();
		if (session!=null && session.getAttribute("loggedUser")!=null){
			user = (User) session.getAttribute("loggedUser");
			List<User> followers = UserMapper.getFollowers(user);
			List<User> following = UserMapper.getFollowing(user);
			List<org.fubme.models.Post> posts = TimelineManager.getProfileForUser(user, 25); 
			User info = UserMapper.getUserInfo(user);
			request.setAttribute("followers", followers);
			request.setAttribute("following", following);
			request.setAttribute("posts", posts);
			request.setAttribute("info", info);
			request.getRequestDispatcher("profile.jsp").forward(request,
					response);
			return;
		} else {
			Cookie[] cookies = request.getCookies();
			if (cookies.length>0){
				String username = null;
				for (int i = 0 ; i < cookies.length ; i++)
					if (cookies[i].getName().equals("username")) username = cookies[i].getValue();
				user = new User(username,null);
				request.getSession(true);
				session.setAttribute("loggedUser", user);
				List<User> followers = UserMapper.getFollowers(user);
				List<User> following = UserMapper.getFollowing(user);
				List<org.fubme.models.Post> posts = TimelineManager.getProfileForUser(user, 25); 
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
