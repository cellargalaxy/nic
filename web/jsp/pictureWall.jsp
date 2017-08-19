<%--
  Created by IntelliJ IDEA.
  User: cellargalaxy
  Date: 17-8-19
  Time: 下午2:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>照片墙</title>
    <script type="text/javascript" src="/nic/js/jquery.js"></script>
    <link rel="stylesheet" type="text/css" href="/nic/css/pictureWall.css">
    <script type="text/javascript">
        pictureIndex=${pictureIndex}
        setInterval('nextPicture()', 10000);
        function previousPicture() {
            $("img").attr("src","/nic/picture?index="+(pictureIndex-1));
            pictureIndex=pictureIndex-1;
        }
        function nextPicture() {
            $("img").attr("src","/nic/picture?index="+(pictureIndex+1));
            pictureIndex=pictureIndex+1;
        }
    </script>
</head>
<body>
    <div class="previousPicture" onclick="previousPicture()"></div>
    <div class="nextPicture" onclick="nextPicture()"></div>
    <div class="pictureName"><h2><a href="/nic">首页</a></h2></div>
    <div class="pictureWall"><img class="picture" src="/nic/picture?index=${pictureIndex}"></div>
</body>
</html>
