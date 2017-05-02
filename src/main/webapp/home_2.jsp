<!DOCTYPE html>
<html style="width:100%;height:100%;">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Untitled</title>
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/css/Navigation-with-Button1.css">
    <link rel="stylesheet" href="assets/css/styles.css">
</head>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>


<body style="background-image:url(&quot;assets/img/food-drink-kitchen-cutting-board.jpg&quot;);width:100%;height:100%;">
    <div style="width:100%;height:10%;">
        <nav class="navbar navbar-default navigation-clean-button" style="background-color:rgb(37,35,35);width:100%;height:100%;">
            <div class="container">
                <div class="navbar-header"><a class="navbar-brand navbar-link" href="#" style="color:rgb(142,142,142);padding-top:16%;padding-bottom:16%;">Feed Me</a>
                    <button class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button>
                </div>
                <div class="collapse navbar-collapse" name="navcol-1">
                    <ul class="nav navbar-nav">
                        <li class="active" role="presentation"><a href="#" style="color:rgb(142,142,142);padding-top:16%;">Search </a></li>
                        <li role="presentation"><a href="#" style="color:#8e8e8e;padding-top:16%;">Random </a></li>
                        <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false" href="#" style="color:#8e8e8e;padding-top:16%;">Stored <span class="caret"></span></a>
                            <ul class="dropdown-menu" role="menu">
                                <li role="presentation"><a href="#">First Item</a></li>
                                <li role="presentation"><a href="#">Second Item</a></li>
                                <li role="presentation"><a href="#">Third Item</a></li>
                            </ul>
                        </li>
                    </ul>
