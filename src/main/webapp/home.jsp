<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="com.google.appengine.api.datastore.DatastoreServiceFactory" %>
<%@ page import="com.google.appengine.api.datastore.DatastoreService" %>
<%@ page import="com.google.appengine.api.datastore.Query" %>
<%@ page import="com.google.appengine.api.datastore.Entity" %>
<%@ page import="com.google.appengine.api.datastore.FetchOptions" %>
<%@ page import="com.google.appengine.api.datastore.Key" %>
<%@ page import="com.google.appengine.api.datastore.KeyFactory" %>

<html>
	<head>
    	<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="/stylesheets/main.css">		    	
		<link rel="stylesheet" href="/stylesheets/simple-sidebar.css">		
		<link rel="stylesheet" href="/stylesheets/bootstrap.css">
		<link rel="stylesheet" href="/stylesheets/searchForm.css">		
		<script src="/js/jquery.min.js"></script>
		<script src="/js/bootstrap.min.js"></script>
		<script src="/js/jquery.min.js"></script>
		<script src="/js/bootstrap.min.js"></script>	  
		<script src="/js/sidebarHeight.js"></script>	  		
  	</head>

	<body>
	  <!--  import navbar, import javascript to control at end of file -->
	  <%@ include file="/html/navbar.html" %>
	  <div id="leftborder" class="leftborder">
	  	<a href="#menu-toggle" class="btn btn-default" id="menu-toggle">Menu</a>			
	  </div>	
	  
	  <div class="content">  
			<center><h1>FeedMe</h1></center>
			<br><br>
	  		<form action="/search" method="post">
				 <div class="row">
						<div class="col col-sm-4">
				    	    <ul class="nav nav-tabs nav-stacked text-center" role="tablist">
				                <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">Home</a></li>
				                <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">Profile</a></li>
				                <li role="presentation"><a href="#messages" aria-controls="messages" role="tab" data-toggle="tab">Messages</a></li>
				            </ul>
						</div>
				        <div class="col col-sm-8">
				            <div class="row tab-content">
				                <div role="tabpanel" class="tab-pane fade active in" id="home">
				                	basic info
				                </div>
				                <div role="tabpanel" class="tab-pane fade" id="profile">
									nutritional
				                </div>
				                <div role="tabpanel" class="tab-pane fade" id="messages">
									ingredients
				                </div>
				            </div>
						</div>
					</div>
				    <div class="row">
				    <ul class="list-inline" style="text-align-last: center;margin-top: 3%;">
			            <li><button type="button" class="btn btn-default prev-step">Previous</button></li>
				        <li><button type="submit" class="btn btn-primary">Search</li>		            			            
			            <li><button type="button" class="btn btn-default next-step">Next</button></li>
				    </ul>  				    
				    </div>
				</form>
		</div>
		
		<div id="rightborder" class="rightborder"></div>
		
		<!-- !next/prev step control -->
		<script>
		  $(document).ready(function () {
			  $(".next-step").click(function (e) {
			
			  var $active = $('.nav-tabs li.active');
			  $active.next().removeClass('disabled');
			  nextTab($active);
			
			  });
			  
			  $(".prev-step").click(function (e) {
			
			  var $active = $('.nav-tabs li.active');
			  prevTab($active);
			
		      });
		  });
		  function nextTab(elem) {
		  	$(elem).next().find('a[data-toggle="tab"]').click();
		  }
		  function prevTab(elem) {
		  	$(elem).prev().find('a[data-toggle="tab"]').click();
		  }       
	  </script>  
	  <script type="text/javascript" src="js/navbar.js"></script>
	  <script type="text/javascript" src="js/sidebarHeight.js"></script>	  
	</body>
	
		  
	  
</html>