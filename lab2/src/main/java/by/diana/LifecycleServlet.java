package by.diana;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LifecycleServlet extends HttpServlet {

    private final StringBuilder lifecycleString;

    public LifecycleServlet() {
        super();

        String message = "Constructor created\n";
        this.lifecycleString = new StringBuilder(message);
        System.out.println(message);
    }


    @Override
    public void init() throws ServletException {
        String message = "init execution\n";
        System.out.println(message);
        this.lifecycleString.append(message);

        super.init();
    }

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        String message = "service execution\n";
        System.out.println(message);
        this.lifecycleString.append(message);

        super.service(request, response);
        //respond(request, response);
    }

    @Override
    public void destroy() {
        String message = "destroy execution\n";
        System.out.println(message);
        this.lifecycleString.append(message);

        super.destroy();
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        respond(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        respond(request, response);
    }


    private void respond(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String urlParams = "firstname: " + request.getParameter("firstname") + "; lastname: " + request.getParameter("lastname");
        response.getWriter().println(this.lifecycleString.toString() + " >>>>> " + getClientInfo(request) + " >>>>> " + urlParams);
    }

    private String getClientInfo(HttpServletRequest request) {
        return request.getMethod() + ", ServerName: " + request.getServerName() + ", HostIp: " + request.getRemoteAddr();
    }
}
