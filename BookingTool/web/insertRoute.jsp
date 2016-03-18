<%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 05.03.2016
  Time: 10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Booking Tool</title>
</head>
<body>
<h1>Insert route page</h1>
<p><b><a href="main.jsp">Main page</a></b></p>
<form name="insertRoute" action="insertRoute.do" method="post">
    Enter route number: <input type="text" name="routeNumber"><br>
    Enter leaving stations: <input type="text" name="leavingStation"><br>
    Enter arrival stations: <input type="text" name="arrivalStation"><br>

    <input type="submit" value="Enter">
</form>

</body>
</html>
