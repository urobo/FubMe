<jsp:include page="/header.jsp" />
                  <div class="box">
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
                                <form action="Signup" method="post" name = "signup">
                                <label>username</label> <input type="text" name="newusername"/>
                                <label>password</label> <input type="password" name="newpassword"/>
                                <label>confirm password</label><input type="password" name="retypepswd"/>
                                <label>email</label> <input type="text" name="email"/>
                                <label>confirm email</label> <input type="text" name="retypemail"/>
                                <input type="submit" value="Sign up" name="signup" style= "display:block;float:right;"/>
                                </form>
                        </div>
                        <div class="sign" id="signin">
                                <form action="Login" method="post" name="signin">
                                <label>username</label> <input type="text" name="username"/>
                                <label>password</label> <input type="password" name="password"/>
                                <input type="submit" value="Sign in" name="signin" style="display:block;float:right;"/>
                                </form>
                        </div>
                       
               	</div>
<jsp:include page="/footer.jsp" />               	                  