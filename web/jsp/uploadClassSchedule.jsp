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
    <link rel="stylesheet" type="text/css" href="/nic/css/error.css">
</head>
<body>
<div class="half">
    <div class="login">
        <form action="" method="post" enctype="multipart/form-data">
            <table>
                <tr>
                    <td><a href="/nic">首页</a></td>
                    <td>上传课程表（xls）：</td>
                </tr>
                <tr>
                    <td>学年：</td>
                    <td><input type="number" name="year" min="2017" required/></td>
                </tr>
                <tr>
                    <td>学期：</td>
                    <td><input type="number" name="semester" min="1" max="2" required/></td>
                </tr>
                <tr>
                    <td>课程表：</td>
                    <td><input type="file" name="classSchedule" required/></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="上传"/> <a target= "_blank" href="/nic/html/uploadClassSchedule.html">如何得到上传的课程表？</a></td>
                </tr>
            </table>
        </form>
    </div>
</div>

</body>
</html>
