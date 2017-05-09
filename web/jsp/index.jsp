<%--
  Created by IntelliJ IDEA.
  User: cellargalaxy
  Date: 2017/5/4
  Time: 23:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>网络信息与现代教育技术中心</title>
	<script type="text/javascript" src="/nic/js/jquery.js"></script>
	<link rel="stylesheet" type="text/css" href="/nic/css/index.css">
	<script type="text/javascript">
		setInterval('pictureWall()', 20000);
		function pictureWall() {
			document.body.style.backgroundImage = "url(/nic/pictureWall?t=" + Math.random() + ")";
		}
	</script>
</head>
<body onload="pictureWall()">
<div class="translucent">
	<h2>${error}</h2>
	<h2>网络信息与现代教育技术中心</h2>
	<!-- 
	<p><a href="signUp">注册</a></p>
	<p><a href="login">登录</a></p>
	-->
	<p><a href="netview">交换机监控</a></p>
	<p><a href="nicerList">队员查看</a></p>
	<p><a href="pictureWall">图片墙</a></p>
	
	<p onclick="alert('跟多功能的建设期待小伙伴们积极参与')">电影台</p>
	<p onclick="alert('跟多功能的建设期待小伙伴们积极参与')">FTP文件夹</p>
	<p onclick="alert('跟多功能的建设期待小伙伴们积极参与')">在线上传课表生成值班表</p>
	<p onclick="alert('跟多功能的建设期待小伙伴们积极参与')">线代、运筹学在线计算工具</p>
	<p onclick="pictureWall()">点我改变背景图片</p>
	<br>

</div>

</body>
</html>
