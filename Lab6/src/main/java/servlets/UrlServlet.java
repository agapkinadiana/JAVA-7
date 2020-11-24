package servlets;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UrlServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String urlN = request.getParameter("urln");
        ServletContext context = getServletContext();
        String url = context.getInitParameter("URL" + urlN);

        if (url == null) {
            response.getWriter().println(String.format("Parameter URL%s is not found", urlN));
        } else {
            String endpoint = "http://localhost:8080" + request.getContextPath() + url;
            HttpClient client = new HttpClient();
            GetMethod method = new GetMethod(endpoint);
            client.executeMethod(method);
            response.getWriter().println(method.getResponseBodyAsString());
        }
    }
}
