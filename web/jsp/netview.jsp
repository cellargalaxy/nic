<%--
  Created by IntelliJ IDEA.
  User: cellargalaxy
  Date: 2017/4/25
  Time: 11:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>交换机监控列表</title>
	<script type="text/javascript" src="/nic/js/jquery.js"></script>
	<script type="text/javascript" src="/nic/js/netview.js"></script>
	<link rel="stylesheet" type="text/css" href="/nic/css/netview.css">
	<script type="text/javascript">
		setInterval("reload()", 1000 * 60 * 5);
	</script>
</head>
<body>
<h2>${error}</h2>

<h5>
	<form action="" method="get">
		查询：
		<input type="text" name="demandKey" placeholder="地址或者ip的一部分" required>
		<input type="submit" value="查询">
	</form>
</h5>

<c:if test="${youNicer.admin==1}">
	<h5>
		楼栋：<input type="text" name="building" placeholder="A1">
		楼层：<input type="text" name="floor" placeholder="1">
		名字：<input type="text" name="name" placeholder="1F-2328">
		ip：<input type="text" name="address" placeholder="127.0.0.1">
		<input type="button" value="添加" onclick="addHost()">
	</h5>
	
	<h5>
		<form action="" method="post" enctype="multipart/form-data">
			批量添加（xls）：
			<input type="file" name="ipFile" required>
			<input type="submit" value="添加">
			<a href="netview/ipFile">excel模板</a>
		</form>
	</h5>
</c:if>

<c:forEach var="building" items="${buildings}">
	<h3 class="buildingName" onclick="display('${building.buildingName}')"><a>${building.buildingName}</a></h3>
	<table name="${building.buildingName}">
		<tr>
			<td>地址</td>
			<c:if test="${youNicer.admin==1}">
				<td>ip</td>
			</c:if>
			<td>延时</td>
			<td>连接</td>
			<td>日期</td>
			<c:if test="${youNicer.admin==1}">
				<td>删除</td>
			</c:if>
		</tr>
		<c:forEach var="map" items="${building.maps}">
			<c:if test="${map.conn}">
				<tr class="connTr">
			</c:if>
			<c:if test="${!map.conn}">
				<tr class="notConnTr">
			</c:if>
			<td>${map.name}</td>
			<c:if test="${youNicer.admin==1}">
				<td>${map.address}</td>
			</c:if>
			<td>
				<c:forEach var="delay" items="${map.delays}">
					<c:if test="${delay>-1}">
						<div class="conn">${delay}</div>
					</c:if>
					<c:if test="${delay<0}">
						<div class="notConn">${delay}</div>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:if test="${map.conn}">
					<img src="image/huaji.jpg" >
				</c:if>
				<c:if test="${!map.conn}">
					<img src="image/angry.jpg" ">
				</c:if>
			</td>
			
			<td>
				<c:if test="${map.conn}">
					恢复时间：${map.date}
				</c:if>
				<c:if test="${!map.conn}">
					断开时间：${map.date}
				</c:if>
			</td>
			
			<c:if test="${youNicer.admin==1}">
				<td><a href="" onclick="deleteHost('${map.address}')">删除</a></td>
			</c:if>
			</tr>
		</c:forEach>
	</table>
</c:forEach>
</body>
</html>
