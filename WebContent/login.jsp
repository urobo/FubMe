<jsp:include page="/header.jsp" />
                  <div class="box">
                        <div class="sign" id="signup">
                                <form action="" method="get">
                                <label>username</label> <input type="text" name="username"/>
                                <label>password</label> <input type="password" name="password"/>
                                <label>confirm password</label><input type="password" name="retypepswd"/>
                                <label>email</label> <input type="text" name="email"/>
                                <label>confirm email</label> <input type="text" name="retypemail"/>
                                <input type="submit" value="Sign up" name="signup" style= "display:block;float:right;"/>
                                </form>
                        </div>
                        <div class="sign" id="signin">
                                <form action="" method="get">
                                <label>username</label> <input type="text" name="username"/>
                                <label>password</label> <input type="password" name="pswd"/>
                                <input type="submit" value="Sign in" name="signin" style="display:block;float:right;"/>
                                </form>
                        </div>
                       
               	</div>
<jsp:include page="/footer.jsp" />               	                  