package servlets;

import utils.EmailAuthenticator;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

public class EmailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Session session = Session.getDefaultInstance(new Properties());
            //Указываем протокол - IMAP
            Store store = session.getStore("imaps");

            //подключаемся к почтовому серверу
            store.connect(
                    getServletContext().getInitParameter("mailHostInbox"),
                    Integer.parseInt(getServletContext().getInitParameter("mailPortInbox")),
                    getServletContext().getInitParameter("mailUsername"),
                    getServletContext().getInitParameter("mailPassword")
            );

            //получаем папку с входящими сообщениями
            Folder inbox = store.getFolder("INBOX");
            //открываем её только для чтения
            inbox.open(Folder.READ_ONLY);

            Message[] messages = inbox.getMessages();
            StringBuilder messagesTable = new StringBuilder("<table border=\"2\">");
            int page = request.getParameter("page") == null ? 0 : Integer.parseInt(request.getParameter("page"));
            int recordsPerPage = request.getParameter("recordsPerPage") == null ? 10 : Integer.parseInt(request.getParameter("recordsPerPage"));
            for (int i = page * recordsPerPage; i < messages.length && i < recordsPerPage * (page + 1); i++) {
                Message message = messages[i];
                try {
                    messagesTable.append("<tr>")
                            .append("<td>SendDate: ").append(message.getSentDate()).append("</td>")
                            .append("<td>Subject: ").append(message.getSubject()).append("</td>")
                            .append("<td>Content: ").append(message.getContent() == null ? "" : message.getContent()).append("</td>");
                } catch (Exception ignored) { }

            }
            messagesTable.append("</table>");
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("<h1>Count: " + messages.length + "</h1>");
            response.getWriter().println(messagesTable);
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String email = getServletContext().getInitParameter("mailUsername");
            String password = getServletContext().getInitParameter("mailPassword");

            Properties properties = new Properties();
            properties.put("mail.smtp.host", getServletContext().getInitParameter("mailHost"));
            properties.put("mail.smtp.port", getServletContext().getInitParameter("mailPort"));
            properties.put("mail.from", email);
            properties.put("mail.smtp.password", password);
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.debug", "false");

            // Авторизируемся
            Session session = Session.getInstance(properties, new EmailAuthenticator(email, password));
            // Создание объекта сообщения
            MimeMessage message = new MimeMessage(session);

            // Установка атрибутов сообщения
            message.setFrom(new InternetAddress(email));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(request.getParameter("receiver")));
            message.setSubject("Lab15");

            // Установка тела сообщения
            message.setText(request.getParameter("message"));

            // Отправка сообщения
            Transport.send(message);

            response.setContentType("text/html");
            response.getWriter().println("<h1>Message has been successfully sent!</h1>");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
