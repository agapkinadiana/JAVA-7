<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lab6</title>
</head>
<body>
<%
    String url1 = getServletConfig().getServletContext().getInitParameter("URL1");
    String url2 = getServletConfig().getServletContext().getInitParameter("URL2");
%>
<div>
    <div>
        <a href="<%="/Lab6" + url1%>"><%=url1%></a>
    </div>
    <div>
        <a href="<%="/Lab6" + url2%>"><%=url2%></a>
    </div>
</div>
</body>
</html>
