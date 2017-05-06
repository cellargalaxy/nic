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
<div class="translucent">
	<form action="" method="post">
		<table align="center">
			<tr>
				<td></td>
				<td><h2>${error}</h2></td>
			</tr>
			<tr>
				<td></td>
				<td><h3>网络信息与现代教育技术中心</h3></td>
			</tr>
			<tr>
				<td>账号：</td>
				<td><input type="text" name="id" required></td>
			</tr>
			<tr>
				<td>密码：</td>
				<td><input type="password" name="pw" required></td>
			</tr>
			<tr>
				<td><a href="signUp">注册</a></td>
				<td><input type="submit" value="登录"></td>
			</tr>
			<tr>
				<td></td>
				<td></td>
			</tr>
		</table>
	</form>
</div>


</body>
</html>
