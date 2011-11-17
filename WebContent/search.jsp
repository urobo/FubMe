<%@page import="org.fubme.models.*"%>
<%@page import="java.util.List"%>
<%@page import="org.fubme.persistency.Helper"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.net.URI"%>
<jsp:include page="/header.jsp" />


<div id="search">

	<form action="Search" method="post">
		<input value="Search" name="searchtext" type="text"
			style="font-family: Gotham Book, Gotham, sans-serif;" /> <input
			type="submit" name="searchbutton" value="Search"
			style="font-family: Gotham Book, Gotham, sans-serif;" /><br /> <input
			type="checkbox" name="people" value="people" checked="checked" />
		people &nbsp;&nbsp; <input type="checkbox" name="tags" value="bytags"
			checked="checked" /> by tags &nbsp;&nbsp; <input type="checkbox"
			name="posts" value="posted_content" checked="checked" /> posts'
		content &nbsp;&nbsp;
	</form>

</div>
<div class="searchresults">
	<div class="rounded" id="peoplelist">
		<span>People</span>
	</div>
	<div class="peopleresults">
		<ul>
			<%
				if (request.getAttribute("people") instanceof List<?>) {
					List<User> people = (List<User>) request.getAttribute("people");
					for (int i = 0; i < people.size(); i++) {
						out.print("<li><div class=\"people\"><img class=\"postimage\" src=\"pp1.jpg\" height=\"48px\" width=\"48px\" alt=\"profile picture\"/><div class=\"author\">"
								+ people.get(i).getId() + "</div><br/><i>");
						if (people.get(i).getBio() != null)
							out.print(people.get(i).getBio());
						out.print("</i><div class=\"misc_buttons\">");
						out.print("<button class=\"rounded\" id=\"action_follow_urobo\" type = \"submit\" name= \"action_follow_urobo\"><span>+</span></button></div></div></li>");
					}
				}
			%>
		</ul>
	</div>
	<div class="rounded" id="posts">
		<span>Posts</span>
	</div>
	<div class="postsresults">
		<ul>
			<%
				if (request.getAttribute("posts") instanceof List<?>) {
					List<Post> posts = (List<Post>) request.getAttribute("posts");
					for (int i = 0; i < posts.size(); i++) {
						Post post = posts.get(i);
						out.print("<li><div class=\"post\"><div class=\"postimage\"><img src=\"pp1.jpg\" height=\"48px\" width=\"48px\" alt=\"profile picture\"/></div><div class=\"author\"><a href= \""
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
							URI uri = new URI(request.getScheme(),
							           null,
							           request.getServerName(),
							           request.getServerPort(),
							           request.getContextPath()+ "/Profile",
							           "user=" +likeList.get(j).getId(),
							           null); 
								

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
						out.print("</div></li>");
					}
				}
			%>


		</ul>
	</div>
</div>
<jsp:include page="/footer.jsp" />
