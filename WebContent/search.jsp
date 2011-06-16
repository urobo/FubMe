<%@page import="org.fubme.models.*"%>
<%@page import="java.util.List"%>
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
						out.print("<li><div class=\"post\"><div class=\"postimage\"><img src=\"pp1.jpg\" height=\"48px\" width=\"48px\" alt=\"profile picture\"/></div><div class=\"author\">"
								+ post.getUser_id()
								+ "</div><div class=\"posttext\">"
								+ post.getBody()
								+ "<div class= \"tag\">");
						if (post.getTags() instanceof List<?>)
							for (int j = 0; j < post.getTags().size(); j++) {
								out.print("<a href = \"http://127.0.0.1:8080/FubMe/Search?tags="
										+ post.getTags().get(j).getName()
										+ "\"> #"
										+ post.getTags().get(j).getName() + "</a>");
							}
						out.print("</div><hr/> <div class=\"miscellanea\"><button name=\"comment\" type=\"submit\" class=\"linkbutton\">Comment</button><button name=\"like\" type=\"submit\" class=\"linkbutton\">Like</button><button name=\"reblog\" type=\"submit\" class=\"linkbutton\">ReBlog</button></div>");
						if (post.getComments() instanceof List<?>)
							for (int j = 0; j < post.getComments().size(); j++) {
								out.print("<div class = \"comment\"><div class=\"author\">"
										+ post.getComments().get(j).getLuser_id()
										+ "</div>"
										+ post.getComments().get(j).getBody()
										+ "</div>");
							}
						out.print("</div></li>");
					}
				}
			%>


		</ul>
	</div>
</div>
<jsp:include page="/footer.jsp" />
