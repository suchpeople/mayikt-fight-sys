<%--
  Created by IntelliJ IDEA.
  User: z2545
  Date: 2023/9/13
  Time: 0:49
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>航班系统</title>
</head>
<body>
<a href="insertFlight">插入数据</a>
<table border="1" align="center" style="border-collapse: collapse;width: 80%">
    <tr align="center">
        <th align="center">航号</th>
        <th align="center">航空公司</th>
        <th align="center">出发机场</th>
        <th align="center">达到机场</th>
        <th align="center">出发时间</th>
        <th align="center">到达时间</th>
        <th align="center">机型</th>
        <th align="center">操作</th>
    </tr>
    <c:forEach items="${flightEntities}" var="f">
    <tr align="center">
        <td align="center">${f.flightId}</td>
        <td align="center">${f.company}</td>
        <td align="center">${f.departureAirport}</td>
        <td align="center">${f.arriveAirport}</td>
        <td align="center">${f.departureTime}</td>
        <td align="center">${f.arriveTime}</td>
        <td align="center">${f.model}</td>
        <td align="center">${f.isDelete}</td>
        <td align="center"><a href="/mayikt_fight_sys_war_exploded/updateFlight?id=${f.id}">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
                href="/mayikt_fight_sys_war_exploded/deleteFlight?id=${f.id}">删除</a></td>
    </tr>
    </c:forEach>

</table>
</body>
</html>
