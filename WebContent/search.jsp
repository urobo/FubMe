<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
        			<title>Search</title>
                <link rel="stylesheet" type="text/css" href="fubme.css" />
        </head>
        <body>
                 <div class="header">  
                         <div class="title"> <h1>welcome on fubme</h1></div>
                           <div class= "links">
              
                                  <a href="">
                                        <img src="images/profile.png" alt="profile"/>
                                  </a>
                                  <a href="">
                                          <img src="images/home.png" alt="home"/>
                                  </a>
                                  <a href="">
                                          <img src="images/search.png" alt="search"/>
                                  </a>
                                  <a href="">
                                          <img src="images/settings.png" alt="settings"/>
                                  </a>
        
                            </div>
                        </div>
                  <hr/>
                  
						<div id="search">
						
						<form action="" method="get">
							<input value="Search" name="searchtext" type="text" style="font-family:Gotham Book,Gotham,sans-serif;"/>
							<input type="submit" name="searchbutton" value="Search" style="font-family:Gotham Book,Gotham,sans-serif;"/><br/>
							<input type="checkbox" name="people" value="people" checked="checked"/> people &nbsp;&nbsp;
							<input type="checkbox" name="tags" value="bytags" checked="checked" /> by tags &nbsp;&nbsp;
							<input type="checkbox" name="posts" value="posted_content" checked="checked" /> posts' content &nbsp;&nbsp;
						</form>
					
						</div>
                  <div class="searchresults">
                  	<div class="rounded" id="peoplelist">
									<span>People</span>
							</div>
							<div class="peopleresults">								
								<ul>
									<li>
										<div class="people">
										
											<img class="postimage" src="pp1.jpg" height="48px" width="48px" alt="profile picture"/>
             						  <b>Urobo</b><br/>
              						  <i>Bachelor student in Computer Science at the Free University of Bozen,IT. At moment I'm working on DyCaPo Project for my thesis http://dycapo.org
