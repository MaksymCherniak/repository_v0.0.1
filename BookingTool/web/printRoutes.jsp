<%@ page import="BookingTool.Entity.Train" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>BookingTool</title>
</head>
<body bgcolor="#dcdcdc">
<table align="center" bgcolor="#b0c4de" border="1" cellspacing="0" cellpadding="0" style="border-color: gray">
    <caption><b>Select route</b></caption>
    <tr align="left" style="font-size: 8pt;font-family: Tahoma;color: black">
        <td align="center" style="width: 100px"><b>Route number</b></td>
        <td align="center" style="width: 100px"><b>Route</b></td>
        <td align="center" style="width: 100px"><b>Leaving time / <br>Arrival time</b></td>
        <td align="center" style="width: 100px"><b>Total seats</b></td>
    </tr>
    <% List<Train> listOfTrains = (List<Train>) request.getAttribute("listOfTrains"); %>
    <% for (int i = 0; i < listOfTrains.size(); i++) {%>
    <tr align="left" style="font-size: 8pt;font-family: Tahoma;color: black">
        <td align="center"><b><a href="printTrain.do?id=<%=listOfTrains.get(i).getId()%>">
            <%= listOfTrains.get(i).getRoute().getRouteNumber()%>
        </a></b></td>
        <td align="center"><b><%=listOfTrains.get(i).getRoute().getLeavingStation()%>&nbsp;-&nbsp;
            <%=listOfTrains.get(i).getRoute().getArrivalStation()%>
        </b></td>
        <td align="center"><b><%= listOfTrains.get(i).getRoute().getLeavingTime()%>
        </b><br>
            <b><%= listOfTrains.get(i).getRoute().getArrivalTime()%>
            </b></td>
        <td align="center"><b>Value of total seats</b></td>
    </tr>
    <%}%>
</table>
<TABLE border="0" align="center">
    <tr>
        <td><p><b><a href="main.jsp">Main page</a></b></p></td>
    </tr>
</TABLE>
</body>
</html>
