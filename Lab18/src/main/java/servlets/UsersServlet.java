package servlets;

import com.google.gson.Gson;
import models.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UsersServlet extends HttpServlet {

    private final String ADMIN_USERNAME = "admin";
    private final String ADMIN_PASSWORD = "admin";

    private final String CLIENT_USERNAME = "client";
    private final String CLIENT_PASSWORD = "client";

    private final String RESET_USERNAME = "reset";
    private final String RESET_PASSWORD = "reset";

    private static boolean loggedAsAdmin = false;
    private static boolean loggedIn = false;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect(request.getContextPath());
        loggedIn = true;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String json;
        if (loggedIn) {
            loggedIn = false;
            json = new Gson().toJson("SignIn");
        } else {
            json = request.getReader().readLine();
            User user = new Gson().fromJson(json, User.class);

            if (!loggedAsAdmin) {
                if (user.getUsername().equals(ADMIN_USERNAME) && user.getPassword().equals(ADMIN_PASSWORD)) {
                    json = new Gson().toJson(ADMIN_USERNAME);
                    loggedAsAdmin = true;
                } else if (user.getUsername().equals(CLIENT_USERNAME) && user.getPassword().equals(CLIENT_PASSWORD)) {
                    json = new Gson().toJson(CLIENT_USERNAME);
                } else {
                    json = new Gson().toJson("error");
                }
            } else {
                if (user.getUsername().equals(RESET_USERNAME) && user.getPassword().equals(RESET_PASSWORD)) {
                    HttpSession session = request.getSession();

                    if (!session.isNew()) {
                        session.invalidate();
                        session = request.getSession();
                    }
                    loggedAsAdmin = false;
                    json = new Gson().toJson(CLIENT_USERNAME);
                } else
                    json = new Gson().toJson(ADMIN_USERNAME);
            }
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}
