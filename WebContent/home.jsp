<%@page import="java.util.List"%>
<%@page import="org.fubme.models.*"%>
<jsp:include page="/header.jsp" />

       
        
        <div class="section" id="canvas">
        
        <div id = "instant">
        
        
                <textarea id= "status" rows="4" cols="105">
                         What are you thinking?
                </textarea>
       
                <button class="rounded" id= "publish" type = "submit" name= "publish"><span>post it!</span></button>
        </div>
        
        <div class="rounded" id="timeline">
        <span>Timeline</span>
        </div>

        <ul>
 		<% 
 			if (request.getAttribute("timeline") instanceof List<?>){
 				List<Post> timeline = (List<Post>) request.getAttribute("timeline");
			if(timeline != null){
				for (int i = 0; i < timeline.size(); i++){
					Post post = timeline.get(i);
					out.print("<li><div class=\"post\"><div class=\"postimage\"><img src=\"pp1.jpg\" height=\"48px\" width=\"48px\" alt=\"profile picture\"/></div><div class=\"posttext\">"+post.getBody()+"<div class= \"tag\">");
					if(post.getTags() instanceof List<?>)
					for(int j = 0; j< post.getTags().size(); j++){
					out.print("<a href = \"\" #"+ post.getTags().get(j).getName() +"</a>");	
					}	
			    	out.print("</div><hr/> <div class=\"miscellanea\"><button name=\"comment\" type=\"submit\" class=\"linkbutton\">Comment</button><button name=\"like\" type=\"submit\" class=\"linkbutton\">Like</button><button name=\"reblog\" type=\"submit\" class=\"linkbutton\">ReBlog</button></div>");           
			    	if (post.getComments() instanceof List<?>)
			    	for (int j = 0 ; j<post.getComments().size(); j++){          
			    	     out.print("<div class = \"comment\"><p>"+post.getComments().get(j).getBody()+"</p></div>");
			    	}
			  	 	 out.print("</div></li>");
					}
 				}
 			}
       	%>
                       
        </ul>

        </div>
        
<jsp:include page="/footer.jsp" />  