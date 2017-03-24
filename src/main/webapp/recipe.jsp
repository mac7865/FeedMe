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
  		<link type="text/css" rel="stylesheet" href="/stylesheets/main.css" />
  		<link href="css/bootstrap.min.css" rel="stylesheet">

  	</head>
	<body>
	<h1>View Recipe</h1>
	<p>Recipe Title: <%=(String)request.getAttribute("recipeTitle")%></p>
    <br></br>	
	<p>Recipe ID: <%=(String)request.getAttribute("recipeId")%></p>
    <br></br>	
    
    <blockquote>Recipe Instructions: <ul class="list-group">

	<% String[] ingredients = (String[])request.getAttribute("recipeIngredients");
    	for(int i = 0; i < ingredients.length; i++)
    	{
    %>    <li class="list-group-item"><%= 	ingredients[i].toString() + "\n"%></li>
    <%  } %>
    </ul>	
    </blockquote>
    
    <br></br>	
    
    <blockquote>Recipe Instructions: <ul class="list-group">

	<% String[] instructions = (String[])request.getAttribute("recipeInstructions");
    	for(int i = 0; i < instructions.length; i++)
    	{
    %>    <li class="list-group-item"><%= 	instructions[i].toString() + "\n"%></li>
    <%  } %>
    </ul>	
    </blockquote>

    
</body>
</html>