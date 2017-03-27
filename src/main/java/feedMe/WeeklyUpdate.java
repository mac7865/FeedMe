package feedMe;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.users.User;

@SuppressWarnings("serial")
public class WeeklyUpdate extends HttpServlet {
	public static String weeklyrecipe = "Raw Mocha Coconut Brownie Tarts [Paleo-friendly]"; // weekly featured recipe every subscriber gets
	public static int weeklyrecipeID = 539193; // weekly featured recipe id

	@SuppressWarnings("deprecation")
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//method that will be called by cron job to update the weekly featured recipe
		SpoonacularAPI update = SpoonacularAPI.getUniqueInstance();
		HttpURLConnection conn = SpoonacularAPI.getUniqueInstance().getRandomRecipeConnection();
		update.weeklyRandomRecipe(req, conn);
	}
}
