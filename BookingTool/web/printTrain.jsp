<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page import="BookingTool.Entity.Train" %>
<%@ page import="BookingTool.Entity.Wagon" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Select wagon</title>
    <spring:url value="/resources/css/main.css" var="mainCss" />
    <spring:url value="/resources/css/table.css" var="tableCss" />
    <spring:url value="/resources/css/buttons.css" var="buttonsCss" />
    <link href="${mainCss}" rel="stylesheet" />
    <link href="${tableCss}" rel="stylesheet" />
    <link href="${buttonsCss}" rel="stylesheet" />
</head>

<% Train train = (Train) request.getAttribute("train"); %>
<% List<Wagon> listOfWagons = (List<Wagon>) request.getAttribute("listOfWagons"); %>

<body>
<br>
<br>
<br>
<br>
<div class="w3-container">
    <ul class="navbar">
        <li><a class="active" href="main.jsp">Home</a></li>
        <li><a href="#">Actions with route</a>
            <ul>
                <li><a href="getRoute.jsp">Select route</a></li><br>
                <li><a href="getAllRoutesGet.do">Print stations by route</a></li><br>
            </ul>
        </li>
        <li><a href="#">Actions for admin</a>
            <ul>
                <li><a href="insertRoute.jsp">Insert new route</a></li><br>
                <li><a href="getAllRoutesForAction.do">Actions with route</a></li><br>
            </ul>
        </li>
        <li><a href="#">Actions for user</a>
            <ul>
                <li><a href="registration.jsp">Registration</a></li><br>
                <li><a href="authorization.jsp">Authorization</a></li><br>
            </ul>
        </li>
        <li style="float: right"><a href="authorization.jsp">Login</a></li>
    </ul>
</div>

<br>
<br>
<table align="center">
    <tr><h1 align="center">Route number: <%=train.getRoute().getRouteNumber()%>,&nbsp;<%=train.getRoute().getLeavingStation()%>
        &nbsp;-&nbsp;<%=train.getRoute().getArrivalStation()%></h1></tr>
    <tr>
        <th style="width: 180px"><h2 align="center" style="color: white">Wagon</h2></th>
        <th style="width: 250px"><h2 align="center" style="color: white">Type</h2></th>
        <th style="width: 180px"><h2 align="center" style="color: white">Total seats</h2></th>
        <th style="width: 180px"><h2 align="center" style="color: white">Free seats</h2></th>
    </tr>
    <% for (int i = 0; i < listOfWagons.size(); i++) {%>
    <tr class="ccc">
        <td align="center"><a href="printWagon.do?id=<%=listOfWagons.get(i).getId()%>&train=<%=train.getId()%>">
            <h2 align="center" style="color: #333333"><%=listOfWagons.get(i).getNumber()%></h2></a></td>
        <td><h2 align="center" style="color: #333333"><%=listOfWagons.get(i).getWagonType()%></h2></td>
        <td><h2 align="center" style="color: #333333"><%=listOfWagons.get(i).getTotalSeats()%></h2></td>
        <td><h2 align="center" style="color: #333333"><%=listOfWagons.get(i).getFreeSeats()%></h2></td>
    </tr>
    <%}%>
</table>
</body>
</html>
