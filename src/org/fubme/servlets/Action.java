package org.fubme.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Action
 */
public class Action extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response,String action){
		if(action == "comments"){
			
		}else if (action == "likes"){
			
		}else if (action == "shares"){
			
		}else if (action == "reports"){
			
		}else if (action == "follows"){
			
		}else if (action == "unfollows"){
			
		}
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Action() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session == null){
			request.setAttribute("error", "You have to login first");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}else{
			String action = request.getParameter("action");
			processRequest(request, response, action);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
