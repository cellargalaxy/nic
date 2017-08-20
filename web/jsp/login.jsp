<%--
  Created by IntelliJ IDEA.
  User: cellargalaxy
  Date: 2017/4/24
  Time: 22:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <link rel="stylesheet" type="text/css" href="/nic/css/login.css">
</head>
<body>

<div class="half">
    <div class="login">
        <form method="post" action="">
            <p><img class="avatar" src="image/avatar.png"></p>

            <div class="line">
                <img class="labelPicture" src="image/id.png">
                <input class="text_field" type="text" name="id" required/>
            </div>

            <div class="line">
                <img class="labelPicture" src="image/pw.png">
                <input class="text_field" type="password" name="pw" required/>
            </div>

            <div class="line">
                <input type="submit" class="btn_login" value="登&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp录"/>
            </div>
            <p><a class="signUp" href="signUp">注册</a></p>
        </form>
    </div>
</div>

</body>
</html>
