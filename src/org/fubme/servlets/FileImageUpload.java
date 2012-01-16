package org.fubme.servlets;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.fubme.helper.Credentials;
import org.fubme.models.User;
import org.fubme.persistency.mappings.UserMapper;

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
				boolean isMultipart = ServletFileUpload
						.isMultipartContent(request);
				if (isMultipart) {
					FileItemFactory factory = new DiskFileItemFactory();
					ServletFileUpload upload = new ServletFileUpload(factory);
					List items = upload.parseRequest(request);
					Iterator iterator = items.iterator();
					while (iterator.hasNext()) {
						FileItem item = (FileItem) iterator.next();
						if (!item.isFormField()) {
							String fileName = item.getName();
							String root = getServletContext().getRealPath("/");
							File path = new File(root + "/uploads");
							if (!path.exists()) {
								boolean status = path.mkdirs();
							}
							String ext = fileName.substring(fileName.lastIndexOf(".")-1);
							File uploadedFile = File.createTempFile(user.getId()+"-", ext, new File(path.getAbsolutePath()+"/"));
							
							String pathToImg = uploadedFile.getAbsolutePath();
							item.write(uploadedFile);
							UserMapper.updateImg(uploadedFile.getName(), user);
							request.setAttribute("img", uploadedFile.getName());
							RequestDispatcher view = request.getRequestDispatcher("settings.jsp");
							view.forward(request, response);
							return;
						}
					}
				}

			}
			request.setAttribute("error", "You have to login first");
			request.getRequestDispatcher("login.jsp")
					.forward(request, response);
			return;

		} catch (FileUploadException e) {

			e.printStackTrace();

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}
