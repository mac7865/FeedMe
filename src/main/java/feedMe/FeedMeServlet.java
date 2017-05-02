package feedMe;

import java.io.Console;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class FeedMeServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		int offset = (int) (Math.random() * 100);		
		//send request to API for specific recipes
		//first build string, base string in StringBuilder then add paramaters
		//string must be built in proper order, refer to Spoonacular API
		System.out.println(req.getParameterMap().keySet().toString());
		
		StringBuilder urlBuilder = new StringBuilder("https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/searchComplex?instructionsRequired=true"+"&limitLicense=false&number=3&offset="+offset+"&ranking=1");
		try {
			urlBuilder.append("&query="+(String)req.getParameter("query").replace(' ', '+'));
		} catch(Exception e) {
			
		}
		urlBuilder.append("&cuisine=");	
		if(req.getParameter("c_African") != null)
			urlBuilder.append("african%2c+");
		
		if(req.getParameter("c_Chinese") != null)
			urlBuilder.append("chinese%2c+");
		
		if(req.getParameter("c_Japanese") != null)
			urlBuilder.append("japanese%2c+");
		
		if(req.getParameter("c_Korean") != null)
			urlBuilder.append("korean%2c+");
		
		if(req.getParameter("c_Vietnamese") != null)
			urlBuilder.append("vietnamese%2c+");
		
		if(req.getParameter("c_Spanish") != null)
			urlBuilder.append("spanish%2c+");
		
		if(req.getParameter("c_Middle") != null)
			urlBuilder.append("middle+eastern%2c+");
		
		if(req.getParameter("c_Jewish") != null)
			urlBuilder.append("jewish%2c+");
		
		if(req.getParameter("c_Indian") != null)
			urlBuilder.append("indian%2c+");
		
		if(req.getParameter("c_British") != null)
			urlBuilder.append("british%2c+");
		
		if(req.getParameter("c_Irish") != null)
			urlBuilder.append("irish%2c+");
		
		if(req.getParameter("c_French") != null)
			urlBuilder.append("french%2c+");
		
		if(req.getParameter("c_Italian") != null)
			urlBuilder.append("italian%2c+");
		
		if(req.getParameter("c_Mexican") != null)
			urlBuilder.append("mexican%2c+");
		
		if(req.getParameter("c_Thai") != null)
			urlBuilder.append("thai%2c+");
		
		if(req.getParameter("c_Cajun") != null)
			urlBuilder.append("cajun%2c+");
		
		if(req.getParameter("c_Southern") != null)
			urlBuilder.append("southern%2c+");
		
		if(req.getParameter("c_Greek") != null)
			urlBuilder.append("greek%2c+");
		
		if(req.getParameter("c_German") != null)
			urlBuilder.append("german%2c+");
		
		if(req.getParameter("c_Nordic") != null)
			urlBuilder.append("nordic%2c+");
		
		if(req.getParameter("c_Eastern") != null)
			urlBuilder.append("eastern+european%2c+");
		
		if(req.getParameter("c_American") != null)
			urlBuilder.append("american%2c+");
		
		if(req.getParameter("c_Caribbean") != null)
			urlBuilder.append("caribbean%2c+");
		
		if(req.getParameter("c_Latin") != null)
			urlBuilder.append("latin+american%2c+");
		
		if(urlBuilder.substring(urlBuilder.length()-4, urlBuilder.length()).equals("%2c+"))
			urlBuilder.delete(urlBuilder.length()-4, urlBuilder.length());
		
		if(urlBuilder.substring(urlBuilder.length()-9, urlBuilder.length()).equals("&cuisine="))
			urlBuilder.delete(urlBuilder.length()-9, urlBuilder.length());
		
		if(!req.getParameter("text_Include").equals("")) {
			urlBuilder.append("&includeIngredients="+(String)req.getParameter("text_Include"));
			while(urlBuilder.indexOf(",") != -1) {
				urlBuilder.replace(urlBuilder.indexOf(","), urlBuilder.indexOf(",")+1, "%2c");
				System.out.println(urlBuilder.toString());

			}
			while(urlBuilder.indexOf(" ") != -1) {
				urlBuilder.replace(urlBuilder.indexOf(" "), urlBuilder.indexOf(" ")+1, "+");
			}	
		}

		if(!req.getParameter("text_Exclude").equals("")) {
			urlBuilder.append("&excludeIngredients="+(String)req.getParameter("text_Exclude"));
			while(urlBuilder.indexOf(",") != -1) {
				urlBuilder.replace(urlBuilder.indexOf(","), urlBuilder.indexOf(",")+1, "%2c");

			}
			while(urlBuilder.indexOf(" ") != -1) {
				urlBuilder.replace(urlBuilder.indexOf(" "), urlBuilder.indexOf(" ")+1, "+");
			}	
		}
		
		urlBuilder.append("&intolerances=");
		if(req.getParameter("c_Dairy") != null)
			urlBuilder.append("dairy%2c+");
		
		if(req.getParameter("c_Egg") != null)
			urlBuilder.append("egg%2c+");
		
		if(req.getParameter("c_Tree") != null)
			urlBuilder.append("tree+nuts%2c+");
		
		if(req.getParameter("c_Gluten") != null)
			urlBuilder.append("gluten%2c+");

		if(req.getParameter("c_Peanuts") != null)
			urlBuilder.append("peanut%2c+");

		if(req.getParameter("c_Sesame") != null)
			urlBuilder.append("sesame%2c+");
		
		if(req.getParameter("c_Seafood") != null)
			urlBuilder.append("seafood%2c+");
		
		if(req.getParameter("c_Shellfish") != null)
			urlBuilder.append("shellfish%2c+");
		
		if(req.getParameter("c_Soy") != null)
			urlBuilder.append("soy%2c+");
		
		if(req.getParameter("c_Sulfite") != null)
			urlBuilder.append("sulfite%2c+");
		
		if(req.getParameter("c_Wheat") != null)
			urlBuilder.append("wheat%2c+");
		
		if(urlBuilder.substring(urlBuilder.length()-4, urlBuilder.length()).equals("%2c+"))
			urlBuilder.delete(urlBuilder.length()-4, urlBuilder.length());
		
		if(urlBuilder.substring(urlBuilder.length()-14, urlBuilder.length()).equals("&intolerances="))
			urlBuilder.delete(urlBuilder.length()-14, urlBuilder.length());
		
		
		try{
				Integer.parseInt(req.getParameter("maxCalories"));
				urlBuilder.append("&maxCalories="+(String)req.getParameter("maxCalories"));
			} catch (NumberFormatException e) {
				// not an integer!
			}
		
		
		
			try{
				Integer.parseInt(req.getParameter("maxCarbs"));
				urlBuilder.append("&maxCarbs="+(String)req.getParameter("maxCarbs"));
			} catch (NumberFormatException e) {
				// not an integer!
			}
		
		
		
			try{
				Integer.parseInt(req.getParameter("maxFat"));
				urlBuilder.append("&maxFat="+(String)req.getParameter("maxFat"));
			} catch (NumberFormatException e) {
				// not an integer!
			}
		
		
		
			try{
				Integer.parseInt(req.getParameter("maxProtein"));
				urlBuilder.append("&maxProtein="+(String)req.getParameter("maxProtein"));
			} catch (NumberFormatException e) {
				// not an integer!
			}
		

		
			try{
				Integer.parseInt(req.getParameter("minCalories"));
				urlBuilder.append("&minCalories="+(String)req.getParameter("minCalories"));
			} catch (NumberFormatException e) {
				// not an integer!
			}
		
		
		
			try{
				Integer.parseInt(req.getParameter("minCarbs"));
				urlBuilder.append("&minCarbs="+(String)req.getParameter("minCarbs"));
			} catch (NumberFormatException e) {
				// not an integer!
			}
		
		
		
			try{
				Integer.parseInt(req.getParameter("minFat"));
				urlBuilder.append("&minFat="+(String)req.getParameter("minFat"));
			} catch (NumberFormatException e) {
				// not an integer!
			}
		
		
		
			try{
				Integer.parseInt(req.getParameter("minProtein"));
				urlBuilder.append("&minProtein="+(String)req.getParameter("minProtein"));
			} catch (NumberFormatException e) {
				// not an integer!
			}
		
			System.out.println(req.getParameter("s_LifeStyle") != null);
			System.out.println(req.getParameter("s_LifeStyle"));
		try{
			if(!req.getParameter("s_LifeStyle").equals("null")) {
				System.out.println(req.getParameter("s_LifeStyle"));
				urlBuilder.append("&diet="+(String)req.getParameter("s_LifeStyle"));
			}
		} catch(Exception e) {}
		
		Logger.getAnonymousLogger().info(urlBuilder.toString());
		URL url = new URL(urlBuilder.toString());
      	HttpURLConnection conn = SpoonacularAPI.getUniqueInstance().getComplexSearchConnection(url);
      	req = SpoonacularAPI.getUniqueInstance().parseSearchResults(req, conn); 
      	req.getRequestDispatcher("/search.jsp").forward(req, resp);
    }
}
