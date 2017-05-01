package feedMe;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.Enumeration;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SpecificRecipeServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		//send request to API for specific recipe		
		String url = req.getRequestURL().toString();
		String recipeString = url.substring(url.lastIndexOf('/')+1, url.length());
		Logger.getAnonymousLogger().info(recipeString);
		int recipeId = 0;
		try {
			recipeId = Integer.parseInt(recipeString);
			HttpURLConnection conn = SpoonacularAPI.getUniqueInstance().getSpecificRecipeConnection(recipeId);
			req = SpoonacularAPI.getUniqueInstance().parseSpecificRecipe(req, conn);
			req.getRequestDispatcher("/recipe.jsp").forward(req, resp);
		} catch(Exception e) {
			
		}

	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		//send request to API for specific recipe		
		String url = req.getRequestURL().toString();
		String recipeString = url.substring(url.lastIndexOf('/')+1, url.length());
		
		int recipeId = Integer.parseInt(recipeString);
		HttpURLConnection conn = SpoonacularAPI.getUniqueInstance().getSpecificRecipeConnection(recipeId);
		req = SpoonacularAPI.getUniqueInstance().parseSpecificRecipe(req, conn);
		req.getRequestDispatcher("/recipe.jsp").forward(req, resp);

	}
}
