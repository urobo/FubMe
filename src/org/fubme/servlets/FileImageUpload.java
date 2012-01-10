package org.fubme.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.DiskFileUpload;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.fubme.helper.Credentials;
import org.fubme.models.User;

/**
 * Servlet implementation class FileImageUpload
 */
public class FileImageUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FileImageUpload() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
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

				FileUpload fup = new FileUpload();
				boolean isMultipart = FileUpload.isMultipartContent(request);
				if (isMultipart) {
					DiskFileUpload upload = new DiskFileUpload();
					// Parse the request
					List items = upload.parseRequest(request);
					Iterator iter = items.iterator();
					while (iter.hasNext()) {
						FileItem item = (FileItem) iter.next();
						InputStream in = item.getInputStream();
						int len = request.getContentLength();

						byte[] dataBytes = new byte[len];
						int index = in.read(dataBytes, 0, len);

						response.setContentType("image/jpg");
						response.setHeader("Content-Disposition",
								"attachment; filename=\"image.jpg\"");

						response.getOutputStream().write(dataBytes, 0, len);
						response.getOutputStream().flush();

						return;
					}
				}
				request.setAttribute("error", "You have to login first");
				request.getRequestDispatcher("login.jsp").forward(request,
						response);
				return;

			}
		} catch (Exception e) {

		}

	}

}
