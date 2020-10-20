<%@ taglib prefix="ads" uri="taglib.tdl" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lab5</title>
</head>
<body>
<div>
    <ads:dossier endpoint="/lab5/serv/ttt">
        <ads:lastname name="last-name-tag" initValue="Diana"/>
        <ads:surname name="surname-tag" initValue="Diana"/>
        <ads:sex name="sex-tag"/>
        <ads:submit type="ok"/>
        <ads:submit type="cancel"/>
    </ads:dossier>
</div>
</body>
</html>
