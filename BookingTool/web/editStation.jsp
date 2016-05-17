<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page import="BookingTool.Entity.Stations" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Edit stations</title>
    <spring:url value="/resources/css/main.css" var="mainCss" />
    <spring:url value="/resources/css/table.css" var="tableCss" />
    <spring:url value="/resources/css/buttons.css" var="buttonsCss" />
    <link href="${mainCss}" rel="stylesheet" />
    <link href="${tableCss}" rel="stylesheet" />
    <link href="${buttonsCss}" rel="stylesheet" />
</head>

<% Stations stations = (Stations) request.getAttribute("stations"); %>

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
    <form name="editStation" action="editStation.do" method="post">
        <input type="hidden" name="station_id" value="<%=stations.getId()%>">
        <tr><h1 align="center">Edit stations</h1></tr>
        <tr>
            <td width="250px"><h1 align="right">Station(<%=stations.getStation()%>): </h1></td>
            <td width="300px">&nbsp;&nbsp;<input type="text" class="text" name="stations" style="width: 250px"></td>
        </tr>
        <tr>
            <td width="250px"><h1 align="right">Arrival time(<%=stations.getArrivalTime()%>): </h1></td>
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
            <td width="250px"><h1 align="right">Leaving time(<%=stations.getLeavingTime()%>): </h1></td>
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
                <td><input type="submit" class="submit" value="Edit"></td>
            </tr>
        </TABLE>
    </form>
</table>
</body>
</html>
