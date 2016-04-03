<%@ page import="BookingTool.Entity.Train" %>
<%@ page import="BookingTool.Model.LocalModel.Wagon" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>BookingTool</title>
</head>
<body bgcolor="#dcdcdc">
<% Train train = (Train) request.getAttribute("train"); %>
<% List<Wagon> listOfWagons = (List<Wagon>) request.getAttribute("listOfWagons"); %>
<table align="center" bgcolor="#b0c4de" border="1" cellspacing="0" cellpadding="0" style="border-color: gray">
    <caption><b>Route number: <%=train.getRoute().getRouteNumber()%>,&nbsp;<%=train.getRoute().getLeavingStation()%>
        &nbsp;-&nbsp;<%=train.getRoute().getArrivalStation()%>
    </b></caption>
    <tr align="left" style="font-size: 8pt;font-family: Tahoma;color: black">
        <td align="center" style="width: 100px;height: 30px"><b>Wagon</b></td>
        <td align="center" style="width: 200px;height: 30px"><b>Type</b></td>
        <td align="center" style="width: 100px;height: 30px"><b>Total seats</b></td>
        <td align="center" style="width: 100px;height: 30px"><b>Free seats</b></td>
    </tr>
    <% for (int i = 0; i < listOfWagons.size(); i++) {%>
    <tr align="left" style="font-size: 8pt;font-family: Tahoma;color: black">
        <td align="center" style="height: 20px"><b><a
                href="printWagon.do?id=<%=listOfWagons.get(i).getId()%>&train=<%=train.getId()%>">
            <%=listOfWagons.get(i).getNumber()%>
        </a></b></td>
        <td align="center" style="height: 20px"><b><%=listOfWagons.get(i).getWagonType()%>
        </b></td>
        <td align="center" style="height: 20px"><b><%=listOfWagons.get(i).getTotalSeats()%>
        </b></td>
        <td align="center" style="height: 20px"><b><%=listOfWagons.get(i).getFreeSeats()%>
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
