package listeners;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class L2 implements HttpSessionAttributeListener {

    public void attributeAdded(HttpSessionBindingEvent event) {
        System.out.println("Session attribute has been added");
    }

    public void attributeReplaced(HttpSessionBindingEvent event) {
        System.out.println("Session attribute has been modified");
    }

    public void attributeRemoved(HttpSessionBindingEvent event) {
        System.out.println("Session attribute has been removed");
    }
}
