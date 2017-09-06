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
        //setInterval('picture()', 20000);
        function init() {
            document.body.style.backgroundImage = "url(/nic/image/nic.jpg)";
        }
        function picture() {
            document.body.style.backgroundImage = "url(/nic/picture?index=" + Math.random() + ")";
        }
    </script>
</head>
<body onload="init()">
<div class="translucent">
    <h2>网络信息与现代教育技术中心</h2>
    <!--
    <p><a href="signUp">注册</a></p>
    <p><a href="login">登录</a></p>
    -->
    <p><a href="netview">交换机监控</a></p>
    <p><a href="nicerList">队员查看</a></p>
    <p><a href="pictureWall">图片墙</a></p>
    <p><a href="onDuty/uploadClassSchedule">上传课程表</a></p>
    <p><a href="onDuty/createClassSchedule">生成课余空闲表</a></p>
    <p><a target= "_blank" href="ftp://202.116.150.40">FTP文件夹</a></p>
    <p><a target= "_blank" href="http://202.116.150.40:81">可道云(账号:nicer 密码:kodcloud)</a></p>
    <p onclick="picture()">点我改变背景图片</p>
    <br>

</div>

</body>
</html>