<%@page import="org.fubme.models.*"%>
<%@page import="java.util.List"%>
<%@page import="org.fubme.persistency.mappings.UserMapper"%>
<%@page import="org.fubme.persistency.Helper"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.net.URI"%>

<jsp:include page="/header.jsp" />
<div class="rightcolumn">
	<div id="bio">
		<img
			src="<%User user = new User(request.getParameter("user"), null);
			String img = UserMapper.getPathToImg(user);
			out.print(request.getScheme() + "://" + request.getServerName()
					+ ":" + request.getServerPort() + request.getContextPath()
					+ "/uploads/" + img);%>"
			height="80px" width="80px" alt="profile picture" />


		<%
			if (!((User) request.getSession().getAttribute("loggedUser"))
					.getId().equals(user.getId())) {
				out.print("<div class=\"action\">");
				out.print("<a href=\"");
				boolean follow = false;
				if (UserMapper.isfollowing((User) request.getSession()
						.getAttribute("loggedUser"), user)) {
					out.print(request.getScheme() + "://"
							+ request.getServerName() + ":"
							+ request.getServerPort()
							+ request.getContextPath()
							+ "/Action?action=unfollows&amp;unfollows="
							+ user.getId());
					follow = true;
				} else {
					out.print(request.getScheme() + "://"
							+ request.getServerName() + ":"
							+ request.getServerPort()
							+ request.getContextPath()
							+ "/Action?action=follows&amp;follows="
							+ user.getId());
				}
				out.print("\"><span class=\"action\">");
				if (follow)
					out.print("-");
				else
					out.print("+");
				out.print("</span></a></div>");
			}
		%>

		<p>
			<b> <%
 	out.print(((User) request.getAttribute("info")).getId());
 %>
			</b>
		</p>
		<p>
			<i
				style="font-family: 'Lucida Sans Unicode', 'Lucida Grande', Garuda, sans-serif; font-style: italic;">
				<%
					out.print(((User) request.getAttribute("info")).getBio());
				%>
			</i>
		</p>
	</div>
	<div class="contacts">
		<p>
			<b>Followers</b>
		</p>
		<p>
		<ul class="contacts">
			<%
				if (request.getAttribute("followers") instanceof List<?>) {
					List<User> followers = (List<User>) request
							.getAttribute("followers");
					for (int i = 0; i < followers.size(); i++) {
						out.print("<li class=\"contacts\"> <a class = \"author\" href=\""
								+ request.getScheme() + "://"
								+ request.getServerName() + ":"
								+ request.getServerPort()
								+ request.getContextPath() + "/Profile?user="
								+ followers.get(i).getId() + "\">"
								+ followers.get(i).getId() + "</a></li>");
					}
				}
			%>
		</ul>
		</p>

		<p>
			<b>Following</b>
		</p>
		<p>
		<ul class="contacts">
			<%
				if (request.getAttribute("following") instanceof List<?>) {
					List<User> following = (List<User>) request
							.getAttribute("following");
					for (int i = 0; i < following.size(); i++) {
						//String tmp = UserMapper.getPathToImg(following.get(i));

						out.print("<li class=\"contacts\"> <a class = \"author\" href=\""
								+ request.getScheme() + "://"
								+ request.getServerName() + ":"
								+ request.getServerPort()
								+ request.getContextPath() + "/Profile?user="
								+ following.get(i).getId() + "\">"
								+ following.get(i).getId() + "</a></li>");
					}
				}
			%>

		</ul>
		</p>
		</div>
	
	<div id="search">
		<b>Search</b>
		<form action="" method="get" >
			<p>
			
				<input class="searchicon" style="height:1.9em;" value="Search" name="searchtext" id="searchtext" type="text" />
				
				<input class="searchicon"  src="images/search.png" type="image" alt="Search"
					id="searchbutton" name="searchbutton" />
				
			</p>
		</form>

	</div>
</div>

<div class="section" id="canvas">

	<%
		if (((User) request.getSession().getAttribute("loggedUser"))
				.getId().equals(request.getParameter("user"))) {
			out.print("<div id=\"instant\">");
			out.print("<form action=\"" + request.getScheme() + "://"
					+ request.getServerName() + ":"
					+ request.getServerPort() + request.getContextPath()
					+ "/Post");
			out.print("\" method=\"post\" >");
			out.print("<textarea id= \"status\" rows=\"2\" cols=\"103\" name=\"post_body\">What are you thinking? lol</textarea>");
			out.print("<span id=\"tags\">tags: </span><input type=\"text\" name=\"tags\"/><span class=\"hint\"> hint: comma separated tags</span>");
			out.print("<button class=\"rounded\" id= \"publish\" type = \"submit\" name= \"publish\"><span>post it!</span></button>");
			out.print("</form>");
			out.print("</div>");
		}
	%>

	<div class="streamidentifier">
		<span>Posts</span>
	</div>

	<ul class="stream">
		<%
			if (request.getAttribute("posts") instanceof List<?>) {
				List<Post> posts = (List<Post>) request.getAttribute("posts");
				if (posts != null) {

					if (posts.size() == 0) {
						out.print("<div class=\"post\">");
						out.print("<p class=\"noposts\">No Posts To Show<p>");
						out.print("</div>");
					}
					for (int i = 0; i < posts.size(); i++) {
						Post post = posts.get(i);
						out.print("<li><div class=\"post\"><div class=\"postimage\"><img src=\""
								+ request.getScheme()
								+ "://"
								+ request.getServerName()
								+ ":"
								+ request.getServerPort()
								+ request.getContextPath()
								+ "/uploads/"
								+ img
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
						out.print("</div></li>");
					}
				}
			}
		%>

	</ul>

</div>
<jsp:include page="/footer.jsp" />
