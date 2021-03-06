<%@page import="java.util.List"%>
<%@page import="org.fubme.models.*"%>
<%@page import="org.fubme.persistency.Helper"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.net.URI"%>

<jsp:include page="/header.jsp" />

<div class="section" id="canvas">


	<div class="streamidentifier">
		<span>Post</span>
	</div>
	<div class="stream">
		<%
			Post post = (Post) request.getAttribute("post");

			out.print("<div class=\"post\"><div class=\"postimage\"><img src=\"pp1.jpg\" height=\"48px\" width=\"48px\" alt=\"profile picture\"/></div><div class=\"author\"><a href= \""
					+ request.getScheme()
					+ "://"
					+ request.getServerName()
					+ ":"
					+ request.getServerPort()
					+ request.getContextPath()
					+ "/Profile");

			out.print("?user=" + post.getUser_id() + "\" class=\"author\">"
					+ post.getUser_id() + "</a>");

			out.print("</div><div class=\"posttext\">" + post.getBody()
					+ "<div class= \"tag\">");
			if (post.getTags() instanceof List<?>)
				for (int j = 0; j < post.getTags().size(); j++) {
					//FIXME
					out.print("<a href= \"" + request.getScheme() + "://"
							+ request.getServerName() + ":"
							+ request.getServerPort()
							+ request.getContextPath() + "/Search?tags="
							+ post.getTags().get(j).getName() + "\"> #"
							+ post.getTags().get(j).getName() + "</a>");
				}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

			out.print("</div><div class=\"miscellanea\">");

			out.print("<div class=\"likelist\">");

			List<User> likeList = Helper.getLikes(post);
			for (int j = 0; j < likeList.size(); j++) {
				out.print("<span><a style=\"text-decoration:none\" href= \"");
				URI uri = new URI(request.getScheme(), null,
						request.getServerName(), request.getServerPort(),
						request.getContextPath() + "/Profile", "user="
								+ likeList.get(j).getId(), null);

				out.print(uri.toASCIIString());
				out.print("\" class=\"author\">" + likeList.get(j).getId());
				out.print("</a></span>");
				if (j != likeList.size() - 1)
					out.print(", ");
			}

			if (likeList.size() != 0)
				out.print(" <span class=\"author\">liked this</span>");
			out.print(" </div>");

			out.print("<a href=\"" + request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath() + "/Action?action=");

			if (Helper.doesUserLikesPost(((User) request.getSession()
					.getAttribute("loggedUser")), post)) {
				out.print("unlikes&amp;post_id=");
				out.print(post.getId()
						+ "&amp;user_id="
						+ ((User) request.getSession().getAttribute(
								"loggedUser")).getId()
						+ "\" class=\"linkbutton\">UnLike</a>");

			} else {
				out.print("likes&amp;post_id=");
				out.print(post.getId()
						+ "&amp;user_id="
						+ ((User) request.getSession().getAttribute(
								"loggedUser")).getId()
						+ "\" class=\"linkbutton\">Like</a>");
			}
			out.print("</div>");
			if (post.getComments() instanceof List<?>)
				for (int j = 0; j < post.getComments().size(); j++) {
					out.print("<div class = \"comment\"><div class=\"author\"><a href= \""
							+ request.getScheme()
							+ "://"
							+ request.getServerName()
							+ ":"
							+ request.getServerPort()
							+ request.getContextPath() + "/Profile");

					out.print("?user="
							+ post.getComments().get(j).getLuser_id()
							+ "\" class=\"author\">"
							+ post.getComments().get(j).getLuser_id() + "</a>"
							+ "</div>" + post.getComments().get(j).getBody()
							+ "</div>");
				}
			out.print("<div class=\"miscellanea\"> <form name=\"actionComment\" action=\"Action?action=comments&amp;user_id="
					+ ((User) request.getSession().getAttribute("loggedUser"))
							.getId()
					+ "&amp;post_id="
					+ post.getId()
					+ "\" method=\"post\"><textarea name=\"comment_text\" rows=\"1\" cols=\"60\"></textarea><button name=\"comment\" type=\"submit\" class=\"linkbutton\">Comment</button></form></div>");

			out.print("<div class=\"postidentifier\">");
			out.print("<span><a style=\"text-decoration:none;color:#d3c8cb\" href= \""
					+ request.getScheme()
					+ "://"
					+ request.getServerName()
					+ ":"
					+ request.getServerPort()
					+ request.getContextPath()
					+ "/Post?id=" + post.getId() + "\">");

			Date date = new Date(post.getPtime().getTime());
			out.print("Posted " + sdf.format(date));

			out.print("</a></span></div>");
			out.print("</div>");

			if (!((User) request.getSession().getAttribute("loggedUser"))
					.getId().equals(post.getUser_id())) {
				out.print("<div class=\"report\"><span class=\"report\">Report</span>");
				out.print("<form action=\""
						+ request.getScheme()
						+ "://"
						+ request.getServerName()
						+ ":"
						+ request.getServerPort()
						+ request.getContextPath()
						+ "/Action?action=reports&amp;post_id="
						+ post.getId()
						+ "&amp;wd_id="
						+ post.getUser_id()
						+ "&amp;wb_id="
						+ ((User) request.getSession().getAttribute(
								"loggedUser")).getId());
				out.print("\" method=\"post\" >");
				out.print("	<p class=\"reportblock\"><span class=\"reportblock\">Category :</span><br/><input type=\"radio\" name=\"category\" value=\"offensive\">Offensive</input><br/><input type=\"radio\" name=\"category\" value=\"spam\">Spam</input><br/><input type=\"radio\" name=\"category\" value=\"racist\">Racist</input><br/></p><p class=\"reportblock\" style=\"margin-left:35%\"><span class=\"reportblock\">Reason :</span><br/><textarea name=\"reason\" cols=\"50\" rows=\"6\" style=\"width:60%\"></textarea></p><input type=\"submit\" value=\"Submit\"/></form></div>");
			}
		%>
	</div>
</div>
</div>

<jsp:include page="/footer.jsp" />
