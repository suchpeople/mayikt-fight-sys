<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<div >
    <h1>插入数据</h1>

    <form action="insertFlight" method="post">
        <label>航&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号:&nbsp;<input type="text"  name="flightId"></label><br>
        <label>航空公司:&nbsp;<input type="text"  name="company"></label><br>
        <label>出发机场:&nbsp;<input type="text"  name="departureAirport"></label><br>
        <label>达到机场:&nbsp;<input type="text"  name="arriveAirport"></label><br>
        <label>出发时间:&nbsp;<input type="text"  name="departureTime"></label><br>
        <label>到达时间:&nbsp;<input type="text"  name="arriveTime"></label><br>
        <label>机&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型:&nbsp;<input type="text"  name="model"></label><br>
        <input type="submit" value="提交">
    </form>
</div>

</body>
</html>