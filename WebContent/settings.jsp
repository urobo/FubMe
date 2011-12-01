<jsp:include page="/header.jsp" />

<div class="canvas" >
	<div class="settingsblock">
		<%
			out.print("<form action=\""+ request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/ImageUpload");
			out.print("\" enctype=\"multipart/form-data\" method=\"post\" >");
		%>
		<p><label class="blocktitle">Change profile picture :</label><br/>
				<input type="file" name="newprofilepicture"/><br/>
				<input type="submit" value="Upload Image"/><br/>
			
		</p>
		</form>
	</div>
	<div class="settingsblock" style="height:230px">
		
		<%
			out.print("<form action=\""+ request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/Action?action=updateinfo");
			out.print("\" method=\"post\" >");
			
		%>
			<div class="settingsublock">
				<p><label class="blocktitle">Change Password :</label>
					<br/><label>old password</label>
					<input type="password" name="oldpassword" /> 
					<label>new password</label>
					<input type="password" name="newpassword" /> 
					<label>confirm new password</label>
					<input type="password" name="retypepswd" /> 
				</p>
				
				<input type="submit" value="Save" name="updateinfo"  />
				
			</div>
			<div class="settingsublock" style="margin-left:20px">	
				<p><label style="color:#974107">Change Email :</label>
					<br/><label>old email</label>
					<input type="text" name="oldemail" /> 
					<label>new email</label>
					<input type="text" name="newemail" /> 
					<label>confirm new email</label> 
					<input type="text" name="retypemail" /> 
				</p>
				
			</div>
		</form>
	</div>
	
	<div class="settingsblock" style="height:265px">
	<%
				out.print("<form action=\""+ request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/Action?action=updatedetails");
				out.print("\" method=\"post\" >");
			%>
	<div class="settingsublock">
	
		<p><label class="blocktitle">Futher Information : </label><br/>
			
			
			<br/> <label>firstname</label>
			<input type="text" name="firstname"/>
			<label>lastname</label>
			<input type="text" name="lastname"/>
			<label>birthday  (YYYY/MM/DD)</label>
			<input type="text" name="birthday" maxlength="10"/>
			
		</p>
			<input type="submit" value="Save" name="updatedetails"  />
		</div>
		<div class="settingsublock">
			<p>
			<br/> <label>bio</label>
			<textarea name="bio" cols="80" rows="6"></textarea>
			</p>
			
		</div>
		</form>
	</div>
	
</div>

<jsp:include page="/footer.jsp" />
