<%@ page import="Catalog.Entity.Enum.GenreFilm" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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

<% List<GenreFilm> genreFilms = (List<GenreFilm>) request.getAttribute("listOf"); %>

<body>
<ul class="navbar">
    <li><a href="main">Home</a></li>
    <li><a href="getFilms">Films</a></li>
    <li><a class="active" href="getSerials">Serials</a></li>
    <li><a href="getCartoons">Cartoons</a></li>
    <li><a href="getSounds">Sounds</a></li>
    <li><a href="getBooks">Books</a></li>
    <li style="float: right"><a href="#" onclick="logout('http://localhost:8080/logout')">Logout</a></li>
</ul>
<div class="content-nav" style="margin-top: 51px">
    <ul>
        <li><a href="addSerial">Add new serial</a></li>
        <li><a href="getSerials">Print my serials</a></li>
        <li><a href="getSerialsByGenre">Get serials by genre</a></li>
        <li><a href="#">Top-10 Serials</a></li>
        <li><a href="#">10 last added serials</a></li>
    </ul>
</div>
<br><br>
<div class="input-box">
    <div>
        <form method="post" action="getSerialsByGenre">

            <label>Select genre:</label>
            <select name="genreFilm">
                <% for (GenreFilm genreFilm : genreFilms) { %>
                <option value="<%=genreFilm%>"><%=genreFilm%>
                </option>
                <% } %>
            </select>

            <input type="submit" value="Select">
        </form>
    </div>
</div>
</body>
</html>
