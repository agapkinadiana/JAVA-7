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

public class Sss extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = "Sss servlet";
        System.out.println(message);
        response.getWriter().println(message);

        String param = request.getParameter("param");
        System.out.println("param: " + param);
        if (param != null) {
            String forwardUrl = null;
            if (param.equals("toHtml")) {
                forwardUrl = "/redirect-page-test.html";
            } else if (param.equals("forwardTwice")) {
                forwardUrl = "/ggg?param=" + param;
            } else if (param.equals("gggRequest")) {
                response.getWriter().println(sendGggRequest(request.getParameter("method")));
                return;
            }

            if (forwardUrl != null) {
                response.sendRedirect("/lab3" + forwardUrl);
                /*RequestDispatchesendRedirectr dispatcher = getServletContext().getRequestDispatcher(forwardUrl);
                dispatcher.forward(request, response);*/
                return;
            }
        }

        super.service(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        /*RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ggg");
        dispatcher.forward(request, response);*/
        response.sendRedirect("/lab3/ggg");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ggg");
        dispatcher.forward(request, response);*/
        response.sendRedirect("/lab3/ggg");
    }


    private String sendGggRequest(String method) throws IOException {
        if (method == null) {
            return "No method parameter has been provided";
        }

        URL endpoint = new URL("http://localhost:8080/lab3/ggg?param=gggRequest&method=" + method);
        HttpURLConnection connection = (HttpURLConnection) endpoint.openConnection();
        connection.setRequestMethod(method.toUpperCase());

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String chunk;
        StringBuilder content = new StringBuilder();
        while ((chunk = in.readLine()) != null) {
            content.append(chunk);
        }
        in.close();

        return content.toString();
    }
}
