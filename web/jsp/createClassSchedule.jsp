<%--
  Created by IntelliJ IDEA.
  User: cellargalaxy
  Date: 17-8-20
  Time: 下午4:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>生成课余空闲表</title>
    <link rel="stylesheet" type="text/css" href="/nic/css/error.css">
</head>
<body>
<div class="half">
    <div class="login">
        <form method="get" action="">
            <table>
                <tr>
                    <td><a href="/nic">首页</a></td>
                    <td>查询课程表提交情况：</td>
                </tr>
                <tr>
                    <td>学年：</td>
                    <td><input type="number" name="year" min="2017" value="${year}" required></td>
                </tr>
                <tr>
                    <td>学期：</td>
                    <td><input type="number" name="semester" min="1" max="2" value="${semester}"required> <input type="submit" value="查询"></td>
                </tr>
            </table>
        </form>
        <form method="post" action="">
            <table>
                <tr>
                    <td></td>
                    <td>生成课余空闲表：</td>
                </tr>
                <tr>
                    <td>学年：</td>
                    <td><input type="number" name="year" min="2017" value="${year}" required></td>
                </tr>
                <tr>
                    <td>学期：</td>
                    <td><input type="number" name="semester" min="1" max="2" value="${semester}" required></td>
                </tr>
                <tr>
                    <td>开始周：</td>
                    <td><input type="number" name="startWeek" min="1" max="25" required></td>
                </tr>
                <tr>
                    <td>结束周：</td>
                    <td><input type="number" name="endWeek" min="1" max="25" required> <input type="submit" value="下载"></td>
                </tr>
            </table>
        </form>
    </div>
</div>

<div class="half">
    <div class="login">
        <table>
            <tr>
                <td>已提交</td>
                <td>(${fn:length(nicers)})</td>
            </tr>
            <c:forEach var="nicer" items="${nicers}">
                <tr>
                    <td>${nicer.name}</td>
                    <c:if test="${youNicer!=null&&youNicer.status==youNicer.adminStatus}">
                    <td>
                        <form method="post" action="">
                            <input type="hidden" name="year" value="${year}" required>
                            <input type="hidden" name="semester" value="${semester}" required>
                            <input type="hidden" name="nicerId" value="${nicer.id}" required>
                            <input type="submit" value="下载课程表">
                        </form>
                    </td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

</body>
</html>

