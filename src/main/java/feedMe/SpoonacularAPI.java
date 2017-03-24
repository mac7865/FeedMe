package feedMe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.appengine.repackaged.org.codehaus.jackson.map.ObjectMapper;


public class SpoonacularAPI {
	public String weeklyrecipe 
	      = "Raw Mocha Coconut Brownie Tarts [Paleo-friendly]"; // weekly featured recipe every subscriber gets
	public int weeklyrecipeID = 539193; // weekly featured recipe id
	
	private SpoonacularAPI() {}
	
	private static SpoonacularAPI singleton = new SpoonacularAPI();
	
	public static synchronized SpoonacularAPI getUniqueInstance() {
		return singleton;	
	}

	public HttpURLConnection getRandomRecipeConnection() throws IOException {
		//send request to API for random recipe
		URL url = new URL("https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/random?limitLicense=false&number=1&tags=vegetarian%2Cdessert");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestProperty("X-Mashape-Key", "IveATqgidUmshh51JwkUjJa2kGAgp1wfynojsn358NrsAalt2G");
		conn.setRequestProperty("Accept", "application/json");			
		return conn;
	}
	
	public HttpURLConnection getSpecificRecipeConnection(int recipeId) throws IOException {
		//send request to API for specific recipe based on recipe id
		URL url = new URL("https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/"+recipeId+"/information");
	    HttpURLConnection conn = (HttpURLConnection) url.openConnection();				
		conn.setRequestProperty("X-Mashape-Key", "IveATqgidUmshh51JwkUjJa2kGAgp1wfynojsn358NrsAalt2G");
		conn.setRequestProperty("Accept", "application/json");
		return conn;
	}
	
	public HttpServletRequest parseRandomRecipe(HttpServletRequest req, HttpURLConnection conn) throws IOException {
		//parse random recipe for id to get recipe info properly
		int respCode = conn.getResponseCode();
		if (respCode == HttpURLConnection.HTTP_OK) {		
		  req.setAttribute("error", "");
		  StringBuffer response = new StringBuffer();
		  String line;

		  BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		  while ((line = reader.readLine()) != null) {
		    response.append(line);
		  }
		  reader.close();
		  
		  Logger.getLogger("default").info("Response from API: " + response.toString());
		  
		  
		  JSONParser parser = new JSONParser();
		  JSONObject jsonObject;
		  try {
			  jsonObject = (JSONObject) parser.parse(response.toString());
			  Logger.getLogger("default").info(jsonObject.toString()+"\n");
			  Logger.getLogger("default").info(jsonObject.keySet().toString());
			  JSONArray jsonArray = (JSONArray) jsonObject.get("recipes");
			  Logger.getLogger("default").info(jsonArray.size()+" jsonArray size");
			  Logger.getLogger("default").info(jsonArray.get(0).toString());
			  //extract total recipe info from original json respnse
			  HashMap<String,Object> result =
				        new ObjectMapper().readValue(jsonArray.get(0).toString(), HashMap.class);
			  Logger.getLogger("default").info(result.toString());
			  Logger.getLogger("default").info(result.get("id").toString());
			  Logger.getLogger("default").info(result.get("title").toString());
			  
			  //extract ingredients from recipe info
			  ArrayList<HashMap<String,Object>> list = (ArrayList<HashMap<String,Object>>) result.get("extendedIngredients");
			  String[] ingredients = new String[list.size()];
			  Logger.getLogger("default").info(jsonArray.toString() + "\n");
			  for(int i = 0; i < list.size(); i++) {
				  HashMap<String,Object> temp = list.get(i);
				  ingredients[i] = temp.get("originalString").toString();
				  Logger.getLogger("default").info(temp.get("originalString").toString() + "\n");
			  }
			  
			  //extract steps from recipe info, json is a mess for this
			  list = (ArrayList<HashMap<String,Object>>) result.get("analyzedInstructions");
			  Logger.getLogger("default").info(list.toString() + "\n");
			  HashMap<String,Object> temp = list.get(0);
			  Logger.getLogger("default").info(temp.get("steps").getClass().toString());
			  ArrayList<HashMap<String,Object>> steps = (ArrayList<HashMap<String, Object>>) temp.get("steps");
			  String[] instructions = new String[steps.size()];
			  for(int i = 0; i < steps.size(); i++) {
				  temp = steps.get(i);
				  instructions[i] = temp.get("number").toString() + ") " + temp.get("step").toString();
				  Logger.getLogger("default").info(temp.get("number").toString() + ") " + temp.get("step").toString());
			  }
			  
			  Logger.getLogger("default").info(result.get("extendedIngredients").toString());
			  Logger.getLogger("default").info(result.get("analyzedInstructions").toString());			  
			  req.setAttribute("recipeId", result.get("id").toString());
			  req.setAttribute("recipeTitle", result.get("title").toString());
			  req.setAttribute("recipeIngredients", ingredients);
			  req.setAttribute("recipeInstructions", instructions);
	      } catch (ParseException e) {
			  // TODO Auto-generated catch block
	    	  e.printStackTrace();
		  }
	      
	    } else {
		  req.setAttribute("line", conn.getResponseCode() + " " + conn.getResponseMessage());
          Logger.getLogger("default").info(conn.getResponseCode() + " error " + conn.getResponseMessage());
	    }
		
		return req;
	}
	
