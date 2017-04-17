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
	  		<form action="/search" method="post" name="searchForm" id="searchForm">
				 <div class="row">
						<div class="col-md-12">
				            <div class="panel with-nav-tabs panel-primary">
				                <div class="panel-heading">
				                        <ul class="nav nav-tabs">
				                            <li class="active"><a href="#basic" data-toggle="tab"id="basicTab">Basic</a></li>
				                            <li><a href="#nutrition" data-toggle="tab" id="nutritionTab">Nutrition</a></li>
				                            <li><a href="#dietary" data-toggle="tab" id="dietaryTab">Dietary</a></li>
	
				                        </ul>
				                </div>
				                <div class="panel-body">
				                    <div class="tab-content">
				                        <div class="tab-pane fade in active" id="basic">
				                        	<label for="query" class="col-2 col-form-label">*Keywords (only field required): </label>
									 		<div class="col-10">
									   		 <input class="form-control" type="text" placeholder="Chicken, Steak, Soup, Tacos..." id="query" name="query">
									  		</div>
				                        </div>
				                        <div class="tab-pane fade" id="nutrition">
				                        	Nutrition
				                        	<br><br>
				                        	<div><p>The maximum number of calories the recipe can have.</p><span class="type">NUMBER</span></div>
										    <div><input type="text" placeholder="optional" class="form-control" id="maxCalories" name="maxCalories">
										    </div>
											<div><p>The maximum number of grams of carbohydrates the recipe can have.</p><span class="type">NUMBER</span></div>
											    <div><input type="text" placeholder="optional" class="form-control" id="maxCarbs" name="maxCarbs">
											    </div>
											<div><p>The maximum number of grams of fat the recipe can have.</p>
											<span class="type">NUMBER</span></div>
											    <div><input type="text" placeholder="optional" class="form-control" id="maxFat" name="maxFat">
											    </div>
											<div><p>The maximum number of grams of protein the recipe can have.</p>
												<span class="type">NUMBER</span></div>
											    <div><input type="text" placeholder="optional" class="form-control" id="maxProtein" name="maxProtein">
											    </div>
											<div><p>The minimum number of calories the recipe must have.</p><span class="type">NUMBER</span></div>
											    <div><input type="text" placeholder="optional" class="form-control" id="minCalories" name="minCalories">
											    </div>
											<div><p>The minimum number of grams of carbohydrates the recipe must have.</p><span class="type">NUMBER</span></div>
											    <div><input type="text" placeholder="optional" class="form-control" id="minCarbs" name="minCarbs">
											    </div>
											<div><p>The minimum number of grams of fat the recipe must have.</p>
												<span class="type">NUMBER</span></div>
											    <div><input type="text" placeholder="optional" class="form-control" id="minFat" name="minFat">
											    </div>
											<div><p>The minimum number of grams of protein the recipe must have.</p><span class="type">NUMBER</span></div>
											    <div><input type="text" placeholder="optional" class="form-control" id="minProtein" name="minProtein">
											    </div>
				                        </div>
				                        <div class="tab-pane fade" id="dietary">Dietary</div>
				                    </div>
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
	</body>
	
		  
	  
</html>