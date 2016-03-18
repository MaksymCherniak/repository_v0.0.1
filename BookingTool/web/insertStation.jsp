<%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 18.03.2016
  Time: 20:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Booking Tool</title>
</head>
<body>
<h1>Insert stations page</h1>
<p><b><a href="main.jsp">Main page</a></b></p>
${info}
<form name="insertStation" action="insertStation.do" method="post">
    Enter route number: <input type="text" name="routeNumber"><br>
    Enter stations: <input type="text" name="station"><br>
    Enter leaving time: <input type="text" name="leavingTime"><br>
    Enter arrival time: <input type="text" name="arrivalTime"><br>
    <input type="submit" value="Add stations"><br>
</form>
<p><b><a href="insertTrain.jsp">Insert train</a></b></p>
</body>
</html>
