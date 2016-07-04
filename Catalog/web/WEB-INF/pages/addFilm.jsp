<%@ page import="Catalog.Entity.Enum.GenreFilm" %>
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

<% List<GenreFilm> genreFilms = (List<GenreFilm>) request.getAttribute("listOf"); %>
<% List<String> listOfCountries = (List<String>) request.getAttribute("countries"); %>

<body>
<ul class="navbar">
    <li><a href="main">Home</a></li>
    <li><a class="active" href="getFilms">Films</a></li>
    <li><a href="getSerials">Serials</a></li>
    <li><a href="getCartoons">Cartoons</a></li>
    <li><a href="getSounds">Sounds</a></li>
    <li><a href="getBooks">Books</a></li>
    <li style="float: right"><a href="#" onclick="logout('http://localhost:8080/logout')">Logout</a></li>
</ul>
<div class="content-nav" style="margin-top: 35px">
    <ul>
        <li><a href="addFilm">Add new film</a></li>
        <li><a href="getFilms">Print my films</a></li>
        <li><a href="getFilmsByGenre">Get films by genre</a></li>
        <li><a href="#">Top-10 Films</a></li>
        <li><a href="#">10 last added films</a></li>
    </ul>
</div>
<br><br>
<div class="input-box">
    <div>
        <h1 align="center">Add new film</h1>
        <form:form method="post" commandName="film" action="addFilm">
            <form:label path="title">Title:</form:label>
            <form:input path="title"/>

            <form:label path="producer">Producer:</form:label>
            <form:input path="producer"/>

            <form:label path="country">Country:</form:label>
            <form:select path="country">
                <% for (String country : listOfCountries) { %>
                <form:option value="<%=country%>"><%=country%></form:option>
                <% } %>
            </form:select>

            <form:label path="genreFilm">Genre:</form:label>
            <form:select path="genreFilm">
                <% for (GenreFilm genreFilm : genreFilms) { %>
                    <form:option value="<%=genreFilm%>"><%=genreFilm%></form:option>
                <% } %>
            </form:select>

            <form:label path="released">Released:</form:label>
            <form:select path="released">
                <% for (int i = 2016; i >= 1980; i-- ) { %>
                    <form:option value="<%=i%>"><%=i%></form:option>
                <% } %>
            </form:select>

            <form:label path="description">Description:</form:label>
            <form:textarea path="description"/>

            <input type="submit" value="Add film">
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
