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

<aside class="login wrapper" >
	<div class="login frame">
		<form >
			<img class="errorPic" src="/nic/image/error.png">
			<p><br></p>
			<p class="errorText">${error}</p>
			<p><a class="errorText" href="/nic/login" >登录</a></p>
		</form>
	</div>
</aside>

</body>
</html>
