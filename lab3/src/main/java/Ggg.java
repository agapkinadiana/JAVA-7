import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Ggg extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = "Ggg servlet";
        System.out.println(message);
        response.getWriter().println(message);

        super.service(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String param = request.getParameter("param");
        if (param != null) {
            if (param.equals("forwardTwice")) {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/redirect-page-test.html");
                dispatcher.forward(request, response);
            } else if (param.equals("gggRequest")) {
                response.getWriter().println("Hello from Ggg. param=" + request.getParameter("param") + ", method: " + request.getParameter("method") + " : " + request.getMethod());
            }
        } else {
            response.getWriter().println("Hello world: " + request.getMethod());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String param = request.getParameter("param");
        if (param != null) {
            response.getWriter().println("Hello from Ggg. param=" + request.getParameter("param") + ", method: " + request.getParameter("method") + " : " + request.getMethod());
        } else {
            response.getWriter().println("Hello world: " + request.getMethod());
        }
    }
}
