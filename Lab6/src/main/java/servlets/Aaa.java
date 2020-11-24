package servlets;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Aaa extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        PostMethod postRequest = new PostMethod("http://localhost:8080" + request.getContextPath() + "/Bbb");

        postRequest.setParameter("first", "1");
        postRequest.setParameter("second", "2");
        postRequest.setParameter("third", "3");

        postRequest.setRequestHeader("first", "1");
        postRequest.setRequestHeader("second", "2");
        postRequest.setRequestHeader("third", "3");

        new HttpClient().executeMethod(postRequest);

        response.getWriter().println("<h1>Body:</h1>" + postRequest.getResponseBodyAsString());
        response.getWriter().println("<h1>Headers from response:<br/></h1>");

        for (Header header : postRequest.getResponseHeaders()) {
            response.getWriter().println("- " + header + "<br>");
        }

        response.setContentType("text/html");
    }
}