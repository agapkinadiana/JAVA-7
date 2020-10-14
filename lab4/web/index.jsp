<%@ page import="java.util.Date" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.util.logging.Logger" %>
<%@ page import="java.util.Objects" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Lab4</title>
</head>
<body>
<div>
    <%
        Logger logger = Logger.getLogger("com.anexinet.sample");
        int hours = new Date().getHours();
        String jspPageName = "";
        String welcomeMessage = "";
        if (hours > 0 && hours < 6) {
            welcomeMessage = "Good Night";
            jspPageName = "night.jsp";
        } else if (hours > 6 && hours < 12) {
            welcomeMessage = "Good Morning";
            jspPageName = "morning.jsp";
        } else if (hours > 12 && hours < 18) {
            welcomeMessage = "Good Afternoon";
            jspPageName = "afternoon.jsp";
        } else if (hours > 18 && hours < 24) {
            welcomeMessage = "Good Evening";
            jspPageName = "evening.jsp";
        }

        logger.info(welcomeMessage);
        System.out.println(welcomeMessage);

    %>
    <div>
        <h1><%= welcomeMessage %></h1>
    </div>
    <div>
        <%
            StringBuilder tableMarkup = new StringBuilder("<br/><br/>");
            tableMarkup.append("<table>");

            LocalDateTime localDateTime = LocalDateTime.now();
            for (int i = 0; i < 7; i++) {
                LocalDateTime date = localDateTime.plusDays(i);
                tableMarkup.append("<tr>");
                tableMarkup.append("<td>").append(date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))).append("</td>");
                tableMarkup.append("<td>").append(date.getDayOfWeek().getValue()).append("</td>");
                tableMarkup.append("</tr>");
            }
            tableMarkup.append("</table>");
        %>
        <%= tableMarkup.toString() %>
    </div>
    <br/>

    <div>
        <%
            String includedPageName = request.getParameter("include-page");
            if (includedPageName != null) {
                logger.info("Opening " + includedPageName + "...");
                System.out.println("Opening " + includedPageName + "...");
                if (includedPageName.equals("afternoon.jsp")) {
                    includedPageName = "/afternoon";
                    logger.info("Requesting the servlet " + includedPageName + "...");
                    System.out.println("Requesting the servlet " + includedPageName + "...");
                }
        %>
        <%--<jsp:include page="<%= includedPageName %>"/>--%>
        <%--<jsp:forward page="<%= includedPageName %>"/>--%>
        <%
            }
        %>

        <%if (Objects.equals(includedPageName, "night.jsp")) {%>
        <%@include file="night.jsp" %>
        <%} else if (Objects.equals(includedPageName, "morning.jsp")) {%>
        <%@include file="morning.jsp" %>
        <%} else if (Objects.equals(includedPageName, "afternoon.jsp") || Objects.equals(includedPageName, "/afternoon")) {%>
        <%@include file="afternoon.jsp" %>
        <%} else if (Objects.equals(includedPageName, "evening.jsp")) {%>
        <%@include file="evening.jsp" %>
        <%}%>
    </div>

    <form action="${pageContext.request.contextPath}" method="get">
        <input type="hidden" name="include-page" value="<%= jspPageName %>">
        <button type="submit">Press</button>
    </form>

    <form action="${pageContext.request.contextPath}/jjj" method="get">
        <input type="hidden" name="jspPageName" value="<%= jspPageName %>">
<%--        <input type="hidden" name="redirect" value="1">--%>
        <input type="hidden" name="redirect">
        <input type="hidden" name="method" value="GET">
        <button type="submit">Jjj</button>
    </form>
</div>
</body>
</html>
