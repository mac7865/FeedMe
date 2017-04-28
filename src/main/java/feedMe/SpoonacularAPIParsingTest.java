package feedMe;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;

import static org.easymock.EasyMock.*;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;

public class SpoonacularAPIParsingTest {
	
	@Test
	public void parseSpecificTest()
	  throws IOException {
	   //get and parse a known recipe
	   HttpURLConnection connection = SpoonacularAPI.getUniqueInstance().getSpecificRecipeConnection(100);	   
	   HttpServletRequest request = mock(HttpServletRequest.class);  
	   request = SpoonacularAPI.getUniqueInstance().parseSpecificRecipe(request, connection);
       
       String[] ingredients = (String[])request.getAttribute("recipeIngredients");
       String[] instructions = (String[])request.getAttribute("recipeInstructions");
       
       //assert that returned values are equal to expected value for this recipe
       assert(request.getAttribute("recipeId").toString().equals("100"));	   
       assert(request.getAttribute("recipeTitle").toString().equals("Pan-Seared Skirt Steak with Anchovies and Lime"));

       assert(instructions[0].equals("1) Rub the steaks with the lime juice and salt and pepper."));
       assert(instructions[1].equals("2) Let stand for 10 minutes."));
       assert(instructions[2].equals("3) In a skillet, heat the olive oil."));
       assert(instructions[3].equals("4) Add the steaks and cook over moderately high heat, turning once, until medium-rare, 4 minutes; transfer to plates and keep warm."));
       assert(instructions[4].equals("5) Add the anchovies, scallions and garlic to the skillet and cook over moderate heat, stirring, 30 seconds."));
       assert(instructions[5].equals("6) Add the water and simmer until the sauce has thickened, scraping up the browned bits from the bottom, 30 seconds."));
       assert(instructions[6].equals("7) Pour the sauce over the steaks and serve with lime wedges."));
       
       assert(ingredients[0].equals("1 2-ounce can anchovy fillets packed in oil, drained and minced"));
       assert(ingredients[1].equals("1 large garlic clove, minced"));
       assert(ingredients[2].equals("Kosher salt and freshly ground pepper"));
       assert(ingredients[3].equals("1 tablespoon fresh lime juice, plus lime wedges for serving"));
       assert(ingredients[4].equals("1 tablespoon extra-virgin olive oil"));
       assert(ingredients[5].equals("2 scallions, minced"));
       assert(ingredients[6].equals("4 6-ounce skirt steaks"));
       assert(ingredients[7].equals("1/4 cup water"));
       
       assert(request.getAttribute("image").toString().equals("https://spoonacular.com/recipeImages/pan-seared-skirt-steak-with-anchovies-and-lime-100.jpg"));
	}
	
	@Test
	public void parseRandomTest() throws IOException {
		//get and parse a random recipe
		HttpURLConnection connection = SpoonacularAPI.getUniqueInstance().getRandomRecipeConnection();	   
		HttpServletRequest request = mock(HttpServletRequest.class);  
		request = SpoonacularAPI.getUniqueInstance().parseRandomRecipe(request, connection);
	    String[] ingredients = (String[])request.getAttribute("recipeIngredients");
	    String[] instructions = (String[])request.getAttribute("recipeInstructions");
		
		System.out.println(request.getAttribute("recipeId"));
		
		//get and parse the specific recipe chosen by the random recipe
		HttpURLConnection connectionKnown = SpoonacularAPI.getUniqueInstance().getSpecificRecipeConnection(Integer.parseInt(request.getAttribute("recipeId").toString()));	   
		HttpServletRequest requestKnown = mock(HttpServletRequest.class);  
		request = SpoonacularAPI.getUniqueInstance().parseSpecificRecipe(requestKnown, connectionKnown);
	    String[] ingredientsKnown = (String[])requestKnown.getAttribute("recipeIngredients");
	    String[] instructionsKnown = (String[])requestKnown.getAttribute("recipeInstructions");
	    
	  //assert that returned values from random are equal to expected value from specific recipe
	  assert(request.getAttribute("recipeId").toString().equals(requestKnown.getAttribute("recipeId").toString()));	   
	  assert(request.getAttribute("recipeTitle").toString().equals(requestKnown.getAttribute("recipeTitle").toString()));
	  assert(request.getAttribute("image").toString().equals(requestKnown.getAttribute("image").toString()));
	  
	  assert(ingredients.length == ingredientsKnown.length);
	  for(int i = 0; i < ingredients.length; i++) {
		  assert(ingredients[i].equals(ingredientsKnown[i]));
	  }
	  
	  assert(instructions.length == instructionsKnown.length);
	  for(int i = 0; i < instructions.length; i++) {
		  assert(instructions[i].equals(instructionsKnown[i]));
	  }
	}

	@Test
	public void parseSearchResultsTest() throws IOException {
		//get recipes that contain the query Chicken
		URL testURL = new URL("https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/searchComplex?fillIngredients=false&limitLicense=false&number=3&offset=77&query=chicken&ranking=1");
		HttpURLConnection connection = SpoonacularAPI.getUniqueInstance().getComplexSearchConnection(testURL);
		HttpServletRequest request = mock(HttpServletRequest.class);  
		request = SpoonacularAPI.getUniqueInstance().parseSearchResults(request, connection);
		//extract appropriate attributes
		ArrayList<String> recipes = (ArrayList<String>)request.getAttribute("recipes");
		ArrayList<Long> recipeIDs = (ArrayList<Long>)request.getAttribute("recipeIDs");
		ArrayList<String> summaries = (ArrayList<String>)request.getAttribute("summaries");
		
		//assert that proper number of recipes were returned and each recipe contains the query specified
		assert(recipes.size() == 3);
		for(int i = 0; i < summaries.size(); i++) {
			assert(summaries.get(i).contains("chicken") || summaries.get(i).contains("Chicken"));
		}
	}
}
