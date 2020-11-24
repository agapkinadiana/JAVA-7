package servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Bbb extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.getWriter().println("First parameter: " + request.getParameter("first") + "<br/>");
        response.getWriter().println("Second parameter: " + request.getParameter("second") + "<br/>");
        response.getWriter().println("Third parameter: " + request.getParameter("third") + "<br/>");

        response.getWriter().println("First header: " + request.getHeader("first") + "<br/>");
        response.getWriter().println("Second header: " + request.getHeader("second") + "<br/>");
        response.getWriter().println("Third header: " + request.getHeader("third") + "<br/>");

        response.setContentType("text/html");
        response.setHeader("First_Header", "1");
        response.setHeader("Second_Header", "2");
    }
}
