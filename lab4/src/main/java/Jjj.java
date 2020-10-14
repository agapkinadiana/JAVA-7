import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Jjj extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.getWriter().println("From Jjj");
        String redirectParam = request.getParameter("redirect");
        if (redirectParam == null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(getPagePath(request));
            dispatcher.forward(request, response);
        } else {
            response.setHeader("Content-Type", "text/html");
            response.getWriter().println(callJsp(request));
        }
    }


    private String callJsp(HttpServletRequest request) throws IOException {
        String method = request.getParameter("method");
        if (method == null) {
            method = "GET";
        }
        URL endpoint = new URL("http://localhost:8080/" + request.getContextPath() + getPagePath(request));
        HttpURLConnection connection = (HttpURLConnection) endpoint.openConnection();
        connection.setRequestMethod(method.toUpperCase());

        BufferedReader responseBody = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String chunk;
        StringBuilder content = new StringBuilder();
        while ((chunk = responseBody.readLine()) != null) {
            content.append(chunk);
        }
        responseBody.close();

        return content.toString();
    }

    private String getPagePath(HttpServletRequest request) {
        String targetPageName = request.getParameter("jspPageName");
        return targetPageName == null ? "/" : "/" + targetPageName;
    }
}
