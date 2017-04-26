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
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/netview.js"></script>
	<link rel="stylesheet" type="text/css" href="css/netview.css">
	<script type="text/javascript">
		setInterval("reload()",100000);
	</script>
</head>
<body >

	<h5>
		<form action="#" method="get">
			查询：
			<input type="text" name="demandKsy" required>
			<input type="submit" value="查询">
		</form>
	</h5>
	
	<h5>
		楼栋：<input type="text" name="building" >
		楼层：<input type="text" name="floor" >
		名字：<input type="text" name="name" >
		ip：<input type="text" name="address" >
		<input type="button" value="添加" onclick="addHost()">
	</h5>
	
	<h5>
		<form action="#" method="post" enctype="multipart/form-data">
			批量添加（xls）：
			<input type="file" name="ipFile" required>
			<input type="submit" value="添加">
		</form>
	</h5>
	
	<c:forEach var="building" items="${buildings}">
		<h3 onclick="display('${building.buildingName}')">${building.buildingName} (按我折叠)</h3>
		<table name="${building.buildingName}">
			<tr><td>地址</td><td>ip</td><td>延时</td><td>连接</td><td>日期</td><td>删除</td></tr>
			<c:forEach var="map" items="${building.maps}">
				<c:if test="${map.conn}">
					<tr class="connTr">
				</c:if>
				<c:if test="${!map.conn}">
					<tr class="notConnTr">
				</c:if>
					<td>${map.name}</td><td>${map.address}</td>
					<td>
						<c:forEach var="delay" items="${map.delay}">
							<c:if test="${delay>-1}">
								<div class="connDelay">${delay}</div>
							</c:if>
							<c:if test="${delay<0}">
								<div class="notConnDelay">${delay}</div>
							</c:if>
						</c:forEach>
					</td>
					<td>
						<c:if test="${map.conn}">
							<img src="image/huaji.jpg" width="30px" height="30px">
						</c:if>
						<c:if test="${!map.conn}">
							<img src="image/angry.jpg" width="30px" height="30px">
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
					<td><a  href="" onclick="deleteHost('${map.address}')">删除</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:forEach>
</body>
</html>
