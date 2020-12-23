package servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String x = request.getParameter("x");
        String y = request.getParameter("y");

        response.getWriter().println("X: " + x);
        response.getWriter().println("Y: " + y);
    }
}