	public HttpServletRequest parseSpecificRecipe(HttpServletRequest req, HttpURLConnection conn) throws IOException {
		//parse random recipe for id to get recipe info properly
		int respCode = conn.getResponseCode();
		if (respCode == HttpURLConnection.HTTP_OK) {
			
		  req.setAttribute("error", "");
		  StringBuffer response = new StringBuffer();
		  String line;

		  BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		  while ((line = reader.readLine()) != null) {
		    response.append(line);
		  }
		  reader.close();
		  
		  Logger.getLogger("default").info("Response from API: " + response.toString());
		  
		  
		  JSONParser parser = new JSONParser();
		  JSONObject jsonObject;
		  try {
			  jsonObject = (JSONObject) parser.parse(response.toString());
			  Logger.getLogger("default").info(jsonObject.toString()+"\n");
			  Logger.getLogger("default").info(jsonObject.keySet().toString());
			  Logger.getLogger("default").info(jsonObject.size()+" jsonArray size");
			  
			  //extract ingredients from recipe info
			  ArrayList<HashMap<String,Object>> list = (ArrayList<HashMap<String,Object>>) jsonObject.get("extendedIngredients");
			  String[] ingredients = new String[list.size()];
			  Logger.getLogger("default").info(jsonObject.toString() + "\n");
			  for(int i = 0; i < list.size(); i++) {
				  HashMap<String,Object> temp = list.get(i);
				  ingredients[i] = temp.get("originalString").toString();
				  Logger.getLogger("default").info(temp.get("originalString").toString() + "\n");
			  }
			  
			  //extract steps from recipe info, json is a mess for this
			  list = (ArrayList<HashMap<String,Object>>) jsonObject.get("analyzedInstructions");
			  Logger.getLogger("default").info(list.toString() + "\n");
			  HashMap<String,Object> temp = list.get(0);
			  Logger.getLogger("default").info(temp.get("steps").getClass().toString());
			  ArrayList<HashMap<String,Object>> steps = (ArrayList<HashMap<String, Object>>) temp.get("steps");
			  String[] instructions = new String[steps.size()];
			  for(int i = 0; i < steps.size(); i++) {
				  temp = steps.get(i);
				  instructions[i] = temp.get("number").toString() + ") " + temp.get("step").toString();
				  Logger.getLogger("default").info(temp.get("number").toString() + ") " + temp.get("step").toString());
			  }
			  
			  Logger.getLogger("default").info(jsonObject.get("extendedIngredients").toString());
			  Logger.getLogger("default").info(jsonObject.get("analyzedInstructions").toString());			  
			  req.setAttribute("recipeId", jsonObject.get("id").toString());
			  req.setAttribute("recipeTitle", jsonObject.get("title").toString());
			  req.setAttribute("recipeIngredients", ingredients);
			  req.setAttribute("recipeInstructions", instructions);
	      } catch (ParseException e) {
			  // TODO Auto-generated catch block
	    	  e.printStackTrace();
		  }
	      
	    } else {
		  req.setAttribute("line", conn.getResponseCode() + " " + conn.getResponseMessage());
          Logger.getLogger("default").info(conn.getResponseCode() + " error " + conn.getResponseMessage());
		}
		
		return req;
	}

	public void weeklyRandomRecipe(HttpServletRequest req, HttpURLConnection conn) throws IOException {
		int respCode = conn.getResponseCode();
		if (respCode == HttpURLConnection.HTTP_OK) {		
		  req.setAttribute("error", "");
		  StringBuffer response = new StringBuffer();
		  String line;

		  BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		  while ((line = reader.readLine()) != null) {
		    response.append(line);
		  }
		  reader.close();
		  
		  Logger.getLogger("default").info("Response from API: " + response.toString());
		  
		  
		  JSONParser parser = new JSONParser();
		  JSONObject jsonObject;
		  try {
			  jsonObject = (JSONObject) parser.parse(response.toString());
			  JSONArray jsonArray = (JSONArray) jsonObject.get("recipes");
			  //extract total recipe info from original json respnse
			  HashMap<String,Object> result =
				        new ObjectMapper().readValue(jsonArray.get(0).toString(), HashMap.class);

			  weeklyrecipe = result.get("title").toString();
	      } catch (ParseException e) {
			  // TODO Auto-generated catch block
	    	  e.printStackTrace();
		  }
	      
	    } else {
		  req.setAttribute("line", conn.getResponseCode() + " " + conn.getResponseMessage());
          Logger.getLogger("default").info(conn.getResponseCode() + " error " + conn.getResponseMessage());
	    }
		
	}
}
