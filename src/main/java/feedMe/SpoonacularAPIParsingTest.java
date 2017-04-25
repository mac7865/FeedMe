package feedMe;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Arrays;
import java.util.logging.Logger;

import static org.easymock.EasyMock.*;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
public class SpoonacularAPIParsingTest {
	
	

	@Test
	public void parseRandomAndSpecifcTest() throws IOException {
		
		HttpServletRequest reqRandom = createMock(HttpServletRequest.class);
		HttpURLConnection connRandom = SpoonacularAPI.getUniqueInstance().getRandomRecipeConnection();
		reqRandom = SpoonacularAPI.getUniqueInstance().parseRandomRecipe(reqRandom, connRandom);
//		String url = reqRandom.getRequestURL().toString();
//		String recipeString = url.substring(url.lastIndexOf('/')+1, url.length());
//		String title = (String)reqRandom.getAttribute("recipeTitle");
		int recipeId = Integer.parseInt((String)reqRandom.getParameter("recipeId"));
		HttpServletRequest reqSpecific = createMock(HttpServletRequest.class);
		HttpURLConnection connSpecific = SpoonacularAPI.getUniqueInstance().getSpecificRecipeConnection(recipeId);
		reqSpecific = SpoonacularAPI.getUniqueInstance().parseRandomRecipe(reqSpecific, connSpecific);
		
		assertTrue(compareRecipes(reqRandom, reqSpecific));
	}
	
	private boolean compareRecipes(HttpServletRequest random, HttpServletRequest specific) {
		
		if(!random.getAttribute("recipeId").equals(specific.getAttribute("recipeId"))) return false;
		if(!random.getAttribute("recipeTitle").equals(specific.getAttribute("recipeTitle"))) return false;
		if(!Arrays.equals((String[])random.getAttribute("recipeIngredients"), (String[])specific.getAttribute("recipeIngredients"))) return false;
		if(!Arrays.equals((String[])random.getAttribute("recipeInstructions"), (String[])specific.getAttribute("recipeInstructions"))) return false;
		return true;
	}
}
