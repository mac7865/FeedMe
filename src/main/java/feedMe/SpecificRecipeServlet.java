package feedMe;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SpecificRecipeServlet extends HttpServlet {
public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		//send request to API for random recipe		
		System.out.println(req.getAttribute("recipeID"));
		int recipeId = Integer.parseInt(req.getParameter("recipeID"));
		HttpURLConnection conn = SpoonacularAPI.getUniqueInstance().getSpecificRecipeConnection(recipeId);
		req = SpoonacularAPI.getUniqueInstance().parseSpecificRecipe(req, conn);
		req.getRequestDispatcher("/recipe.jsp").forward(req, resp);

	}
}
