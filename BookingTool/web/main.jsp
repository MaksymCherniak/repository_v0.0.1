<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Contacts</title>
    <spring:url value="/resources/css/main.css" var="mainCss" />
    <link href="${mainCss}" rel="stylesheet" />
</head>
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
<TABLE border="0" align="center">
    <tr>
        <td><h1>${info}</h1></td>
    </tr>
</TABLE>
</body>
</html>