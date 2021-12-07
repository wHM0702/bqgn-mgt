<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">

<head>
    <meta charset="utf-8">
    <title>ziyaun</title>
    <script src="jquery-1.8.3.js" type="text/javascript"></script>
    <script type="text/javascript">
        function xiazai(pathName,rname) {
            $.post("dload",{"pathName":pathName,"rname":rname},function (data) {
                $("#msg").html(data)
            });
        }
    </script>
</head>

<body style="text-align: center">
    <h1 align="center">这是资源列表页</h1>

    <table align="center">
        <tr>
            <td>资源名称</td>
            <td>上传时间</td>
            <td>操作</td>
        </tr>
        <#list ziyuanList as zy>
            <tr>
                <td>${zy.rname}</td>
                <td>${zy.upTime}</td>
<#--                <td><a href="#" onclick="xiazai('${zy.rpath}','${zy.rname}')">下载</a></td>-->
                <td><a href="dload?pathName=${zy.rpath}&rname=${zy.rname}">下载</a></td>
            </tr>
        </#list>
    </table>
    <h3 id="msg"></h3>
</body>
</html>