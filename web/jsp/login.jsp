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

<aside class="login wrapper">
    <div class="login frame">
        <form method="post" action="">
            <img class="avatar" src="image/avatar.png">
            <p><label class="label_input"><img class="labelPicture" src="image/id.png"></label><input class="text_field"
                                                                                                      type="text"
                                                                                                      name="id"
                                                                                                      required/></p>
            <p><label class="label_input"><img class="labelPicture" src="image/pw.png"></label><input class="text_field"
                                                                                                      type="password"
                                                                                                      name="pw"
                                                                                                      required/></p>

            <input type="submit" class="btn_login" value="登&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp录"/>
            <p><a id="forget_pwd" href="signUp">注册</a></p>
        </form>
    </div>
</aside>


</body>
</html>
