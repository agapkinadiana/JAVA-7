package listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class L1 implements ServletContextListener {

    public void contextInitialized(ServletContextEvent event) {
        System.out.println("WebApp context has been initialized");
    }

    public void contextDestroyed(ServletContextEvent event) {
        System.out.println("WebApp context has been destroyed");
    }
}
