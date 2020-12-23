package servlets;

import com.google.gson.Gson;
import dao.CommentDao;
import models.Comment;
import utils.SessionUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


public class CommentsServlet extends HttpServlet {

    private CommentDao commentDao;


    @Override
    public void init() {
        commentDao = new CommentDao(getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String json = new Gson().toJson(commentDao.get(Integer.parseInt(request.getParameter("referenceId"))));
            response.setContentType("application/json");
            response.setHeader("sessionId", SessionUtils.getSessionId(request));
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Gson gson = new Gson();
            String json = gson.toJson(
                    commentDao.create(
                            gson.fromJson(request.getReader().readLine(), Comment.class)
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
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Gson gson = new Gson();
            String json = gson.toJson(
                    commentDao.update(
                            gson.fromJson(request.getReader().readLine(), Comment.class)
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
                    commentDao.delete(
                            Integer.parseInt(request.getParameter("id"))
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
}
