<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page import="BookingTool.Entity.Seat" %>
<%@ page import="BookingTool.Entity.Train" %>
<%@ page import="java.util.List" %>
<%@ page import="BookingTool.Entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Select seat</title>
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
<% Train train = (Train) request.getAttribute("train"); %>
<% List<Seat> listOfSeats = (List<Seat>) request.getAttribute("listOfSeats"); %>

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
    <tr><h1 align="center">Route number: <%=train.getRoute().getRouteNumber()%>,&nbsp;<%=train.getRoute().getLeavingStation()%>
        &nbsp;-&nbsp;<%=train.getRoute().getArrivalStation()%>,&nbsp;Wagon number: <%=listOfSeats.get(0).getWagon().getNumber()%></h1></tr>
    <tr>
        <th style="width: 180px"><h2 align="center" style="color: white">Seat</h2></th>
        <th style="width: 250px"><h2 align="center" style="color: white">Status</h2></th>
    </tr>
    <% for (int i = 0; i < listOfSeats.size(); i++) {%>
    <tr class="ccc">
        <td align="center">
            <% if (String.valueOf(listOfSeats.get(i).getStatus()).equalsIgnoreCase("free")) { %>
            <a href="buyTicket.do?seat=<%=listOfSeats.get(i).getId()%>">
            <h2 align="center" style="color: #333333"><%=listOfSeats.get(i).getNumber()%></h2></a>
            <% } else { %>
            <h2 align="center" style="color: #333333"><%=listOfSeats.get(i).getNumber()%></h2>
            <% } %></td>
        <td><h2 align="center" style="color: #333333"><%=listOfSeats.get(i).getStatus()%></h2></td>
    </tr>
    <%}%>
</table>
</body>
</html>