http://uroboatsonet.blogspot.com/ </i>
 											<div class="misc_buttons">
              						    <button class="rounded" id= "action_follow_urobo" type = "submit" name= "action_follow_urobo"><span>+</span></button>
              						  </div>
										</div>
									</li>
									<li>
											<div class="people">
											<img class="postimage" src="blah.jpg" height="48px" width="48px" alt="profile picture"/>
											
             						  <b>Bodom</b><br/>
              						  <i>Master student in Software Engineering at the Free University of Bozen,IT. </i>
              						  
              						  <div class="misc_buttons">
              						    <button class="rounded" id= "action_follow_bodom" type = "submit" name= "action_follow_bodom"><span>+</span></button>
              						  </div>
              						  </div>
									</li>
									<li></li>
									<li></li>
								</ul>								
							</div>
							<div class="rounded" id="posts">
									<span>Posts</span>
							</div>
							<div class="postsresults">								
								<ul>
									<li>
									 <div class="post">
         							  <div class="postimage">
      								     <img src="pp1.jpg" height="48px" width="48px" alt="profile picture"/>
       							    </div>
         							  <div class="posttext">
             						   hello world!
              						  <div class= "tag">

               					 <a href="">#asd</a>
               					 <a href="">#tag</a>
              					  </div>
              					  <hr/>
               				 <div class="miscellanea">
                   				     <button name="comment" type="submit" class="linkbutton">Comment</button>
               					         <button name="like" type="submit" class="linkbutton">Like</button>
                    		    <button name="reblog" type="submit" class="linkbutton">ReBlog</button>
              					  </div>
              
         				       <div class = "comment">
               		         <p>comment01</p>
             				   </div>
             				   <div class = "comment">
                  		      <p>comment02</p>
            				    </div>
             				   <div class = "comment">
             			           <p>comment03</p>
             				   </div>
        						  </div>
      					  </div>
										
									</li>
									<li>
									<div class="post">
       	  <div class="postimage">
           <img src="pp1.jpg" height="48px" width="48px" alt="profile picture"/>
           </div>
           <div class="posttext">
                 post number 2
                <div class= "tag">

                <a href="">#bullshit</a>
                <a href="">#tag</a>
                <a href="">#experiment</a>
                <a href="">#fubme</a>
                </div>
                <hr/>
                <div class="miscellanea">
                        <button name="comment" type="submit" class="linkbutton">Comment</button>
                        <button name="like" type="submit" class="linkbutton">Like</button>
                        <button name="reblog" type="submit" class="linkbutton">ReBlog</button>
                </div>
          </div>    
        </div>
									</li>
									<li>
									<div class="post">
        		
        		 <div class="postimage">
           <img src="pp1.jpg" height="48px" width="48px" alt="profile picture"/>
           </div>
           <div class="posttext">
                <p> post number 3</p>
                <p>
                        <iframe src="http://www.youtube.com/embed/gyZYHNZ0GT4" width="480" height="390"> 
                        your browser doesn't support iframe </iframe>
                </p>
                
                <div class= "tag">

                <a href="">#video</a>
                <a href="">#john carpenter</a>
                </div>
                <hr/>
                <div class="miscellanea">
                        <button name="comment" type="submit" class="linkbutton">Comment</button>
                        <button name="like" type="submit" class="linkbutton">Like</button>
                        <button name="reblog" type="submit" class="linkbutton">ReBlog</button>
                </div>
              </div>
        </div>
									</li>
									<li>
									 <div class="post">
        <div class="postimage">
           <img src="pp1.jpg" height="48px" width="48px" alt="profile picture"/>
           </div>
        <div class="posttext">
                <p>Integer dignissim, arcu ut posuere commodo, tellus nulla fringilla elit, vel tristique nulla odio in dolor. Integer at quam a turpis luctus gravida. Maecenas dolor nibh, molestie et commodo venenatis, egestas in nisl. Nam dignissim ultrices fringilla. Phasellus non est non eros tempor consectetur. Etiam consequat augue at leo posuere ut porta sem dapibus. Maecenas adipiscing turpis vitae velit gravida varius. Mauris lobortis ultrices magna eget euismod. In eleifend mollis posuere. Vivamus nec semper erat. Duis volutpat, urna vel auctor facilisis, libero nunc ullamcorper est, ac tempor turpis leo non justo. Quisque ipsum justo, dignissim vitae condimentum aliquet, sodales ac dolor. Sed posuere, dolor id laoreet commodo, magna leo laoreet turpis, vel laoreet ante tellus sed sapien. Donec eros mi, auctor a rutrum a, tristique vel purus. Sed elementum, urna vitae vehicula commodo, orci diam condimentum ante, id mollis eros odio vel neque. Sed id tortor non arcu ullamcorper pretium. Nunc vestibulum diam eget dui pellentesque molestie. Integer non mauris eu augue pharetra faucibus id non elit. Vivamus ac mi leo. Sed aliquet dictum pretium.</p> 
                
                 <div class= "tag">

                <a href="">#loren ipsum</a>
                </div>
                <hr/>
            
                <div class="miscellanea">
                        <button name="comment" type="submit" class="linkbutton">Comment</button>
                        <button name="like" type="submit" class="linkbutton">Like</button>
                        <button name="reblog" type="submit" class="linkbutton">ReBlog</button>
                </div>
          </div>
        </div>
									</li>
								</ul>								
							</div>                   
                  </div>
                  <hr/>
                         <div class="footer">
   
                                  <div class= "links">
                                          <a href="">
                                                <img src="images/profile.png" alt="profile"/>
                                          </a>
                                          <a href="">
                                                <img src="images/home.png" alt="home"/>
                                          </a>
                                          <a href="">
                                                <img src="images/search.png" alt="search"/>
                                          </a>
                                          <a href="">
                                                <img src="images/settings.png" alt="settings"/>
                                          </a>
                                   </div>
                        </div>
        </body>
</html>