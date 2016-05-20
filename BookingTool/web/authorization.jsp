<%@ page import="BookingTool.Entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <meta charset="utf-8">
    <title>Login</title>
    <spring:url value="/resources/css/main.css" var="mainCss" />
    <spring:url value="/resources/css/table.css" var="tableCss" />
    <link href="${mainCss}" rel="stylesheet" />
    <link href="${tableCss}" rel="stylesheet" />
</head>

<% User user = (User) request.getSession().getAttribute("user"); %>

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
        <% } %>
        <li style="float: right"><a href="authorization.jsp">Login</a></li>
        <li style="float: right"><a href="registration.jsp">Registration</a></li>
    </ul>
</div>
<br>
<br>
<table align="center">
    <form name="authorization" action="authorization.do">
        <tr><h1 align="center">Registration form</h1></tr>
        <tr>
            <td width="300px"><h1 align="right">Enter your email(login): </h1></td>
            <td width="300px">&nbsp;&nbsp;<input type="text" required="" class="text" name="email" style="width: 250px"></td>
        </tr>
        <tr>
            <td width="300px"><h1 align="right">Enter your password: </h1></td>
            <td width="300px">&nbsp;&nbsp;<input type="password" required="" class="text" name="password" style="width: 250px"></td>
        </tr>
        <TABLE border="0" align="center">
            <tr>
                <td><input type="submit" class="submit" value="Login"></td>
            </tr>
        </TABLE>
    </form>
</table>
<TABLE border="0" align="center">
    <tr>
        <td><h1>${info}</h1></td>
    </tr>
</TABLE>
</body>
</html>
