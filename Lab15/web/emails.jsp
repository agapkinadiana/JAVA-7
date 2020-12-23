<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Lab15</title>
</head>
<body>
<div>
    <div>
        <button onclick="prev()">Prev</button>
        <button onclick="next()">Next</button>
    </div>
    <div id="emails"></div>
</div>
<script>
    const EMAILS_INBOX_URL = "http://localhost:8080/Lab15/mail";
    let page = 0;
    let recordsPerPage = 10;

    load();

    function load() {
        fetch(EMAILS_INBOX_URL + `?page=` + page + `&recordsPerPage=` + recordsPerPage)
        .then(response => response.text())
        .then(response => {
            document.getElementById("emails").innerHTML = response;
        });
    }

    function prev() {
        if (page > 0) {
            page--;
            load();
        }
    }

    function next() {
        page++;
        load();
    }
</script>
</body>
</html>
