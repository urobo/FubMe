<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@page import="org.fubme.models.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title>Login on FubMe</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<link rel="stylesheet" type="text/css" href="fubme.css" />
<link rel="icon" href="images/fubme.png" type="image/png" />

</head>

<body>
	<div class="header">
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

			<span class="links" >Search</span> </a>

			<% out.print("<a href= \""+ request.getScheme() +"://"+  request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/Settings\">"); %>

			<span class="links" >Settings</span> </a>
			
			<% out.print("<a href= \""+ request.getScheme() +"://"+  request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/Action?action=logmeout\">"); %>

			<span class="links" >Log out</span> </a>
		</div>

	</div>
	<hr />