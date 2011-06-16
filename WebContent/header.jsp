<%@page import="org.fubme.models.*"%>

<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
        <title>Login on FubMe</title>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
        <link rel="stylesheet" type="text/css" href="fubme.css"/>
        <link rel="icon" href="images/fubme.png" type="image/png" />

</head>

        <body>
                 <div class="header">  
                         <div class="title"> <h1>welcome on fubme</h1></div>
                           <div class= "links">
              
                                  <%
									out.print("<a href= \""+ request.getScheme() +"://"+  request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/Profile");                
                    				if (session.getAttribute("loggedUser") != null)
                                	  out.print("?user="+((User)session.getAttribute("loggedUser")).getId());
                    				out.print("\">");
                                  %>

                                        <img src="images/profile.png" alt="profile"/>
                                  </a>
                                 
                                 
                                  <% out.print("<a href= \""+ request.getScheme() +"://"+  request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/Home\">"); %>
                             
                                          <img src="images/home.png" alt="home"/>
                                  </a>
                                  
                                  <% out.print("<a href= \""+ request.getScheme() +"://"+  request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/Search\">"); %>

                                          <img src="images/search.png" alt="search"/>
                                  </a>
                                  
                                  <% out.print("<a href= \""+ request.getScheme() +"://"+  request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/Settings\">"); %>

                                          <img src="images/settings.png" alt="settings"/>
                                  </a>
                            </div>
									                            
                        </div>
                  <hr/>