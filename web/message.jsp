<%--
  Created by IntelliJ IDEA.
  User: cellargalaxy
  Date: 17-8-19
  Time: 下午11:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>信息</title>
    <link rel="stylesheet" type="text/css" href="/nic/css/error.css">
</head>
<body>
<div class="half">
    <div class="login">
        <div class="line"><img class="errorPic" src="/nic/image/info.jpg"></div>
        <div class="line"><p class="errorText">${message}</p></div>
        <div class="line"><a class="errorText" href="${url}">返回</a></div>
    </div>
</div>

</body>
</html>