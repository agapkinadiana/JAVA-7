package servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Logger;

public class Sss extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //Получения  значения параметра инициации
        String path = getServletContext().getInitParameter("filesPath");
        String fileName = request.getParameter("file");
        System.out.println(path);

        String message = "Servlet:Sss";
        System.out.println(message);

        if (fileName != null) {
            Logger logger = Logger.getLogger("com.anexinet.sample");
            logger.info(fileName);
            File file = new File(path + "//" + fileName);
            response.setContentType("application/msword");
            //это заголовок, указывающий, будет ли контент отображаться встроенным в браузере,
            //то есть как веб-страница или как часть веб-страницы, или как вложение , которое загружается и сохранены локально
            response.addHeader("Content-Disposition", "attachment; filename=" + file.getName());
            response.setContentLength((int) file.length());

            BufferedInputStream buf = new BufferedInputStream(new FileInputStream(file));
            int readBytes;
            while ((readBytes = buf.read()) != -1) {
                response.getOutputStream().write(readBytes);
            }
        }
    }
}
