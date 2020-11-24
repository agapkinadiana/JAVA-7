package servlets;

import beans.CBean;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Ccc extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        getServletContext().setAttribute("atrCBean", new CBean());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        respond(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        respond(request, response);
    }


    private void respond(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String value1 = request.getParameter("value1");
        String value2 = request.getParameter("value2");
        String value3 = request.getParameter("value3");
        String cBeanAction = request.getParameter("CBean");
        CBean cBean = null;
        String message = "No required parameters were provided";
        if (cBeanAction != null ) {
            if (cBeanAction.equals("new")) {
                cBean = new CBean(value1, value2, value3);
                message = "CBean instance has been successfully created";
            } else if (cBeanAction.equals("old")) {
                //cBean = (CBean) getServletContext().getAttribute("atrCBean");
                //cBean = (CBean) request.getAttribute("atrCBean");
                cBean = (CBean) session.getAttribute("atrCBean");
                if (value1 != null && !value1.equals("")) {
                    cBean.setValue1(value1);
                }
                if (value2 != null && !value2.equals("")) {
                    cBean.setValue2(value2);
                }
                if (value3 != null && !value3.equals("")) {
                    cBean.setValue3(value3);
                }
                message = "CBean instance has been successfully overwritten";
            }
        }

        if (cBean != null) {
            //getServletContext().setAttribute("atrCBean", cBean);
            //request.setAttribute("atrCBean", cBean);
            session.setAttribute("atrCBean", cBean);
        }
        response.getWriter().println(message);

        request.getRequestDispatcher("/Ccc.jsp").forward(request, response);
    }
}
