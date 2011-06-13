<%@page import="org.fubme.models.Post"%>
<% 
	
	Post post = (Post)request.getAttribute("post");
	
%>

<div class="post">
           <div class="postimage">
           <img src="pp1.jpg" height="48px" width="48px" alt="profile picture"/>
           </div>
           <div class="posttext">
                <%= post.getBody() %>
                <div class= "tag">
				<% for (int i = 0; i < post.getTags().size() ; i++) 
				out.println("<a href=\"\">#" + post.getTags().get(i).getName()+"</a>");
                %>
                
                </div>
                <hr/>
                <div class="miscellanea">
                        <button name="comment" type="submit" class="linkbutton">Comment</button>
                        <button name="like" type="submit" class="linkbutton">Like</button>
                        <button name="reblog" type="submit" class="linkbutton">ReBlog</button>
                </div>
              	<% for (int i =0; i< post.getComments().size(); i++)
              		out.println("<div class = \"comment\"> "+post.getComments().get(i).getLuser_id()+"<p>"+ post.getComments().get(i).getBody()+"</p></div>");
              		%>
          </div>
        </div>