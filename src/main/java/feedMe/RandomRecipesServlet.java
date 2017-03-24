package feedMe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
//import com.google.appengine.repackaged.com.fasterxml.jackson.core.JsonFactory;
//import com.google.appengine.repackaged.com.fasterxml.jackson.core.JsonParser;
//import com.google.appengine.repackaged.com.fasterxml.jackson.core.JsonToken;

import com.google.appengine.repackaged.org.codehaus.jackson.map.ObjectMapper;


@SuppressWarnings("serial")
public class RandomRecipesServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		//send request to API for random recipe		
		HttpURLConnection conn = SpoonacularAPI.getUniqueInstance().getRandomRecipeConnection();
		req = SpoonacularAPI.getUniqueInstance().parseRandomRecipe(req, conn);
		req.getRequestDispatcher("/recipe.jsp").forward(req, resp);

	}
}
