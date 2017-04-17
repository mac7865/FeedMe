package feedMe;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.appengine.repackaged.org.codehaus.jackson.map.ObjectMapper;


public class SpoonacularAPI {
	
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
			  JSONArray jsonArray = (JSONArray) jsonObject.get("recipes");
			  //extract total recipe info from original json respnse
			  HashMap<String,Object> result =
				        new ObjectMapper().readValue(jsonArray.get(0).toString(), HashMap.class);
			  
			  //extract ingredients from recipe info
			  ArrayList<HashMap<String,Object>> list = (ArrayList<HashMap<String,Object>>) result.get("extendedIngredients");
			  String[] ingredients = new String[list.size()];

			  for(int i = 0; i < list.size(); i++) {
				  HashMap<String,Object> temp = list.get(i);
				  ingredients[i] = temp.get("originalString").toString();
			  }
			  
			  //extract steps from recipe info, json is a mess for this
			  list = (ArrayList<HashMap<String,Object>>) result.get("analyzedInstructions");
			  HashMap<String,Object> temp = list.get(0);
			  ArrayList<HashMap<String,Object>> steps = (ArrayList<HashMap<String, Object>>) temp.get("steps");
			  String[] instructions = new String[steps.size()];
			  for(int i = 0; i < steps.size(); i++) {
				  temp = steps.get(i);
				  instructions[i] = temp.get("number").toString() + ") " + temp.get("step").toString();
			  }
			  
			  		  
			  req.setAttribute("recipeId", result.get("id").toString());
			  req.setAttribute("recipeTitle", result.get("title").toString());
			  req.setAttribute("recipeIngredients", ingredients);
			  req.setAttribute("recipeInstructions", instructions);
			  Logger.getAnonymousLogger().info(result.get("image").toString());
			  System.out.println(result.get("image").toString());
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

			  //extract ingredients from recipe info
			  ArrayList<HashMap<String,Object>> list = (ArrayList<HashMap<String,Object>>) jsonObject.get("extendedIngredients");

			  String[] ingredients = new String[list.size()];
			  for(int i = 0; i < list.size(); i++) {
				  HashMap<String,Object> temp = list.get(i);
				  ingredients[i] = temp.get("originalString").toString();
			  }
			  //extract steps from recipe info, json is a mess for this
			  list = (ArrayList<HashMap<String,Object>>) jsonObject.get("analyzedInstructions");
			  String[] instructions = new String[0];
			  if(list.size() > 0) {
				  HashMap<String,Object> temp = list.get(0);
				  ArrayList<HashMap<String,Object>> steps = (ArrayList<HashMap<String, Object>>) temp.get("steps");
				  instructions = new String[steps.size()];
				  for(int i = 0; i < steps.size(); i++) {
					  temp = steps.get(i);
					  instructions[i] = temp.get("number").toString() + ") " + temp.get("step").toString();
				  }
			  }
			  System.out.println(jsonObject.get("image").toString());
			  req.setAttribute("recipeId", jsonObject.get("id").toString());
			  req.setAttribute("recipeTitle", jsonObject.get("title").toString());
			  req.setAttribute("recipeIngredients", ingredients);
			  req.setAttribute("recipeInstructions", instructions);
			  req.setAttribute("image", jsonObject.get("image").toString());			  
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
			  HashMap<String,Object> result = new ObjectMapper().readValue(jsonArray.get(0).toString(), HashMap.class);
			  WeeklyUpdate.weeklyrecipe = result.get("title").toString();
			  WeeklyUpdate.weeklyrecipeID = (int) result.get("id");
	      } catch (ParseException e) {
			  // TODO Auto-generated catch block
	    	  e.printStackTrace();
		  }
	      
	    } else {
		  req.setAttribute("line", conn.getResponseCode() + " " + conn.getResponseMessage());
          Logger.getLogger("default").info(conn.getResponseCode() + " error " + conn.getResponseMessage());
	    }
		
	}
	
	public HttpURLConnection getComplexSearchConnection(URL url) throws IOException {
		//HttpResponse<JsonNode> response = Unirest.get("https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/searchComplex?fillIngredients=false&limitLicense=false&number=5&offset=23&query=chicken&ranking=1")
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestProperty("X-Mashape-Key", "IveATqgidUmshh51JwkUjJa2kGAgp1wfynojsn358NrsAalt2G");
		conn.setRequestProperty("Accept", "application/json");			
		return conn;
	}
	
	public HttpServletRequest parseSearchResults(HttpServletRequest req, HttpURLConnection conn) throws IOException {
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
			Logger.getAnonymousLogger().info("Response from API: " + response.toString());
			ArrayList<Long> recipeIDs = new ArrayList<Long>();
			
			JSONParser parser = new JSONParser();
			JSONObject jsonObject;
			try {
				jsonObject = (JSONObject) parser.parse(response.toString());

				//extract recipeIDs from list of results
				JSONArray list = (JSONArray) jsonObject.get("results");

				for(int i = 0; i < list.size(); i++) {
					jsonObject = (JSONObject) list.get(i);
					recipeIDs.add((Long) jsonObject.get("id"));
				}
				
				//request summaries for each recipe that was returned
				ArrayList<String> recipes = new ArrayList<String>();
				ArrayList<String> summaries = new ArrayList<String>();
				for(Long recipeID : recipeIDs) {
					//send request to API for specific recipe based on recipe id to get recipe summary
					URL url = new URL("https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/" + recipeID + "/summary");
				    HttpURLConnection conn2 = (HttpURLConnection) url.openConnection();				
					conn2.setRequestProperty("X-Mashape-Key", "IveATqgidUmshh51JwkUjJa2kGAgp1wfynojsn358NrsAalt2G");
					conn2.setRequestProperty("Accept", "application/json");
					respCode = conn2.getResponseCode();
					if (respCode == HttpURLConnection.HTTP_OK) {		
						req.setAttribute("error", "");
						response = new StringBuffer();
						reader = new BufferedReader(new InputStreamReader(conn2.getInputStream()));
						while ((line = reader.readLine()) != null) {
							response.append(line);
						}
						
						reader.close();
						Logger.getAnonymousLogger().info("Response from API: " + response.toString());
						jsonObject = (JSONObject) parser.parse(response.toString());
						recipes.add((String)jsonObject.get("title"));
						summaries.add((String)jsonObject.get("summary"));
					} else {
						Logger.getLogger("default").info(conn.getResponseCode() + " error " + conn.getResponseMessage());
					}
				}

				ArrayList<String> temp = new ArrayList<String>();
				for(String s : summaries) {
					//redirect spoonacular hrefs to our site
					String newS = "";
					for(int i = 0; i < s.length(); i++) {
						if(s.charAt(i) == '\"') {
							int j = s.indexOf('\"', i+1);
							int x = j;
							j--;
							while(s.charAt(j) <= '9' && s.charAt(j) >= '0') {
								j--;
							}
							j++;
							newS += "\"/recipe/" + s.substring(j, x+2);
							i = x+1;
						} else {
							newS += s.charAt(i);
						}
					}
					temp.add(newS);
				}
				summaries = temp;
				req.setAttribute("recipes", recipes);
				req.setAttribute("summaries", summaries);
				req.setAttribute("recipeIDs", recipeIDs);
		      } catch (ParseException e) {
				  // TODO Auto-generated catch block
		    	  e.printStackTrace();
			  }
		}  else {
		        Logger.getLogger("default").info(conn.getResponseCode() + " error " + conn.getResponseMessage());
		}
		return req;
	}
}
