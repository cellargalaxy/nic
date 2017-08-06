<%--
  Created by IntelliJ IDEA.
  User: cellargalaxy
  Date: 2017/5/4
  Time: 20:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>修改信息：${changeNicer.id}</title>
	<script type="text/javascript" src="/nic/js/jquery.js"></script>
	<script type="text/javascript" src="/nic/js/nic.js"></script>
	<link rel="stylesheet" type="text/css" href="/nic/css/changeNicer.css">
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
			<td><h3>修改信息：${changeNicer.id}</h3></td>
		</tr>
		<tr class="textTr">
			<td>学号：</td>
			<td><input type="text" name="id" placeholder="" value="${changeNicer.id}" readonly></td>
		</tr>
		<tr class="textTr">
			<td>姓名：</td>
			<td><input type="text" name="name" placeholder="" value="${changeNicer.name}" required></td>
		</tr>
		<tr class="textTr">
			<td>性别：</td>
			<td>
				<c:if test="${changeNicer.sex=='女'}">
					<input type="radio" name="sex" value="男">男<input type="radio" name="sex" value="女" checked>女<br>
				</c:if>
				<c:if test="${changeNicer.sex!='女'}">
					<input type="radio" name="sex" value="男" checked>男<input type="radio" name="sex" value="女" >女<br>
				</c:if>
			</td>
		</tr>
		<tr class="textTr">
			<td>学院：</td>
			<td><input type="text" name="college" placeholder="管理学院" value="${changeNicer.college}"></td>
		</tr>
		<tr class="textTr">
			<td>年级：</td>
			<td><input type="text" name="grade" placeholder="15" value="${changeNicer.grade}"></td>
		</tr>
		<tr class="textTr">
			<td>专业班级：</td>
			<td><input type="text" name="className" placeholder="XX1班" value="${changeNicer.className}"></td>
		</tr>
		<tr class="textTr">
			<td>手机：</td>
			<td><input type="text" name="phone" placeholder="13612341234" value="${changeNicer.phone}"></td>
		</tr>
		<tr class="textTr">
			<td>短号：</td>
			<td><input type="text" name="shortPhone" placeholder="选填" value="${changeNicer.shortPhone}"></td>
		</tr>
		<tr class="textTr">
			<td>QQ：</td>
			<td><input type="text" name="qq" placeholder="选填" value="${changeNicer.qq}"></td>
		</tr>
		<tr class="textTr">
			<td>出生：</td>
			<td><input type="date" name="birthday" value="${changeNicer.birthday}"></td>
		</tr>
		<tr class="textTr">
			<td>密码：</td>
			<td><input type="password" name="password"></td>
		</tr>
		<tr class="textTr">
			<td>确认密码：</td>
			<td><input type="password" name="password1"></td>
		</tr>

		<c:if test="${youNicer.status==youNicer.adminStatus}">
			<tr class="textTr">
				<td>状态：</td>
				<td><input type="text" name="status" placeholder="0:禁用;1:激活" value="${changeNicer.status}"></td>
			</tr>
		</c:if>
		<c:if test="${youNicer.status!=youNicer.adminStatus}">
			<input type="hidden" name="status" value="${changeNicer.status}">
		</c:if>
		
		<tr class="textareaTr">
			<td>自我简介：</td>
			<td><textarea name="introduction">${changeNicer.introduction}</textarea></td>
		</tr>
		<tr class="textTr">
			<td></td>
			<td><input type="button" value="修改" onclick="changeNicer()"></td>
		</tr>
	</table>
</div>

</body>
</html>