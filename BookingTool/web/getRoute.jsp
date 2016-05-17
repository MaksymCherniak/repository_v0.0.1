<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Select route</title>
    <spring:url value="/resources/css/main.css" var="mainCss" />
    <spring:url value="/resources/css/table.css" var="tableCss" />
    <link href="${mainCss}" rel="stylesheet" />
    <link href="${tableCss}" rel="stylesheet" />
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
<table align="center">
    <form name="selectRoute" action="selectRoute.do">
        <tr><h1 align="center">Select route</h1></tr>
        <tr>
            <td width="250px"><h1 align="right">Enter leaving stations: </h1></td>
            <td width="300px">&nbsp;&nbsp;<input type="text" class="text" name="leavingStation" style="width: 250px"></td>
        </tr>
        <tr>
            <td width="250px"><h1 align="right">Enter arrival stations: </h1></td>
            <td width="300px">&nbsp;&nbsp;<input type="text" class="text" name="arrivalStation" style="width: 250px"></td>
        </tr>
        <tr>
            <td width="250px"><h1 align="right">Enter leaving date: </h1></td>
            <td width="300px">&nbsp;&nbsp;<select name="routeDD" style="margin-bottom: 14px">
                <% for (int i = 1; i <= 31; i++) { %>
                <option value="<%=i%>"><%=i%></option>
                <% } %>
            </select><b style="margin-bottom: 14px"> - </b><select name="routeMM" style="margin-bottom: 14px">
                <option value="1">January</option>
                <option value="2">February</option>
                <option value="3">March</option>
                <option value="4">April</option>
                <option value="5">May</option>
                <option value="6">June</option>
                <option value="7">July</option>
                <option value="8">August</option>
                <option value="9">September</option>
                <option value="10">October</option>
                <option value="11">November</option>
                <option value="12">December</option>
            </select><b style="margin-bottom: 14px"> - </b><select name="routeYY" style="margin-bottom: 14px">
                <option value="2016">2016</option>
                <option value="2017">2017</option>
            </select></td>
        </tr>
        <TABLE border="0" align="center">
            <tr>
                <td><input type="submit" class="submit" value="Enter"></td>
            </tr>
        </TABLE>
    </form>
</table>
</body>
</html>