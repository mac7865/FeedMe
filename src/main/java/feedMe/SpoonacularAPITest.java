package feedMe;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.HttpURLConnection;

import org.junit.Test;

public class SpoonacularAPITest {

	@Test
	public void testGetRandomRecipeConnectionURL() throws IOException {
		HttpURLConnection conn = SpoonacularAPI.getUniqueInstance().getRandomRecipeConnection();
		assertTrue(conn.getURL().toString().equals("https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/random?limitLicense=false&number=1&tags=vegetarian%2Cdessert"));
	}
	
	@Test 
	public void testGetRandomRecipeConnectionKey() throws IOException {
		HttpURLConnection conn = SpoonacularAPI.getUniqueInstance().getRandomRecipeConnection();
		assertTrue(conn.getRequestProperty("X-Mashape-Key").toString().equals("IveATqgidUmshh51JwkUjJa2kGAgp1wfynojsn358NrsAalt2G"));
	}
	
	@Test
	public void testGetRandomRecipeConnectionAcceptProperty() throws IOException {
		HttpURLConnection conn = SpoonacularAPI.getUniqueInstance().getRandomRecipeConnection();
		assertTrue(conn.getRequestProperty("Accept").toString().equals("application/json"));
	}

	@Test
	public void testGetSpecificRecipeConnectionURL() throws IOException{
		HttpURLConnection conn = SpoonacularAPI.getUniqueInstance().getSpecificRecipeConnection(479101);
		assertTrue(conn.getURL().toString().equals("https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/479101/information"));
	}
	
	@Test
	public void testGetSpecificRecipeConnectionKey() throws IOException{
		HttpURLConnection conn = SpoonacularAPI.getUniqueInstance().getSpecificRecipeConnection(479101);
		assertTrue(conn.getRequestProperty("X-Mashape-Key").toString().equals("IveATqgidUmshh51JwkUjJa2kGAgp1wfynojsn358NrsAalt2G"));
	}
	
	@Test
	public void testGetSpecificRecipeConnectionAcceptProperty() throws IOException{
		HttpURLConnection conn = SpoonacularAPI.getUniqueInstance().getSpecificRecipeConnection(479101);
		assertTrue(conn.getRequestProperty("Accept").toString().equals("application/json"));
	}

}
