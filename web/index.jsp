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
    <title>广工龙洞网络中心</title>
  </head>
  <body>
  <h4>${error}</h4>
  <form action="/nic/" method="post" >
    密码：<input type="password" name="pw" required>
    <input type="submit" value="登录">
  </form>
  </body>
</html>
