<%@ page import="BookingTool.Entity.Seat" %>
<%@ page import="BookingTool.Entity.Train" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Booking Tool</title>
</head>
<body bgcolor="#dcdcdc">
<% Train train = (Train) request.getAttribute("train"); %>
<% List<Seat> listOfSeats = (List<Seat>) request.getAttribute("listOfSeats"); %>
<table align="center" bgcolor="#b0c4de" border="1" cellspacing="0" cellpadding="0" style="border-color: gray">
    <caption><b>Route number: <%=train.getRoute().getRouteNumber()%>,&nbsp;<%=train.getRoute().getLeavingStation()%>
        &nbsp;-&nbsp;<%=train.getRoute().getArrivalStation()%>,&nbsp;Wagon
        number: <%=listOfSeats.get(0).getWagon().getNumber()%>
    </b></caption>
    <tr align="left" style="font-size: 8pt;font-family: Tahoma;color: black">
        <td align="center" style="width: 100px;height: 20px"><b>Seat</b></td>
        <td align="center" style="width: 100px;height: 20px"><b>Status</b></td>
    </tr>
    <% for (int i = 0; i < listOfSeats.size(); i++) { %>
    <tr align="left" style="font-size: 8pt;font-family: Tahoma;color: black">
        <td align="center" style="height: 20px"><b>
            <% if (String.valueOf(listOfSeats.get(i).getStatus()).equalsIgnoreCase("free")) { %>
            <a href="buyTicket.do?seat=<%=listOfSeats.get(i).getId()%>"><%=listOfSeats.get(i).getNumber()%>
            </a>
            <% } else { %>
            <%=listOfSeats.get(i).getNumber()%>
            <% } %>
        </b></td>
        <td align="center" style="height: 20px"><b><%=listOfSeats.get(i).getStatus()%>
        </b></td>
    </tr>
    <% } %>
</table>
<TABLE border="0" align="center">
    <tr>
        <td><p><b><a href="main.jsp">Main page</a></b></p></td>
    </tr>
</TABLE>
</body>
</html>
