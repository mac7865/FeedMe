<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>

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
	  	<blockquote> 
			<table class="table table-hover">
				<tr>
				    <th>Recipes</th>
				    <th>Summaries</th>
			  	</tr>
				<% 
				System.out.println("loading lists");
				ArrayList<String> recipes = (ArrayList<String>)request.getAttribute("recipes");
				System.out.println(recipes.toString());	
					ArrayList<Long> recipeIDs = (ArrayList<Long>)request.getAttribute("recipeIDs");
					System.out.println(recipeIDs.toString());
					ArrayList<String> summaries = (ArrayList<String>)request.getAttribute("summaries");
			    	for(int i = 0; i < recipeIDs.size(); i++)
			    	{
				    %>  <tr>
				    	<td><a href="/recipe/<%=recipeIDs.get(i)%>"><%= recipes.get(i).toString()	+ "\n"%></a></td>
				    	<td><%= summaries.get(i).toString()	+ "\n"%></td>
				    	</tr>
			    <% } %>
			    </table>	
		</blockquote>	
	  </div>

	  <div id="rightborder" class="rightborder">
			<div id="weeklyUpdate" class="weeklyUpdate">
				<p><h3>Weekly Recipe</h3></p>
					<br></br>
				<% if(pageContext.getAttribute("user") != null) { %>
					<%= feedMe.WeeklyUpdate.weeklyrecipe %>
					<br></br>
					<a href="/recipe/<%= feedMe.WeeklyUpdate.weeklyrecipeID %>" class="btn btn-primary">FeedMe this recipe!</a>
			    <% }
			     else {
			    	pageContext.setAttribute("user",null); %>
				    <p>Not subscribed to the weekly recipe</p> 
				    
				 <% } %>
			</div>	
	  </div>
		
	  <script type="text/javascript" src="js/navbar.js"></script>
	</body>
	
		  
	  
</html>