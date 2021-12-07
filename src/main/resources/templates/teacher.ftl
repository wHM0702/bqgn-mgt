<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">

<head>
    <meta charset="utf-8">
    <title>index</title>

</head>

<body>
    <h1>教员：${user.userName}</h1>

    <script type="text/javascript">
        var webscoket = new WebSocket("ws://localhost:8081/webscoket")
        webscoket.onopen = function (ev) {
        }

        webscoket.onmessage = function (ev) {
            alert("校长发通知了"+ev.data)
        }
    </script>
</body>
</html>