<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">

<head>
    <meta charset="utf-8">
    <title>index</title>

</head>

<body style="text-align: center">

    <h2>教学资料上传</h2>
    <form action="/uploadQN2" method="post" enctype="multipart/form-data">
        课程文档：<input name="mufile" type="file"><br/>
        <input type="submit">
    </form>

    <h2><a href="initZiyuan">查看所有资源</a></h2>
</body>

</html>