<p class="navbar-text navbar-right actions">
                    	<%
			
					    UserService userService = UserServiceFactory.getUserService();
				
					    User user = userService.getCurrentUser();
				
					    if (user != null) {
				
					      pageContext.setAttribute("user", user);
				
						%>
										
						<a href="<%= userService.createLogoutURL("/home.jsp") %>">Sign out</a></p>
					
						<%
					
						    } else {
					
						%>
						<a href="<%= userService.createLoginURL("/home.jsp") %>">Sign in</a>
									
						<%
					
						    }
				
						%></p>
                    </p>                </div>
            </div>
        </nav>
    </div>
    <div class="container" style="width:900;margin:30;background-color:White;">
    <form action="/search" method="post">
        <div style="background-color:white;">
            <ul class="nav nav-tabs" style="padding-right:%;padding-left:0%;width:100%;height:8%;">
                <li><a href="#tab-1" role="tab" data-toggle="tab" style="background-color:white;width:100%;">Allergies </a></li>
                <li><a href="#tab-2" role="tab" data-toggle="tab">Nutrition </a></li>
                <li class="active"><a href="#tab-3" role="tab" data-toggle="tab" style="background-color:white;">Preference </a></li>
            </ul>
            <div class="tab-content">
                <div class="tab-pane" role="tabpanel" name="tab-1">
                    <div class="row" style="margin:0;">
                        <div class="col-md-12" style="background-color:white;width:100%;height:20%;">
                            <h1><strong>What are your Allergies</strong>?</h1></div>
                    </div>
                    <div class="col-md-5 col-md-offset-0" style="background-color:white;width:40%;">
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" name="c_Dairy">Dairy</label>
                        </div>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" name="c_Egg">Egg</label>
                        </div>
                        <div class="checkbox">
                            <label style="padding-top:100;">
                                <input type="checkbox" name="c_Tree">Tree Nuts</label>
                        </div>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" name="c_Gluten">Gluten</label>
                        </div>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" name="c_Peanuts">Peanuts</label>
                        </div>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" name="c_Sesame">Sesame</label>
                        </div>
                    </div>
                    <div class="col-md-4" style="background-color:White;height:165px;width:40%;">
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" name="c_Seafood">Seafood</label>
                        </div>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" name="c_Shellfish">Shellfish</label>
                        </div>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" name="c_Soy">Soy</label>
                        </div>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" name="c_Sulfite">Sulfite</label>
                        </div>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" name="c_Wheat">Wheat</label>
                        </div>
                    </div>
                </div>
                <div class="tab-pane" role="tabpanel" name="tab-2" style="background-color:white;">
                    <div class="panel panel-default" style="margin-top:20px;">
                        <div class="panel-heading" style="width:100%;height:25%;">
                            <h3 class="panel-title" style="font-size:22px;">Daily Values</h3></div>
                        <div class="panel-body">
                            <div class="col-md-5" style="padding-top:15px;width:41.5%;height:44.1%;">
                                <label style="font-size:14px;">Max Calories</label>
                                <input type="number" name="t_MaxCal" style="font-size:14px;padding-left:0px;margin-left:5%;" name="t_MaxCal">
                            </div>
                            <div class="col-md-5" style="padding-top:15px;width:41.5%;height:44.1%;">
                                <label style="font-size:14px;">Min Calories </label>
                                <input type="number" name="t_MinCal" style="font-size:14px;" name="t_MinCal">
                            </div>
                            <div class="col-md-5" style="padding-top:15px;width:41.5%;height:44.1%;">
                                <label style="font-size:14px;">Max Fat </label>
                                <input type="number" name="t_MaxFat" style="font-size:14px;margin-left:49px;" name="t_MaxFat">
                            </div>
                            <div class="col-md-5" style="padding-top:15px;width:41.5%;height:44.1%;">
                                <label style="font-size:14px;">Min Fat</label>
                                <input type="number" style="font-size:14px;margin-top:0px;margin-right:0px;margin-left:40px;" name="t_MinFat">
                            </div>
                            <div class="col-md-5" style="padding-top:15px;width:41.5%;height:44.1%;">
                                <label style="font-size:14px;">Max Protein </label>
                                <input type="number" style="font-size:14px;margin-left:18px;" name="t_MaxProtein">
                            </div>
                            <div class="col-md-5" style="padding-top:15px;width:41.5%;height:44.1%;">
                                <label style="font-size:14px;">Min Proten</label>
                                <input type="number" style="font-size:14px;margin-left:5%;" name="t_MinProtein">
                            </div>
                            <div class="col-md-5" style="padding-top:15px;width:41.5%;height:44.1%;">
                                <label style="font-size:14px;">Max Carbs</label>
                                <input type="number" style="font-size:14px;margin-left:32px;" name="t_MaxCarbs">
                            </div>
                            <div class="col-md-5" style="padding-top:15px;width:41.5%;height:44.1%;">
                                <label style="font-size:14px;">Min Carbs</label>
                                <input type="number" style="font-size:14px;margin-left:7%;" name="t_MinCarbs">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="tab-pane active" role="tabpanel" name="tab-3">
                    <h1 style="font-size:28px;height:23px;">Life Style</h1>
                    <select class="s_Lifesyle" style="margin:0px;height:32px;font-size:19px;" name="s_LifeStyle">
                        
                            <option value=null >Omnivore</option>
                            <option value="Pescetarian">Pescetarian 2</option>
                            <option value="Lacto Vegetarian">Lacto Vegetarian</option>
                            <option value="Ovo Vegetarian">Ovo Vegetarian</option>
                            <option value="Vegan">Vegan</option>
                            <option value="Paleo">Paleo</option>
                            <option value="Primal">Primal</option>
                            <option value="Vegetarian">Vegetarian</option>
                        
                    </select>
                    <div class="row" style="margin:0;">
                        <div class="col-md-12" style="background-color:white;">
                            <h1 style="margin-top:15px;">Style of Cuisine</h1></div>
                    </div>
                    <div class="col-md-3 col-md-offset-0" style="background-color:white;">
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" name="c_African">African</label>
                        </div>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" name="c_Chinese">Chinese</label>
                        </div>
                        <div class="checkbox">
                            <label style="padding-top:100;">
                                <input type="checkbox" name="c_Japanese">Japanese</label>
                        </div>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" name="c_Korean">Korean</label>
                        </div>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" name="c_Vietnamese">Vietnamese</label>
                        </div>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" name="c_Spanish">Spanish</label>
                        </div>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" name="c_Middle">Middle Eastern</label>
                        </div>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" name="c_Jewish">Jewish</label>
                        </div>
                    </div>
                    <div class="col-md-3 col-md-offset-0" style="background-color:white;">
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" name="c_Indian">Indian</label>
                        </div>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" name="c_British">British</label>
                        </div>
                        <div class="checkbox">
                            <label style="padding-top:100;">
                                <input type="checkbox" name="c_Irish">Irish</label>
                        </div>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" name="c_French">French</label>
                        </div>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" name="c_Italian">Italian</label>
                        </div>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" name="c_Mexican">Mexican</label>
                        </div>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" name="c_Thai">Thai</label>
                        </div>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" name="c_Cajun">Cajun</label>
                        </div>
                    </div>
                    <div class="col-md-3 col-md-offset-0" style="background-color:white;">
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" name="c_Southern">Southern</label>
                        </div>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" name="c_Greek">Greek</label>
                        </div>
                        <div class="checkbox">
                            <label style="padding-top:100;">
                                <input type="checkbox" name="c_German">German</label>
                        </div>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" name="c_Nordic">Nordic</label>
                        </div>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" name="c_Eastern">Eastern European</label>
                        </div>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" name="c_American">American</label>
                        </div>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" name="c_Caribbean">Caribbean</label>
                        </div>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" name="c_Latin">Latin American</label>
                        </div>
                    </div>
                    <div class="row" style="margin:0;">
                        <div class="col-md-12" style="background-color:white;">
                            <h1 style="margin-top:15px;">Ingrediants ( seperate list with a comma)</h1></div>
                    </div>
                    <div class="col-md-12">
                        <div class="form-group">
                            <label class="control-label">Must Have's</label>
                            <input type="text" name="text_Include" style="margin-right:0px;margin-left:10px;width:292px;">
                        </div>
                    </div>
                    <div class="col-md-12">
                        <div class="form-group">
                            <label class="control-label">Have Not's</label>
                            <input type="text" name="text_Exclude" style="margin-left:21px;width:292px;">
                        </div>
                    </div>
                </div>
            </div>
        </div>
                <button class="btn btn-default" type="submit" name="button_Go" style="margin-left:92.9%;">Feed Me</button>
        </form>
    </div>
    <script src="assets/js/jquery.min.js"></script>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
</body>

</html>