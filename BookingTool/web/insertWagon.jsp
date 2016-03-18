<%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 09.03.2016
  Time: 9:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Booking tool</title>
</head>
<body>
<h1>Insert wagon page</h1>
<p><b><a href="main.jsp">Main page</a></b></p>
<p>
    <form name="insertWagon" action="insertWagon.do" method="post">
    Enter route number <input type="text" name="routeNumber"><br>
    Enter comfortable wagon numbers: <input type="text" name="comfortableNumbers"><br>
    Enter compartment wagon numbers: <input type="text" name="compartmentNumbers"><br>
    Enter economy wagon numbers: <input type="text" name="economyNumbers"><br>
    <input type="submit" value="Enter">
</form>
</p>
</body>
</html>
