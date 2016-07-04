<%@ page import="Catalog.Entity.Enum.GenreBook" %>
<%@ page import="java.util.List" %>
<%@ page import="Catalog.Entity.Enum.GenreFilm" %>
<%@ page import="Catalog.Entity.Enum.GenreSound" %>
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

<% List<GenreSound> genreSounds = (List<GenreSound>) request.getAttribute("listOf");
int i = 0; %>

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
        <form method="post" action="getSoundsByGenre">

            <label>Select genre:</label>
            <select name="genreFilm">
                <% for (GenreSound genreSound : genreSounds) { %>
                <option value="<%=genreSound%>"><%=genreSound%>
                </option>
                <% } %>
            </select>

            <input type="submit" value="Select">
        </form>
    </div>
</div>
</body>
</html>
