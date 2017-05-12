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
<%--<div id="login_frame">--%>
	<%--<form method="post" action="login.js">--%>
		<%--<p><label class="label_input"></label><input class="text_field" type="text" id="username" /></p>--%>
		<%--<p><label class="label_input"></label><input class="text_field" type="text" id="password" /></p>--%>
		<%--<input type="button" id="btn_login" value="登&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp录" />--%>
		<%--&lt;%&ndash;<a id="forget_pwd" href="">忘记密码？</a>&ndash;%&gt;--%>
	<%--</form>--%>
<%--</div>--%>


<aside class="login wrapper" >
	<div class="login frame">
		<form method="post" action="">
			<img class="avatar" src="image/avatar.png">
			<p><label class="label_input"><img class="labelPicture" src="image/id.png"></label><input class="text_field" type="text" name="id" /></p>
			<p><label class="label_input"><img class="labelPicture" src="image/pw.png"></label><input class="text_field" type="password" name="pw" /></p>
			
			<input type="submit" class="btn_login" value="登&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp录" />
			<p><a id="forget_pwd" href="signUp">注册</a></p>
		</form>
		<%--<div class="login avater" ><img src="image/avatar.png"></div>--%>
		<%--<p><img class="login" src="image/id.png"><input class="login name" type="text" title="用户名" placeholder="请输入你的学号"/></p>--%>
		<%--<p><img class="login" src="image/pw.png"><input class="login password" type="password" title="密码" placeholder="请输入你的密码"/></p>--%>
		<%--<button class="login submit" type="submit">登录</button>--%>
	</div>
</aside>

<%--<div class="translucent">--%>
	<%--<form action="" method="post">--%>
		<%--<table align="center">--%>
			<%--<tr>--%>
				<%--<td></td>--%>
				<%--<td><h2>${error}</h2></td>--%>
			<%--</tr>--%>
			<%--<tr>--%>
				<%--<td></td>--%>
				<%--<td><h3>网络信息与现代教育技术中心</h3></td>--%>
			<%--</tr>--%>
			<%--<tr>--%>
				<%--<td><img class="login" src="image/id.png"></td>--%>
				<%--<td><input type="text" name="id" required></td>--%>
			<%--</tr>--%>
			<%--<tr>--%>
				<%--<td><img class="login" src="image/pw.png"></td>--%>
				<%--<td><input type="password" name="pw" required></td>--%>
			<%--</tr>--%>
			<%--<tr>--%>
				<%--<td><a href="signUp">注册</a></td>--%>
				<%--<td><input class="login" type="submit" value="登录"></td>--%>
			<%--</tr>--%>
			<%--<tr>--%>
				<%--<td></td>--%>
				<%--<td></td>--%>
			<%--</tr>--%>
		<%--</table>--%>
	<%--</form>--%>
<%--</div>--%>


</body>
</html>
