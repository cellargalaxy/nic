<%--
  Created by IntelliJ IDEA.
  User: cellargalaxy
  Date: 2017/4/25
  Time: 11:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>异常</title>
	<link rel="stylesheet" type="text/css" href="/nic/css/error.css">
</head>
<body>
<div class="half">
	<div class="login">
		<div class="line"><img class="errorPic" src="/nic/image/error.png"></div>
		<div class="line"><p class="errorText">${error}</p></div>
		<div class="line"><a class="errorText" href="/nic/login" >登录</a></div>
	</div>
</div>

</body>
</html>
