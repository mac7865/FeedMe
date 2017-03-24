package feedMe;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
	@SuppressWarnings("deprecation")
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Logger.getLogger("log").info("sending emails");
		Query sq = new Query("Subscriber");
	    List<Entity> subscribers =
				    datastore.prepare(sq).asList(FetchOptions.Builder.withDefaults());
	    
	    Calendar c = Calendar.getInstance();
	    c.add(Calendar.HOUR, -24);
	    Date dayStart = c.getTime();
		Query pq = new Query("Post").setFilter( new FilterPredicate( "date", FilterOperator.GREATER_THAN_OR_EQUAL, dayStart ) );
	    List<Entity> posts = datastore.prepare(pq).asList(FetchOptions.Builder.withDefaults());

	    Properties props = new Properties();
	    Session session = Session.getDefaultInstance(props, null);
		for(Entity subscribe: subscribers) {
		   String email = (String) subscribe.getProperty("email");
		   Logger.getLogger("log").info("sending to " + email);

		   String message = "";
		   try {
			      MimeMessage msg = new MimeMessage(session);

			      msg.setSubject("FeedMe Weekly Recipe!");
			      msg.setFrom(new InternetAddress("markcarter25@utexas.edu", "FeedMe Admin"));
			      msg.addRecipient(Message.RecipientType.TO,
			                       new InternetAddress(email, "Awesome Blogger"));
			      message += "Here is your awesome weekly recipe from FeedMe!\n\n";
			      
			      msg.setText(message);
			      Transport.send(msg);
				  Logger.getLogger("log").info(message + " sent");

			   } catch (Exception e) {
				  Logger.getLogger("log").info("failed to send an email");
	           }
	    }
	}
}
