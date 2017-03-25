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
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script src="/js/jquery.js"></script>
  	</head>

	<body>
	<div class="leftborder">
		<a href="#menu-toggle" class="btn btn-default" id="menu-toggle">Menu</a>			
	</div>	
	<div class="rightborder"></div>	
	
	<div id="wrapper" class="toggled">
    <div id="sidebar-wrapper">
            <ul class="sidebar-nav">
                <li class="sidebar">
                    <a href="/">
                        Home
                    </a>
                </li>
                <li>
                    <a href="/random">Random Recipe</a>
                </li>
                <li>
                	<%
		
				    UserService userService = UserServiceFactory.getUserService();
			
				    User user = userService.getCurrentUser();
			
				    if (user != null) {
			
				      pageContext.setAttribute("user", user);
			
					%>
								
					<a href="<%= userService.createLogoutURL(request.getRequestURI()) %>">Sign out</a>
				
					<%
				
					    } else {
				
					%>
					<a href="<%= userService.createLoginURL(request.getRequestURI()) %>">Sign in</a>
								
					<%
				
					    }
			
					%>
                </li>
            </ul>
        </div>
      </div>
	  	
	  <div class="content">  
		 <center><h1>Search form goes here</h1></center>
	  </div>
	  <!-- Menu Toggle Script -->
	  <script>
	    $("#menu-toggle").click(function(e) {
	        e.preventDefault();
	        $("#wrapper").toggleClass("toggled");
	    });
   	  </script>
	</body>
</html>