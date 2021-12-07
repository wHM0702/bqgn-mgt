<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">

<head>
    <meta charset="utf-8">
    <title>login</title>

</head>

<body style="text-align: center">
    <h1 style="text-align: center">这是登录</h1>
    <form action="log" method="post">
        电话号：<input type="text" name="phone">
        密码：<input type="password" name="pwd">
        <input type="submit">
    </form>
</body>
</html>