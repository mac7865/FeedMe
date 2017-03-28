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
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
	<head>
  		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="/stylesheets/bootstrap.css">
		<script src="/js/jquery.js"></script>
		<link rel="stylesheet" href="/stylesheets/simple-sidebar.css">	
		<link rel="stylesheet" href="/stylesheets/main.css">		
		<script src="/js/sidebarHeight.js"></script>	
		<script src="/js/navbar.js"></script>																			
  	</head>
	<body>
	  <!--  import sidebars and navbar -->
	  <%@ include file="/html/navbar.html" %>
	  
	  <div id="leftborder" class="leftborder">
	  	<a href="#menu-toggle" class="btn btn-default" id="menu-toggle">Menu</a>			
	  </div>
	  
	  <div class="content" style="text-align:center">  
		  		<h1><%=(String)request.getAttribute("recipeTitle")%></h1>
			    <br></br>	
			    
			    <blockquote>Recipe Instructions: <ul class="list-group">
			
				<% String[] ingredients = (String[])request.getAttribute("recipeIngredients");
			    	for(int j = 0; j < ingredients.length; j++)
			    	{	%> 
			        <li class="list-group-item"><%= 	ingredients[j].toString() + "\n"%></li>
			   	<% } %>
			    </ul>	
			    </blockquote>
			    
			    <br></br>	
			    
			    <blockquote>Recipe Instructions: <ul class="list-group">
			
				<% String[] instructions = (String[])request.getAttribute("recipeInstructions");
			    	for(int i = 0; i < instructions.length; i++)
			    	{
			    %>    <li class="list-group-item"><%= 	instructions[i].toString() + "\n"%></li>
			    <% } %>
			    </ul>	
			    </blockquote>
	  </div>
	  
	  <div id="rightborder" class="rightborder"></div>  
	  <script type="text/javascript" src="js/navbar.js"></script>
	  <script type="text/javascript" src="js/sidebarHeight.js"></script>	  
	   	     	  
</body>
</html>