<%@page import="org.fubme.models.*"%>
<%@page import="java.util.List"%>
<%@page import="org.fubme.persistency.Helper"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.fubme.persistency.mappings.UserMapper"%>
<%@page import="java.util.Date"%>
<%@page import="java.net.URI"%>
<jsp:include page="/header.jsp" />


<div id="search">

	<form action="Search" method="post">
		<input value="Search" name="searchkeywords" type="text"
			style="font-family: 'Lucida Sans Unicode', 'Lucida Grande', Garuda, sans-serif;" />
		<input type="submit" name="searchbutton" value="Search"
			style="font-family: 'Lucida Sans Unicode', 'Lucida Grande', Garuda, sans-serif;" /><br />
		<input type="checkbox" name="people" value="people" checked="checked" />
		people &nbsp;&nbsp; <input type="checkbox"
			name="posts" value="postbody" checked="checked" /> posts'
		content &nbsp;&nbsp;
	</form>

</div>
<div class="searchresults">
	<div class="streamidentifier">
		<span>People</span>
	</div>
	<div class="stream">
		<ul>
			<%
				if (request.getAttribute("people") instanceof List<?>) {
					List<User> people = (List<User>) request.getAttribute("people");
					if (people.size() == 0) {
						out.print("<li><div class=\"post\">");
						out.print("<p class=\"noposts\">No Results Found To Be Shown<p>");
						out.print("</div></li>");
					} else {
						for (int i = 0; i < people.size(); i++) {

							String uImgTmp = UserMapper.getPathToImg(people.get(i));
							out.print("<li><div class=\"post\" style=\"min-height:6em\"><div style=\"float:left\"><img class=\"postimage\" src=\""
									+ request.getScheme()
									+ "://"
									+ request.getServerName()
									+ ":"
									+ request.getServerPort()
									+ request.getContextPath()
									+ "/uploads/"
									+ uImgTmp
									+ "\" height=\"48px\" width=\"48px\" alt=\"profile picture\"/><div class=\"author\"><a href= \""
									+ request.getScheme()
									+ "://"
									+ request.getServerName()
									+ ":"
									+ request.getServerPort()
									+ request.getContextPath() + "/Profile");

							out.print("?user=" + people.get(i).getId() + " \"> "+ people.get(i).getId()+" </a></div><br/><i>");
							if (people.get(i).getBio() != null)
								out.print(people.get(i).getBio());
							out.print("</i></div>");
							//FIXME img as link to action follow passing user as parameter
							if (!((User) request.getSession().getAttribute(
									"loggedUser")).getId().equals(
									people.get(i).getId())) {
								out.print("<div class=\"action\">");
								out.print("<a href=\"");
								boolean follow = false;
								if (UserMapper.isfollowing((User) request
										.getSession().getAttribute("loggedUser"),
										people.get(i))) {
									out.print(request.getScheme()
											+ "://"
											+ request.getServerName()
											+ ":"
											+ request.getServerPort()
											+ request.getContextPath()
											+ "/Action?action=unfollows&amp;unfollows="
											+ people.get(i).getId());
									follow = true;
								} else {
									out.print(request.getScheme() + "://"
											+ request.getServerName() + ":"
											+ request.getServerPort()
											+ request.getContextPath()
											+ "/Action?action=follows&amp;follows="
											+ people.get(i).getId());
								}
								out.print("\"><span class=\"actionsmall\">");
								if (follow)
									out.print("-");
								else
									out.print("+");
								out.print("</span></a></div></li>");
							}
						}
					}
				}
			%>
		</ul>
	</div>
	<div class="streamidentifier">
		<span>Posts</span>
	</div>
	<div class="stream">
		<ul>
			<%
				if (request.getAttribute("posts") instanceof List<?>) {
					List<Post> posts = (List<Post>) request.getAttribute("posts");
					if (posts.size() == 0) {
						out.print("<li><div class=\"post\">");
						out.print("<p class=\"noposts\">No Results Found To Be Shown<p>");
						out.print("</div></li>");
					} else {
						for (int i = 0; i < posts.size(); i++) {
							User user = (User) request.getSession().getAttribute(
									"loggedUser");
							String img = UserMapper.getPathToImg(user);
							Post post = posts.get(i);
							String imgTmp = UserMapper.getPathToImg(new User(post
									.getUser_id(), null));
							out.print("<li><div class=\"post\"><div class=\"postimage\"><img src=\""
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
</div>
<jsp:include page="/footer.jsp" />
