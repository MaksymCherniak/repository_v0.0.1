<%@ page import="Catalog.Entity.Enum.GenreSound" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Contacts</title>
    <spring:url value="/resources/css/main.css" var="mainCss" />
    <spring:url value="/resources/css/table.css" var="tableCss" />
    <link href="${mainCss}" rel="stylesheet" />
    <link href="${tableCss}" rel="stylesheet" />
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

<% List<GenreSound> genreSounds = (List<GenreSound>) request.getAttribute("listOf"); %>

<body>
<ul class="navbar">
    <li><a href="main">Home</a></li>
    <li><a href="getFilms">Films</a></li>
    <li><a href="getSerials">Serials</a></li>
    <li><a href="getCartoons">Cartoons</a></li>
    <li><a class="active" href="getSounds">Sounds</a></li>
    <li><a href="getBooks">Books</a></li>
    <li style="float: right"><a href="#" onclick="logout('http://localhost:8080/logout')">Logout</a></li>
</ul>
<div class="content-nav" style="margin-top: 35px">
    <ul>
        <li><a href="addSound">Add new sound</a></li>
        <li><a href="getSounds">Print my sounds</a></li>
        <li><a href="getSoundsByGenre">Get sounds by genre</a></li>
        <li><a href="#">Top-10 Sounds</a></li>
        <li><a href="#">10 last added sounds</a></li>
    </ul>
</div>
<br><br>
<div class="input-box">
    <div>
        <h1 align="center">Add new sound</h1>
        <form:form method="post" commandName="sound" action="addSound">
            <form:label path="title">Title:</form:label>
            <form:input path="title"/>

            <form:label path="singer">Singer:</form:label>
            <form:input path="singer"/>

            <form:label path="genreSound">Genre:</form:label>
            <form:select path="genreSound">
                <% for (GenreSound genreSound : genreSounds) { %>
                <form:option value="<%=genreSound%>"><%=genreSound%>
                </form:option>
                <% } %>
            </form:select>

            <input type="submit" value="Add sound">
        </form:form>
        <TABLE border="0" align="center">
            <tr>
                <td><h1>${info}</h1></td>
            </tr>
        </TABLE>
    </div>
</div>
</body>
</html>
