package servlets;

import com.google.gson.Gson;
import dao.ReferenceDao;
import models.Reference;
import utils.SessionUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


public class ReferencesServlet extends HttpServlet {

    private ReferenceDao referenceDao;


    @Override
    public void init() {
        referenceDao = new ReferenceDao(getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String json = new Gson().toJson(referenceDao.get(request.getParameter("filter")));
            response.setContentType("application/json");
            response.setHeader("sessionId", SessionUtils.getSessionId(request));
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String line = request.getReader().readLine();
            System.out.println("line: " + line);
            Gson gson = new Gson();
            String json = gson.toJson(
                    referenceDao.create(
                            gson.fromJson(line, Reference.class)
                    )
            );
            response.setContentType("application/json");
            response.setHeader("sessionId", SessionUtils.getSessionId(request));
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Gson gson = new Gson();
            String json = gson.toJson(
                    referenceDao.update(
                            gson.fromJson(request.getReader().readLine(), Reference.class)
                    )
            );
            response.setContentType("application/json");
            response.setHeader("sessionId", SessionUtils.getSessionId(request));
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Gson gson = new Gson();
            String json = gson.toJson(
                    referenceDao.delete(Integer.parseInt(request.getParameter("id")))
            );
            response.setContentType("application/json");
            response.setHeader("sessionId", SessionUtils.getSessionId(request));
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
