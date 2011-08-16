<%@page import="java.util.List"%>
<%@page import="org.fubme.models.*"%>
<jsp:include page="/header.jsp" />



<div class="section" id="canvas">

	<div id="instant">


		<textarea id="status" rows="1" cols="105">
                         What are you thinking?
                </textarea>

		<button class="rounded" id="publish" type="submit" name="publish">
			<span>post it!</span>
		</button>
	</div>

	<div class="rounded" id="timeline">
		<span>Timeline</span>
	</div>

	<ul>
		<%
			if (request.getAttribute("timeline") instanceof List<?>) {
				List<Post> timeline = (List<Post>) request
						.getAttribute("timeline");
				if (timeline != null) {
					for (int i = 0; i < timeline.size(); i++) {
						Post post = timeline.get(i);
						out.print("<li><div class=\"post\"><div class=\"postimage\"><img src=\"pp1.jpg\" height=\"48px\" width=\"48px\" alt=\"profile picture\"/></div><div class=\"author\">"
								+ post.getUser_id());
						if (post.getVia_user_id() != null) {
							out.print(" via <a href= \"" + request.getScheme()
									+ "://" + request.getServerName() + ":"
									+ request.getServerPort()
									+ request.getContextPath() + "/Profile");

							out.print("?user=" + post.getVia_user_id()
									+ "\" class=\"author\">"
									+ post.getVia_user_id() + "</a>");
						}
						out.print("</div><div class=\"posttext\">"
								+ post.getBody() + "<div class= \"tag\">");
						if (post.getTags() instanceof List<?>)
							for (int j = 0; j < post.getTags().size(); j++) {
								out.print("<a href= \"" + request.getScheme()
										+ "://" + request.getServerName() + ":"
										+ request.getServerPort()
										+ request.getContextPath()
										+ "/Search?tags="
										+ post.getTags().get(j).getName()
										+ "\"> #"
										+ post.getTags().get(j).getName()
										+ "</a>");
							}
						out.print("</div><div class=\"miscellanea\"><a href=\""
								+ request.getScheme()
								+ "://"
								+ request.getServerName()
								+ ":"
								+ request.getServerPort()
								+ request.getContextPath()
								+ "/Action?action=likes&post_id="
								+ post.getId()
								+ "&user_id="
								+ ((User) request.getSession().getAttribute(
										"loggedUser")).getId()
								+ "\" class=\"linkbutton\">Like</a>");
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
										+ post.getComments().get(j)
												.getLuser_id()
										+ "\" class=\"author\">"
										+ post.getComments().get(j)
												.getLuser_id() + "</a>"
										+ "</div>"
										+ post.getComments().get(j).getBody()
										+ "</div>");
							}
						out.print("<div class=\"miscellanea\"> <form name=\"actionComment\" action=\"Action?action=comments&user_id="
								+ ((User) request.getSession().getAttribute(
										"loggedUser")).getId()
								+ "&post_id="
								+ post.getId()
								+ "\" method=\"post\"><textarea name=\"comment_text\" rows=\"1\" cols=\"60\"></textarea><button name=\"comment\" type=\"submit\" class=\"linkbutton\">Comment</button></form></div>");

						out.print("</div></li>");
					}
				}
			}
		%>

	</ul>

</div>

<jsp:include page="/footer.jsp" />
