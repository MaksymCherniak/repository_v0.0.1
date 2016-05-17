<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page import="java.util.List" %>
<%@ page import="BookingTool.Entity.Stations" %>
<%@ page import="BookingTool.Entity.Route" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Print stations by route</title>
    <spring:url value="/resources/css/main.css" var="mainCss" />
    <spring:url value="/resources/css/table.css" var="tableCss" />
    <spring:url value="/resources/css/buttons.css" var="buttonsCss" />
    <link href="${mainCss}" rel="stylesheet" />
    <link href="${tableCss}" rel="stylesheet" />
    <link href="${buttonsCss}" rel="stylesheet" />
</head>
<script>
    function deleteStation(url) {
        if (confirm(' Delete stations? ')) {
            document.location=url;
            return true;
        }
        return false;
    }
</script>

<%  Route route = (Route) request.getAttribute("route");
    List<Stations> listOfStations = (List<Stations>) request.getAttribute("listOfStations"); %>

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
    <tr><h1 align="center"><%=route.toString()%></h1></tr>
    <tr>
        <th style="width: 180px"><h2 style="color: white">Station</h2></th>
        <th style="width: 180px"><h2 style="color: white">Arrival time</h2></th>
        <th style="width: 180px"><h2 style="color: white">Leaving time</h2></th>
        <th style="width: 180px"><h2 style="color: white">Edit stations</h2></th>
        <th style="width: 180px"><h2 style="color: white">Delete stations</h2></th>
    </tr>
    <% for (int i = 0; i < listOfStations.size(); i++) {%>
    <tr class="ccc">
        <td><h2 style="color: #333333"><%=listOfStations.get(i).getStation()%></h2></td>
        <td><h2 style="color: #333333"><%=listOfStations.get(i).getArrivalTime()%></h2></td>
        <td><h2 style="color: #333333"><%=listOfStations.get(i).getLeavingTime()%></h2></td>
        <td><a href="editStationGet.do?station_id=<%=listOfStations.get(i).getId()%>" class="button edit">Edit</a></td>
        <td><input type="button" class="button delete" value="Delete" align="center"
               onclick="deleteStation('http://localhost:8080/deleteStation.do?station_id=<%=listOfStations.get(i).getId()%>')"></td>
    </tr>
    <%}%>
</table>
</body>
</html>
