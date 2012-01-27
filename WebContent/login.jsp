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
		<span class="links">Welcome on FubMe</span>
	</div></div>
	<hr />
	<div class="box" style="margin-bottom:8em">
	

	<div id="error">
		<% 
                  			Object error = request.getAttribute("error");
                  			if(error != null && error instanceof String){
                  					String msg = "Error : ";
                  					msg += ((String)error);
                  					out.println(msg);
                  			}
                  			%>
	</div>
	<div class="sign" id="signup">
		<label class="blocktitle">Create a new Account</label><br />
		<form action="Signup" method="post" name="signup">

			<label>username</label> <input type="text" name="newusername" /> <label>password</label>
			<input type="password" name="newpassword" /> <label>confirm
				password</label><input type="password" name="retypepswd" /> <label>email</label>
			<input type="text" name="email" /> <label>confirm email</label> <input
				type="text" name="retypemail" /> <input type="submit"
				value="Sign up" name="signup" style="display: block; float: right;" />
		</form>
	</div>
	<div class="sign" id="signin">
		<label class="blocktitle">Sign in</label> <br />
		<form action="Home" method="post" name="signin">
			<label>username</label> <input type="text" name="username" /> <label>password</label>
			<input type="password" name="password" /> <input type="submit"
				value="Sign in" name="signin" style="display: block; float: right;" />
		</form>
	</div>

</div>
<hr />
<div class="footer" style="height:3em;">
	<div class="links"></div>
</div>
</body>
</html>
