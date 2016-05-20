<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page import="BookingTool.Entity.Train" %>
<%@ page import="java.util.List" %>
<%@ page import="BookingTool.Entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Select route</title>
    <spring:url value="/resources/css/main.css" var="mainCss" />
    <spring:url value="/resources/css/table.css" var="tableCss" />
    <spring:url value="/resources/css/buttons.css" var="buttonsCss" />
    <link href="${mainCss}" rel="stylesheet" />
    <link href="${tableCss}" rel="stylesheet" />
    <link href="${buttonsCss}" rel="stylesheet" />
</head>

<script>
    function logout(url) {
        if (confirm(' Logout? ')) {
            document.location=url;
            return true;
        }
        return false;
    }
</script>

<% User user = (User) request.getSession().getAttribute("user"); %>
<% List<Train> listOfTrains = (List<Train>) request.getAttribute("listOfTrains"); %>

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
        <% if (user != null && user.getEmail().equals("admin@ukr.net")) { %>
        <li><a href="#">Actions for admin</a>
            <ul>
                <li><a href="insertRoute.jsp">Insert new route</a></li><br>
                <li><a href="getAllRoutesForAction.do">Actions with route</a></li><br>
            </ul>
        </li>
        <% } if (user == null) { %>
        <li style="float: right"><a href="authorization.jsp">Login</a></li>
        <li style="float: right"><a href="registration.jsp">Registration</a></li>
        <% } else { %>
        <li style="float: right"><a href="#" onclick="logout('http://localhost:8080/logOut.do')">Logout</a></li>
        <% } %>
    </ul>
</div>

<br>
<br>
<table align="center">
    <tr><h1 align="center">Select route</h1></tr>
    <tr>
        <th style="width: 180px"><h2 style="color: white">Route number</h2></th>
        <th style="width: 180px"><h2 style="color: white">Route</h2></th>
        <th style="width: 180px"><h2 style="color: white">Leaving time / <br>Arrival time</h2></th>
        <th style="width: 180px"><h2 style="color: white">Total seats</h2></th>
    </tr>
    <% for (int i = 0; i < listOfTrains.size(); i++) {%>
    <tr class="ccc">
        <td align="center"><a href="printTrain.do?id=<%=listOfTrains.get(i).getId()%>">
            <h2 align="center" style="color: #333333"><%= listOfTrains.get(i).getRoute().getRouteNumber()%></h2></a></td>
        <td><h2 style="color: #333333"><%=listOfTrains.get(i).getRoute().getLeavingStation()%>&nbsp;-&nbsp;
            <%=listOfTrains.get(i).getRoute().getArrivalStation()%></h2></td>
        <td><h2 style="color: #333333"><%= listOfTrains.get(i).getRoute().getLeavingTime()%> /
            <%= listOfTrains.get(i).getRoute().getArrivalTime()%></h2></td>
        <td><h2 style="color: #333333">Value of total seats</h2></td>
    </tr>
    <%}%>
</table>

</body>
</html>
