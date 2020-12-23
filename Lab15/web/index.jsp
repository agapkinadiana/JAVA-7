<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Lab15</title>
</head>
<body>
<div>
    <form action="/Lab15/mail" method="post">
        <div>
            <label>
                Receiver:
                <input type="email" required name="receiver">
            </label>
        </div>
        <br/>
        <div>
            <label>
                Message:
                <input type="text" required name="message">
            </label>
        </div>
        <br/>
        <div>
            <button type="submit" style="background: aquamarine; width: 100px;font-size: 1.5rem;">Send</button>
        </div>
    </form>
    <div>
        <a href="/Lab15/emails.jsp">Read Messages</a>
    </div>
</div>
</body>
</html>
