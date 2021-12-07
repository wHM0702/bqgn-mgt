<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">

<head>
    <meta charset="utf-8">
    <title>talk</title>

</head>

<body style="text-align: center">

    <input type="text" id="words">
    <button onclick="fa()">发送</button>
    <br/>
    <h2>聊天记录</h2>
    <hr/>
    <div id="msgs">

    </div>

</body>
<script type="text/javascript">
    var webscoket = new WebSocket("ws://localhost:8081/webscoket")
    webscoket.onopen = function (ev) {
        alert("connected!")
    }
    webscoket.onmessage = function (ev) {
        document.getElementById("msgs").innerHTML+='<br/>'+ev.data;
    }
    function fa() {
        var msg = document.getElementById("words").value;
        webscoket.send(msg);
        msg.innerHTML='';
    }
</script>
</html>