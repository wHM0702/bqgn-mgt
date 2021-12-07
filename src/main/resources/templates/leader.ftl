<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">

<head>
    <meta charset="utf-8">
    <title>index</title>

</head>

<body>
    <h1>领导：${user.userName}</h1>

    <h2>发布人员公告</h2>
    <hr/>
    <input type="text" id="words">
    <button onclick="fabu()">发布</button>

    <script type="text/javascript">

        var webscoket = new WebSocket("ws://localhost:8081/webscoket")
        /*  ev就是Event 对象的缩写
       Event 对象代表事件的状态，比如事件在其中发生的元素、键盘按键的状态、鼠标的位置、鼠标按钮的状态。
       事件通常与函数结合使用，函数不会在事件发生前被执行！*/
        webscoket.onopen = function (ev) {
            alert("connected!")
        }

        webscoket.onmessage = function (ev) {
            alert("收到消息")
        }

        function fabu() {
            var words = document.getElementById("words").value;
            webscoket.send(words);
        }
    </script>
</body>
</html>