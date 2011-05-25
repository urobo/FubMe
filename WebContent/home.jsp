<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
        <title>Welcome to FubMe</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /><!-- <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" /> -->
        <meta name="keywords" content="loren ipsum"/>
        <meta name="keywords" content="asd tag"/>
        <meta name="keywords" content="bullshit tag experiment fubme"/>
        <meta name="keywords" content="video john carpenter"/>
        <meta name="keywords" content="loren ipsum"/>
                <link rel="icon" href="file:///home/riccardo/Desktop/IT/images/fubme.png" type="image/png" />
        <link rel="stylesheet" type="text/css" href="fubme.css"/>
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
        <div class="rightcolumn">
                <div id="bio">
                <img src="pp1.jpg" height="80px" width="80px" alt="profile picture"/>
                <p><b>Urobo</b></p>
                <p><i>Bachelor student in Computer Science at the Free University of Bozen,IT. At moment I'm working on DyCaPo Project for my thesis http://dycapo.org
http://uroboatsonet.blogspot.com/ </i></p>
                </div>
                <div class="contacts" id="followers">
                <p><b>Followers</b></p>
                <p></p>
                </div>
                <div class="contacts" id="following">
                <p><b>Following</b></p>
                <p></p>
                </div>
                <div id="search">
                <b>Search</b>
                <form action="" method="get">
			<p><input value="Search" name="searchtext" id="searchtext" type="text"/>
			<input src="images/search_icon.png" type="image" alt="Search" id="searchbutton" name="searchbutton"/></p>
	        </form>

                </div>
        </div>
        
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