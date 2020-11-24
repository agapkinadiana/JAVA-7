<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lab6</title>
</head>
<body>
<div>
    <div>
        <form action="/Lab6/gotoUrl" method="get">
            <label>
                Url:
                <input type="number" name="urln" value="1"/>
            </label>
            <input type="submit" value="Goto Url"/>
        </form>
    </div>

    <div>
        <form action="/Lab6/ccc" method="post">
            <label>
                Value1:
                <input type="text" name="value1" value="Hello"/>
            </label>

            <label>
                Value2:
                <input type="text" name="value2" value="World"/>
            </label>

            <label>
                Value3:
                <input type="text" name="value3" value="!"/>
            </label>

            <label>
                CBean:
                <input type="text" name="CBean" value="old"/>
            </label>

            <input type="submit" value="Set CBean"/>
        </form>
    </div>

    <div>
        <form action="/Lab6/Aaa" method="get">
            <button type="submit">Aaa</button>
        </form>
    </div>
</div>
</body>
</html>
