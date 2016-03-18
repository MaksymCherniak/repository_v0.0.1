<%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 18.03.2016
  Time: 20:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Booking Tool</title>
</head>
<body>
<h1>Insert train page</h1>
<p><b><a href="main.jsp">Main page</a></b></p>
<form name="insertTrain" action="insertTrain.do" method="post">
    Enter route number: <input type="text" name="routeNumber"><br>
    Enter leaving date: <input type="text" name="leavingDate"><br>
    Select days cruising:
    <select size="8" multiple name="days">
        <option value="allDays">All days</option>
        <option value="monday">Monday</option>
        <option value="tuesday">Tuesday</option>
        <option value="wednesday">Wednesday</option>
        <option value="thursday">Thursday</option>
        <option value="friday">Friday</option>
        <option value="saturday">Saturday</option>
        <option value="sunday">Sunday</option>
    </select><br>
    <input type="submit" value="Enter">
</form>
</body>
</html>
