<%@ page import="BookingTool.Entity.Route" %>
<%@ page import="BookingTool.Entity.User" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Insert stations</title>
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
<table align="center">
    <form name="insertStation" action="insertStation.do" method="post">
        <input type="hidden" name="routeNumber" value="<%=route.getRouteNumber()%>">
        <tr><h1 align="center">Add stations</h1></tr>
        <tr>
            <td width="270px"><h1 align="right">Enter stations: </h1></td>
            <td width="300px">&nbsp;&nbsp;<input type="text" class="text" name="station" style="width: 250px"></td>
        <tr>
            <td width="250px"><h1 align="right">Enter arrival time: </h1></td>
            <td width="300px">&nbsp;&nbsp;<select name="arrivalHH" style="margin-bottom: 14px">
                <% for (int i = 0; i < 24; i++) { %>
                <option value="<%=i%>"><%=i%></option>
                <% } %></select>
                <b style="margin-bottom: 14px"> : </b><select name="arrivalMM" style="margin-bottom: 14px">
                    <% for (int i = 0; i < 60; i++) { %>
                    <option value="<%=i%>"><%=i%></option>
                    <% } %>
                </select></td>
        </tr>
        <tr>
            <td width="250px"><h1 align="right">Enter leaving time: </h1></td>
            <td width="300px">&nbsp;&nbsp;<select name="leavingHH" style="margin-bottom: 14px">
                <% for (int i = 0; i < 24; i++) { %>
                <option value="<%=i%>"><%=i%></option>
                <% } %></select>
                <b style="margin-bottom: 14px"> : </b><select name="leavingMM" style="margin-bottom: 14px">
                    <% for (int i = 0; i < 60; i++) { %>
                    <option value="<%=i%>"><%=i%></option>
                    <% } %>
                </select></td>
        </tr>
        <TABLE border="0" align="center">
            <tr>
                <td><input type="submit" class="submit" value="Add stations"></td>
            </tr>
        </TABLE>
        <TABLE border="0" align="center">
            <tr>
                <td><h1>${info}</h1></td>
            </tr>
        </TABLE>
    </form>
</table>
</body>
</html>
