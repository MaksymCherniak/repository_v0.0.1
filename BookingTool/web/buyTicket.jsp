<%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 09.03.2016
  Time: 9:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Booking tool</title>
</head>
<body>
<h1>Buy ticket page</h1>
<p><b><a href="main.jsp">Main page</a></b></p>
<form name="buyTicket" action="buyTicket.do" method="post">
    Enter train number <input type="text" name="trainNumber"><br>
    Enter wagon number <input type="text" name="wagonNumber"><br>
    Enter seat number <input type="text" name="seatNumber"><br>
    Enter your name <input type="text" name="firstName"><br>
    Enter your surname <input type="text" name="lastName"><br>
    <input type="submit" value="Enter">
</form>
</body>
</html>
