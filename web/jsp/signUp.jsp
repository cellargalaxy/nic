<%--
  Created by IntelliJ IDEA.
  User: cellargalaxy
  Date: 2017/5/3
  Time: 10:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <script type="text/javascript" src="/nic/js/jquery.js"></script>
    <script type="text/javascript" src="/nic/js/nic.js"></script>
    <link rel="stylesheet" type="text/css" href="/nic/css/signUp.css">
</head>
<body>
<div class="translucent">
    <table align="center">
        <tr class="textTr">
            <td></td>
            <td><h2>${error}</h2></td>
        </tr>
        <tr class="textTr">
            <td></td>
            <td><h3>注册账号</h3></td>
        </tr>
        <tr class="textTr">
            <td>学号：</td>
            <td><input type="number" name="id" placeholder="3115001234"></td>
        </tr>
        <tr class="textTr">
            <td>名字：</td>
            <td><input type="text" name="name" placeholder="小明"></td>
        </tr>
        <tr class="textTr">
            <td>性别：</td>
            <td><input type="radio" name="sex" value="男" checked>男<input type="radio" name="sex" value="女">女</td>
        </tr>
        <tr class="textTr">
            <td>学院：</td>
            <td><input type="text" name="college" placeholder="管理学院"></td>
        </tr>
        <tr class="textTr">
            <td>年级：</td>
            <td><input type="number" name="grade" placeholder="15"></td>
        </tr>
        <tr class="textTr">
            <td>专业班级：</td>
            <td><input type="text" name="className" placeholder="XX1班"></td>
        </tr>
        <tr class="textTr">
            <td>手机：</td>
            <td><input type="number" name="phone" placeholder="13612341234"></td>
        </tr>
        <tr class="textTr">
            <td>短号：</td>
            <td><input type="number" name="shortPhone" placeholder="选填"></td>
        </tr>
        <tr class="textTr">
            <td>QQ：</td>
            <td><input type="number" name="qq" placeholder="选填"></td>
        </tr>
        <tr class="textTr">
            <td>出生：</td>
            <td><input type="date" name="birthday"></td>
        </tr>
        <tr class="textTr">
            <td>密码：</td>
            <td><input type="password" name="password"></td>
        </tr>
        <tr class="textTr">
            <td>确认密码：</td>
            <td><input type="password" name="password1"></td>
        </tr>
        <tr class="textareaTr">
            <td>自我简介：</td>
            <td><textarea name="introduction" placeholder="选填"></textarea></td>
        </tr>
        <tr class="textTr">
            <td></td>
            <td><input type="button" value="注册" onclick="signUp()"></td>
        </tr>
        <tr>
            <td></td>
            <td></td>
        </tr>
    </table>
</div>


</body>
</html>
