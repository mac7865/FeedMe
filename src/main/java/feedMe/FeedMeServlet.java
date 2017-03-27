package feedMe;

import java.io.Console;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Date;

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
		UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        if (user == null) {
            resp.sendRedirect(userService.createLoginURL(req.getRequestURI()));
        }
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//send request to API for random recipe		
      	HttpURLConnection conn = SpoonacularAPI.getUniqueInstance().getComplexSearchConnection();
      	req = SpoonacularAPI.getUniqueInstance().parseSearchResults(req, conn); 
      	resp.sendRedirect("/");

    }
}
