
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>lab_11</title>
    <script src="scripts/jquery-3.2.1.js"></script>
</head>
<body>
<h1>Headers GET</h1>
<div>
    <div>
        <input type="text" title="" id="x" value="3">
        <input type="text" title="" id="y" value="4">
        <input type="text" title="" id="z" readonly="readonly">
    </div>
    <div>
        <input type="button" value="sum" onclick="sum()">
    </div>
</div>

<h1>Headers POST</h1>
<form name="FHeader" action="#" method="Post">
    <input name="x2" type="text" value="x">
    <input name="y2" type="text" value="y">
    <input id="z2" name="z2" type="text" value="z" readonly="readonly">
    <input type="button" value="sum" onclick="sum2(this.form.x2.value, this.form.y2.value, this.form.z2.value)">
</form>

<h1>XML Rand Numbers</h1>
<div>
    <form action="#" method="post">
        <input name="n" id="n" type="text" placeholder="random value (int)" value="15">
        <div id="result-task-2-1"></div>
        <div id="result-task-2-2"></div>
        <input type="button" value="xml" onclick="getXML(this.form.n.value)">
        <input type="button" value="json" onclick="getJSON(this.form.n.value)">
    </form>
</div>

<div>
    <div>
        <input type="button" value="sync" onclick="getSync()">
        <input type="button" value="async" onclick="getAsync()">
    </div>
</div>

<script>

    function getAsync() {
        sum(true);
        getXML(document.getElementById("n").value, true);
        getJSON(document.getElementById("n").value, true);
    }

    function getSync() {
        sum(false);
        getXML(document.getElementById("n").value, false);
        getJSON(document.getElementById("n").value, false);
    }

    function sum(async) {
        console.log(async);
        const xhr = new XMLHttpRequest();
        xhr.open("GET", "/lab11_war/sss/header", async);
        xhr.setRequestHeader("x", document.getElementById("x").value);
        xhr.setRequestHeader("y", document.getElementById("y").value);
        xhr.send();
        if (async) {
            xhr.onreadystatechange = () => {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    document.getElementById("z").value = xhr.getResponseHeader("z");
                }
            };
        } else {
            document.getElementById("z").value = xhr.getResponseHeader("z");
        }
    }

    function sum2(x,y,z) {
        const xhr = new XMLHttpRequest();
        xhr.open("POST", "/lab11_war/sss/header", true);
        xhr.setRequestHeader("x", x);
        xhr.setRequestHeader("y", y);
        xhr.send();

        xhr.onreadystatechange = () => {
            if (xhr.readyState === 4 && xhr.status === 200) {
                document.getElementById("z2").value = xhr.getResponseHeader("z");
            }
        };
    }

    function getXML(n, async) {
        console.log(async);
        const xhr = new XMLHttpRequest();
        xhr.open("GET", "/lab11_war/sss/xml", async);
        xhr.setRequestHeader("XRand-N", n);
        xhr.send();
        if (async) {
            xhr.onreadystatechange = () => {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    document.getElementById("result-task-2-1").innerHTML = stringifyXML(xhr);
                }
            };
        } else {
            document.getElementById("result-task-2-1").innerHTML = stringifyXML(xhr);
        }
    }

    function getJSON(n, async) {
        console.log(async);
        const xhr = new XMLHttpRequest();
        xhr.open("GET", "/lab11_war/sss/json", async);
        xhr.setRequestHeader("XRand-N", n);
        xhr.send();
        if (async) {
            xhr.onreadystatechange = () => {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    document.getElementById("result-task-2-2").innerHTML = stringifyJSON(xhr);
                }
            };
        } else {
            document.getElementById("result-task-2-2").innerHTML = stringifyJSON(xhr);
        }
    }

    function stringifyXML(req){
        const xmlDoc = req.responseXML;
        const arr = Array.from(xmlDoc.getElementsByTagName("num"));
        return arr.map(number => number.innerHTML).join(" ");
    }

    function stringifyJSON(req){
        const arr = JSON.parse(req.responseText);
        return arr.join(" ");
    }

</script>
</body>
</html>
