<%@ page import="BookingTool.Entity.Route" %>
<%@ page import="BookingTool.Entity.User" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Route actions</title>
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
<% Route route = (Route) request.getAttribute("route"); %>

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
<h1 align="center">&nbsp;&nbsp;Actions with route <%=route.getRouteNumber()%></h1>
<TABLE align="center" border="0">
    <tr><td>
        <button  class="submit" onclick="location.href='http://localhost:8080/insertTrain.do?routeNumber=<%=route.getRouteNumber()%>'"
                 style="width: 250px;height: 60px;"><b>Add trains</b></button>
    </td></tr>
    <tr><td>
        <button  class="submit" onclick="location.href='http://localhost:8080/insertWagon.do?routeNumber=<%=route.getRouteNumber()%>'"
                 style="width: 250px;height: 60px;"><b>Add wagons</b></button>
    </td></tr>
    <tr><td>
        <button  class="submit" onclick="location.href='http://localhost:8080/insertStation.do?routeNumber=<%=route.getRouteNumber()%>'"
                 style="width: 250px;height: 60px;"><b>Add stations</b></button>
    </td></tr>
</TABLE>
</body>
</html>
