<%@ page import="utils.Choice" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Lab13</title>
</head>
<body>
<div>
    <div>
        <h1>Index</h1>
    </div>
    <div>
        <div>
            <a href="/Lab13/sss">Sss</a>
        </div>
        <div>
            <%
                String path = config.getServletContext().getInitParameter("filesPath");
                Choice choice = new Choice();
                for (String filePath: choice.getChoices(path, "doc")) {
            %>
                <div>
                    <a href="/Lab13/sss?file=<%=filePath%>"><%=filePath%></a>
                </div>
            <%}%>
        </div>
    </div>
</div>
</body>
</html>
