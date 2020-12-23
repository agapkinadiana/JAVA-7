import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SssHeader", urlPatterns = "/sss/header")
public class SssHeader extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Integer x = Integer.parseInt(request.getHeader("x"));
            Integer y = Integer.parseInt(request.getHeader("y"));
            Integer z = x + y;

            Thread.sleep(10000);

            response.setHeader("z", z.toString());
        } catch (Exception e) {
            response.getWriter().println(e.getMessage());
        }
    }
}
