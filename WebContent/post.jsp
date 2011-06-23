<%@page import="java.util.List"%>
<%@page import="org.fubme.models.*"%>
<jsp:include page="/header.jsp" />

<div class="section" id="canvas">


	<div class="rounded" id="timeline">
		<span>Post</span>
	</div>
	<%
		Post post = (Post) request.getAttribute("post");
		out.print("<div class=\"post\"><div class=\"postimage\"><img src=\"pp1.jpg\" height=\"48px\" width=\"48px\" alt=\"profile picture\"/></div><div class=\"author\">"
				+ post.getUser_id());
		if (post.getVia_user_id() != null) {
			out.print(" via <a href= \"" + request.getScheme() + "://"
					+ request.getServerName() + ":"
					+ request.getServerPort() + request.getContextPath()
					+ "/Profile");

			out.print("?user=" + post.getVia_user_id()
					+ "\" class=\"author\">" + post.getVia_user_id()
					+ "</a>");
		}
		out.print("</div><div class=\"posttext\">" + post.getBody()
				+ "<div class= \"tag\">");
		if (post.getTags() instanceof List<?>)
			for (int j = 0; j < post.getTags().size(); j++) {
				out.print("<a href= \"" + request.getScheme() + "://"
						+ request.getServerName() + ":"
						+ request.getServerPort()
						+ request.getContextPath() + "/Search?tags="
						+ post.getTags().get(j).getName() + "\"> #"
						+ post.getTags().get(j).getName() + "</a>");
			}
		out.print("</div><hr/> <div class=\"miscellanea\"><button name=\"comment\" type=\"submit\" class=\"linkbutton\">Comment</button><button name=\"like\" type=\"submit\" class=\"linkbutton\">Like</button><button name=\"reblog\" type=\"submit\" class=\"linkbutton\">ReBlog</button></div>");
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
		out.print("</div></div>");
	%>

</div>

<jsp:include page="/footer.jsp" />
