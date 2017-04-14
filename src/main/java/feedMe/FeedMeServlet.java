package feedMe;

import java.io.Console;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

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
		
		StringBuilder urlBuilder = new StringBuilder("https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/searchComplex?instructionsRequired=true&limitLicense=false&number=3&offset="+offset+"&query=chicken&ranking=1");
		
		URL url = new URL(urlBuilder.toString());
		System.out.println(url.toString());
      	HttpURLConnection conn = SpoonacularAPI.getUniqueInstance().getComplexSearchConnection(url);
      	req = SpoonacularAPI.getUniqueInstance().parseSearchResults(req, conn); 
      	req.getRequestDispatcher("/search.jsp").forward(req, resp);
    }
}
