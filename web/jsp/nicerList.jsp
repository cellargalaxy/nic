<%--
  Created by IntelliJ IDEA.
  User: cellargalaxy
  Date: 2017/5/3
  Time: 23:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>队员信息列表</title>
	<script type="text/javascript" src="/nic/js/jquery.js"></script>
	<script type="text/javascript" src="/nic/js/nic.js"></script>
	<link rel="stylesheet" type="text/css" href="/nic/css/nicerList.css">
</head>
<body>
<h2>${error}</h2>
<a href="nicerList/changeNicer?id=${youNicer.id}">修改个人信息</a>

<c:if test="${youNicer.admin==1}">
	<h4>待审核</h4>
	<table>
		<tr>
			<td>学号</td>
			<td>姓名</td>
			<td>性别</td>
			<td>学院</td>
			<td>年级</td>
			<td>专业班级</td>
			<td>电话</td>
			<td>短号</td>
			<td>QQ</td>
			<td>出生</td>
			<td>简介</td>
			<td>是否管理员</td>
			<td>修改</td>
			<td>删除</td>
			<td>通过</td>
		</tr>
		<c:forEach var="nicer" items="${tempNicers}">
			<tr>
				<td>${nicer.id}</td>
				<td>${nicer.name}</td>
				<td>${nicer.sex}</td>
				<td>${nicer.college}</td>
				<td>${nicer.grade}</td>
				<td>${nicer.className}</td>
				<td>${nicer.phone}</td>
				<td>${nicer.shortPhone}</td>
				<td>${nicer.qq}</td>
				<td>${nicer.birthday}</td>
				<td>${nicer.introduction}</td>
				<td>${nicer.admin}</td>
				<form method="get" action="nicerList/changeNicer">
						<input type="hidden" name="id" value="${nicer.id}">
					<td>
						<input type="submit" value="修改">
					</td>
				</form>
				
				<td><input type="button" value="删除" onclick="turnRight(${nicer.id},0)"></td>
				<td><input type="button" value="通过" onclick="turnRight(${nicer.id},1)"></td>
			</tr>
		</c:forEach>
	</table>
	<br>
</c:if>


<h4>队员列表</h4>
<table>
	<tr>
		<td>学号</td>
		<td>姓名</td>
		<td>性别</td>
		<td>学院</td>
		<td>年级</td>
		<td>专业班级</td>
		<td>电话</td>
		<td>短号</td>
		<td>QQ</td>
		<td>出生</td>
		<td>简介</td>
		<c:if test="${youNicer.admin==1}">
			<td>是否管理员</td>
			<td>修改</td>
			<td>删除</td>
		</c:if>
	</tr>
	<c:forEach var="nicer" items="${passNicers}">
		<tr>
			<td>${nicer.id}</td>
			<td>${nicer.name}</td>
			<td>${nicer.sex}</td>
			<td>${nicer.college}</td>
			<td>${nicer.grade}</td>
			<td>${nicer.className}</td>
			<td>${nicer.phone}</td>
			<td>${nicer.shortPhone}</td>
			<td>${nicer.qq}</td>
			<td>${nicer.birthday}</td>
			<td>${nicer.introduction}</td>
			<c:if test="${youNicer.admin==1}">
				<td>${nicer.admin}</td>
				
				<form method="get" action="nicerList/changeNicer">
					<input type="hidden" name="id" value="${nicer.id}">
					<td>
						<input type="submit" value="修改">
					</td>
				</form>
				
				<td><input type="button" value="删除" onclick="turnRight(${nicer.id},0)"></td>
			</c:if>
		</tr>
	</c:forEach>
</table>
</body>
</html>
