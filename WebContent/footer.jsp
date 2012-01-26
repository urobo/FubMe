<%@page import="org.fubme.models.*"%>

<div class="footer">
	<hr />

	<div class="links">
		<%
									out.print("<a href= \""+ request.getScheme() +"://"+  request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/Profile");                
                    				if (session.getAttribute("loggedUser") != null)
                                	  out.print("?user="+((User)session.getAttribute("loggedUser")).getId());
                    				out.print("\">");
                                  %>

			<span class="links" >Profile</span> </a>

			<% out.print("<a href= \""+ request.getScheme() +"://"+  request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/Home\">"); %>

			<span class="links" >Home</span> </a>

			<% out.print("<a href= \""+ request.getScheme() +"://"+  request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/Search\">"); %>

			<span class="links" >Settings</span> </a>

			<% out.print("<a href= \""+ request.getScheme() +"://"+  request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/Settings\">"); %>

			<span class="links" >Search</span> </a>
	</div>
</div>
</body>
</html>