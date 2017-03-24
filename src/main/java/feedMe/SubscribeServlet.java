package feedMe;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@SuppressWarnings("serial")
public class SubscribeServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {        
        // We have one entity group per Guestbook with all Greetings residing
        // in the same entity group as the Guestbook to which they belong.
        // This lets us run a transactional ancestor query to retrieve all
        // Greetings for a given Guestbook.  However, the write rate to each
        // Guestbook should be limited to ~1/second.

	    Key guestbookKey = KeyFactory.createKey("Guestbook", "default");
        String email = req.getParameter("email");
      
        Entity subscriber = new Entity("Subscriber", guestbookKey);
        subscriber.setProperty("email", email);
        
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        datastore.put(subscriber);

        resp.sendRedirect("/");

    }
}
