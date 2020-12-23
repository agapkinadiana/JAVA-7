<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Lab12</title>
</head>
<body>
<div>
    <form method="post" action="j_security_check">
        <div>
            <label>
                username:
                <input type="text" name="j_username"/>
            </label>
        </div>
        <div>
            <label>
                password:
                <input type="password" name="j_password"/>
            </label>
        </div>
        <button type="submit">Login</button>
    </form>
</div>
</body>
</html>
