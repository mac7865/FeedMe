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
  		<link type="text/css" rel="stylesheet" href="/stylesheets/main.css" />
  		<link href="css/bootstrap.min.css" rel="stylesheet">
		<style>
			title {
			    text-align: center;
			}
		</style>
  	</head>

	<body>
	<title>New Title</title>
	<%

	    UserService userService = UserServiceFactory.getUserService();

	    User user = userService.getCurrentUser();

	    if (user != null) {

	      pageContext.setAttribute("user", user);

	%>

	<p>Hello! (You can

	<a href="<%= userService.createLogoutURL(request.getRequestURI()) %>">sign out by clicking here.</a>)</p>

	<%

	    } else {

	%>

	<p>Hello!

	<a href="<%= userService.createLoginURL(request.getRequestURI()) %>">Sign in</a>

	to post on the blog.</p>

	<%

	    }

	%>
    
    <br>
    
	</body>
</html>