<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.net.URI"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="org.fubme.persistency.mappings.UserMapper"%>
<%@page import="org.fubme.persistency.Helper"%>
<%@page import="org.fubme.models.*"%>

<jsp:include page="/header.jsp" />



<div class="section" id="canvas">

	<div id="instant">
		<%
			//FIXME
			out.print("<form action=\" " + request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath() + "/Post");
			out.print("\" method=\"post\" >");
			out.print("<textarea id= \"status\" rows=\"2\" cols=\"103\" name=\"post_body\">What are you thinking? lol</textarea>");
			out.print("<span id=\"tags\">tags: </span><input type=\"text\" name=\"tags\"/><span class=\"hint\"> hint: comma separated tags</span>");
			out.print("<button class=\"rounded\" id= \"publish\" type = \"submit\" name= \"publish\"><span>post it!</span></button>");
			out.print("</form>");
		%>
	</div>

	<div class="streamidentifier"><span>Timeline</span></div>

	<ul class="stream">
		<%
			if (request.getAttribute("timeline") instanceof List<?>) {
				List<Post> timeline = (List<Post>) request
						.getAttribute("timeline");
				if (timeline != null) {
					User user = (User) request.getSession().getAttribute(
							"loggedUser");
					String img = UserMapper.getPathToImg(user);
					
					if (timeline.size() == 0) {
						out.print("<div class=\"post\">");
						out.print("<p class=\"noposts\">No Posts To Show<p>");
						out.print("</div>");
					}
					User userTmp = new User(null,null);
					for (int i = 0; i < timeline.size(); i++) {
						Post post = timeline.get(i);
						userTmp.setId(post.getUser_id());
						String imgTmp = UserMapper.getPathToImg(userTmp); 
						//FIXME
						out.print("<li><div class=\"post\">");
						if(!((User)request.getSession().getAttribute("loggedUser")).getId().equals(post.getUser_id())){
							out.print("<a href=\"");
						
							out.print(request.getScheme()
								+ "://"
								+ request.getServerName()
								+ ":"
								+ request.getServerPort()
								+ request.getContextPath()
								+ "/Post?id="
								+ post.getId());
							out.print("\" style=\"float:right\"><span class=\"report\" style=\"font-wright:italic;\">");
							out.print("report</span></a>");
						}
						out.print("<div class=\"postimage\"><img src=\""
								+ request.getScheme()
								+ "://"
								+ request.getServerName()
								+ ":"
								+ request.getServerPort()
								+ request.getContextPath()
								+ "/uploads/"
								+ imgTmp
								+ "\" height=\"48px\" width=\"48px\" alt=\"profile picture\"/></div><div class=\"author\"><a href= \""
								+ request.getScheme()
								+ "://"
								+ request.getServerName()
								+ ":"
								+ request.getServerPort()
								+ request.getContextPath() + "/Profile");

						out.print("?user=" + post.getUser_id()
								+ "\" class=\"author\">" + post.getUser_id()
								+ "</a>");

						out.print("</div><div class=\"posttext\">"
								+ post.getBody() + "<div class= \"tag\">");
						if (post.getTags() instanceof List<?>)
							for (int j = 0; j < post.getTags().size(); j++) {
								//FIXME
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
						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy-MM-dd hh:mm:ss");

						out.print("</div><div class=\"miscellanea\">");

						out.print("<div class=\"likelist\">");

						List<User> likeList = Helper.getLikes(post);
						for (int j = 0; j < likeList.size(); j++) {
							out.print("<span><a style=\"text-decoration:none\" href= \"");
							URI uri = new URI(request.getScheme(), null,
									request.getServerName(),
									request.getServerPort(),
									request.getContextPath() + "/Profile",
									"user=" + likeList.get(j).getId(), null);

							out.print(uri.toASCIIString());
							out.print("\" class=\"author\">"
									+ likeList.get(j).getId());
							out.print("</a></span>");
							if (j != likeList.size() - 1)
								out.print(", ");
						}

						if (likeList.size() != 0)
							out.print(" <span class=\"author\">liked this</span>");
						out.print(" </div>");

						out.print("<a href=\"" + request.getScheme() + "://"
								+ request.getServerName() + ":"
								+ request.getServerPort()
								+ request.getContextPath() + "/Action?action=");

						if (Helper
								.doesUserLikesPost(((User) request.getSession()
										.getAttribute("loggedUser")), post)) {
							out.print("unlikes&amp;post_id=");
							out.print(post.getId()
									+ "&amp;user_id="
									+ ((User) request.getSession()
											.getAttribute("loggedUser"))
											.getId()
									+ "\" class=\"linkbutton\">UnLike</a>");

						} else {
							out.print("likes&amp;post_id=");
							out.print(post.getId()
									+ "&amp;user_id="
									+ ((User) request.getSession()
											.getAttribute("loggedUser"))
											.getId()
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
						out.print("<div class=\"miscellanea\"> <form name=\"actionComment\" action=\"Action?action=comments&amp;user_id="
								+ ((User) request.getSession().getAttribute(
										"loggedUser")).getId()
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
								+ "/Post?id="
								+ post.getId() + "\">");

						Date date = new Date(post.getPtime().getTime());
						out.print("Posted " + sdf.format(date));

						out.print("</a></span></div>");
						out.print("</div>");
						out.print("</div></li>");
					}
				}
			}
		%>

	</ul>

</div>

<jsp:include page="/footer.jsp" />
