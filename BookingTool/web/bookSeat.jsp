<%@ page import="BookingTool.Entity.Seat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Booking Tool</title>
</head>
<body bgcolor="#dcdcdc">
<% Seat seat = (Seat) request.getAttribute("seat"); %>
<TABLE border="0" align="center">
    <tr>
        <td><p><b>Route number: <%=seat.getWagon().getTrain().getRoute().getRouteNumber()%>,&nbsp;
            <%=seat.getWagon().getTrain().getRoute().getLeavingStation()%>&nbsp;-&nbsp;
            <%=seat.getWagon().getTrain().getRoute().getArrivalStation()%>,&nbsp;Wagon number:
            <%=seat.getWagon().getNumber()%>,&nbsp;Seat number: <%=seat.getNumber()%>,&nbsp;Price = 150.00</b></p></td>
    </tr>
</TABLE>
<table align="center" bgcolor="#dcdcdc" border="0" cellspacing="0" cellpadding="0" style="border-color: gray">
    <form action="buyTicket.do" method="post">
        <tr align="left" style="font-size: 8pt;font-family: Tahoma;color: black;height: 20px">
            <td align="left" style="width: 150px"><b>Enter your name: </b></td>
            <td align="left" style="width: 150px"><input type="text" name="name"></td>
        </tr>
        <tr align="left" style="font-size: 8pt;font-family: Tahoma;color: black;height: 20px">
            <td align="left" style="width: 150px"><b>Enter your surname: </b></td>
            <td align="left" style="width: 150px"><input type="text" name="surname"></td>
        </tr>
        <tr align="left" style="font-size: 8pt;font-family: Tahoma;color: black;height: 20px">
            <td align="right">
                <input type="hidden" name="seat" value="<%=seat.getId()%>"><br>
                <input type="submit" value="Book">
    </form>
    </td>
    </tr>
</table>
<TABLE border="0" align="center">
    <tr>
        <td><p><b><a href="main.jsp">Main page</a></b></p></td>
    </tr>
</TABLE>
</body>
</html>
