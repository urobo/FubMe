<%@page import="java.util.List"%>
<%@page import="org.fubme.persistency.Helper"%>
<%@page import="org.fubme.models.*"%>
<jsp:include page="/header.jsp" />



<div class="section" id="canvas">

	<div id="instant">
		<%
			out.print("<form action=\" " + request.getScheme()
									+ "://" + request.getServerName() + ":"
									+ request.getServerPort()
									+ request.getContextPath() + "/Post");
			out.print("\" method=\"post\" >");
			out.print("<textarea id= \"status\" rows=\"2\" cols=\"103\" name=\"post_body\">What are you thinking? lol</textarea>");
     		out.print("<span id= \"tags\">tags: </span><input type=\"text\" name=\"tags\"/><span class=\"hint\"> hint: comma separated tags</span>");
     		out.print("<button class=\"rounded\" id= \"publish\" type = \"submit\" name= \"publish\"><span>post it!</span></button>");
     		out.print("</form>");
         %>
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
						out.print("<li><div class=\"post\"><div class=\"postimage\"><img src=\"pp1.jpg\" height=\"48px\" width=\"48px\" alt=\"profile picture\"/></div><div class=\"author\"><a href= \"" + request.getScheme()
									+ "://" + request.getServerName() + ":"
									+ request.getServerPort()
									+ request.getContextPath() + "/Profile");

							out.print("?user=" + post.getUser_id()
									+ "\" class=\"author\">"
									+ post.getUser_id() + "</a>");
						
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
						out.print("</div><div class=\"miscellanea\">");
								out.print("<div id=\"likelist\" class=\"author\">");
								
								List<User> likeList = Helper.getLikes(post);
								for (int j = 0; j< likeList.size(); j++){
								 	out.print("<span><a style=\"text-decoration:none\" href= \"" + request.getScheme()
										+ "://" + request.getServerName() + ":"
										+ request.getServerPort()
										+ request.getContextPath() + "/Profile");

									out.print("?user=" + likeList.get(j).getId()
										+ "\" class=\"author\">"										
										+ likeList.get(j).getId()+ "</a></span>");
									if(j!=likeList.size()-1)out.print(", ");
								}
								if(likeList.size()!=0)out.print(" liked this");
								out.print(" </div>");
								
								out.print("<a href=\""
								+ request.getScheme()
								+ "://"
								+ request.getServerName()
								+ ":"
								+ request.getServerPort()
								+ request.getContextPath()
								+ "/Action?action=");
								
								if (Helper.doesUserLikesPost(((User) request.getSession().getAttribute(
										"loggedUser")),post)){
										out.print("unlikes&post_id=");
										out.print(post.getId()
											+ "&user_id="
											+ ((User) request.getSession().getAttribute(
											"loggedUser")).getId()
											+ "\" class=\"linkbutton\">UnLike</a>");
										
								} else { out.print("likes&post_id=");		
									out.print(post.getId()
									+ "&user_id="
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
