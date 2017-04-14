<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
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
			<center><h1>FeedMe</h1></center>
			<br><br>
	  		<form action="/search" method="post">
				 <div class="row">
						<div class="col col-sm-4">
				    	    <ul class="nav nav-tabs nav-stacked text-center" role="tablist">
				                <li role="presentation" class="active"><a href="#basic" aria-controls="Basic" role="tab" data-toggle="tab">Basic</a></li>
				                <li role="presentation"><a href="#nutrition" aria-controls="Nutritional" role="tab" data-toggle="tab">Nutritional</a></li>
				                <li role="presentation"><a href="#dietary" aria-controls="Dietary" role="tab" data-toggle="tab">Dietary</a></li>
				            </ul>
						</div>
				        <div class="col col-sm-8">
				            <div class="row tab-content">
				                <div role="tabpanel" class="tab-pane fade active in" id="basic">
				                	<div class="form-group row">
									  <label for="query" class="col-2 col-form-label">*Keywords (only field required): </label>
									  <div class="col-10">
									    <input class="form-control" type="text" placeholder="Chicken, Steak, Soup, Tacos..." id="query" name="query">
									  </div>
								   </div>
				                </div>
				                <div role="tabpanel" class="tab-pane fade" id="nutrition">
									nutritional
				                </div>
				                <div role="tabpanel" class="tab-pane fade" id="dietary">
									Dietary
				                </div>
				            </div>
						</div>
					</div>
				    <div class="row">
				    <ul class="list-inline" style="text-align-last: center;margin-top: 3%;">
			            <li><button type="button" class="btn btn-default prev-step">Previous</button></li>
				        <li><button type="submit" class="btn btn-primary">FeedMe!</li>		            			            
			            <li><button type="button" class="btn btn-default next-step">Next</button></li>
				    </ul>  				    
				    </div>
				</form>
		</div>

		<div id="rightborder" class="rightborder">
			<div id="weeklyUpdate" class="weeklyUpdate">
				<p><h3>Weekly Recipe</h3></p>
					<br></br>
				<% if(pageContext.getAttribute("user") != null) { %>
					<form action="/specific" method="post">					
						<%= feedMe.WeeklyUpdate.weeklyrecipe %>
						<br></br>		
						<input type="hidden" name="recipeID" value=<%= feedMe.WeeklyUpdate.weeklyrecipeID %>>		
						<button class="btn btn-primary" type="submit">FeedMe this recipe!</button>			
					</form>
			    <% }
			     else {
			    	pageContext.setAttribute("user",null); %>
				    <p>Not subscribed to the weekly recipe</p> 
				    
				 <% } %>
			</div>	
		</div>
		
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
	  <script type="text/javascript" src="	"></script>	  
	</body>
	
		  
	  
</html>