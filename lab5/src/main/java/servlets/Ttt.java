package servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Ttt extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        out.append("<div>").append(request.getParameter("last-name-tag")).append("</div>");
        out.append("<div>").append(request.getParameter("surname-tag")).append("</div>");
        out.append("<div>").append(request.getParameter("sex-tag")).append("</div>");

        response.setHeader("Content-Type", "text/html");
    }
}
