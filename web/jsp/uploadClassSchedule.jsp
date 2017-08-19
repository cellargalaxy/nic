<%--
  Created by IntelliJ IDEA.
  User: cellargalaxy
  Date: 17-8-19
  Time: 下午10:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>上传课程表</title>
</head>
<body>
<form action="" method="post" enctype="multipart/form-data">
    上传课程表（xls）：
    学年：<input type="number" name="year" min="2017" required/>
    学期：<input type="number" name="semester" min="1" max="2" required/>
    课程表：<input type="file" name="classSchedule" required/>
    <input type="submit" value="上传"/>
    <a target= "_blank" href="#">如何得到上传的课程表？</a>
</form>
</body>
</html>
