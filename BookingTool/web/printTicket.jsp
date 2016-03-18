<%@ page import="BookingTool.Entity.Ticket" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 09.03.2016
  Time: 10:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Booking tool</title>
</head>
<body>
<h1>Print ticket page</h1>
<p><b><a href="main.jsp">Main page</a> </b></p>
<p><b><a href="printAllBooks.do">All books</a></b></p>
<h2>Ticket #${ticket.index}</h2>
<ul>
    <li>Id: ${ticket.index}</li>
    <li>Train: ${ticket.train}</li>
    <li>Wagon: ${ticket.wagon.number}</li>
    <li>Seat: ${ticket.seat}</li>
    <li>User name: ${ticket.user.firstName}</li>
    <li>User surname: ${ticket.user.lastName}</li>
</ul>
</body>
</html>
