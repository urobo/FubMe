package org.fubme.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fubme.factories.PostFactory;
import org.fubme.models.Tag;
import org.fubme.models.User;
import org.fubme.persistency.Helper;
import org.fubme.persistency.TimelineManager;
import org.fubme.persistency.mappings.PostMapper;

/**
 * Servlet implementation class Post
 */
public class Post extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession() == null) {
			request.getRequestDispatcher("login.jsp")
					.forward(request, response);
			return;
		} else {
			String post_id= request.getParameter("id");
			org.fubme.models.Post post = Helper.getPost(post_id,((User) request.getSession().getAttribute("loggedUser")));
			request.setAttribute("post", post);
			request.getRequestDispatcher("post.jsp").forward(request, response);
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession() == null) {
			request.getRequestDispatcher("login.jsp")
					.forward(request, response);
			return;
		} else {
			String post_body = request.getParameter("post_body");
			User user = ((User) request
					.getSession().getAttribute("loggedUser"));
			org.fubme.models.Post post = PostFactory.getPost(user.getId(),post_body, null, org.fubme.models.Post.TEXT);
			String tmpTags = request.getParameter("tags");
			if (tmpTags != null){
				String[] tags = tmpTags.split(",");
				List<Tag> taglist = new ArrayList<Tag>();
				for (int i = 0 ; i < tags.length ; i++){
					taglist.add(new Tag(tags[i].trim()));
				}
				post.setTags(taglist);
			}
			PostMapper.createPost(post);
			
			List<org.fubme.models.Post> timeline = TimelineManager
					.getTimelineForUser(user, Home.maxPosts);
			request.setAttribute("timeline", timeline);
			RequestDispatcher view = request.getRequestDispatcher("home.jsp");
			view.forward(request, response);
			return;
			
		}
	}

}
