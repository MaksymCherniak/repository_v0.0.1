<%@ page import="BookingTool.DAO.Impl.MySQLTicketDAO" %>
<%@ page import="BookingTool.Entity.Ticket" %>
<%@ page import="java.util.List" %>
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
<h1>Print all tickets page</h1>
<a href="main.jsp">Main page</a>
<%List<Ticket> listOfTickets = new MySQLTicketDAO().getAllTickets();%>
<% for (int i = 0; i < listOfTickets.size(); i++) {%>
<p><a href="printAllTickets.do?id=<%=listOfTickets.get(i).getIndex()%>">Ticket #<%= listOfTickets.get(i).getIndex()%></a></p>
<%}%>
</body>
</html>
