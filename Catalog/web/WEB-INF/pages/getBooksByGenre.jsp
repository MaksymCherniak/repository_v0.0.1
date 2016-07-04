<%@ page import="Catalog.Entity.Enum.GenreBook" %>
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

<% List<GenreBook> genreBooks = (List<GenreBook>) request.getAttribute("listOf"); %>

<body>
<ul class="navbar">
    <li><a href="main">Home</a></li>
    <li><a href="getFilms">Films</a></li>
    <li><a href="getSerials">Serials</a></li>
    <li><a href="getCartoons">Cartoons</a></li>
    <li><a href="getSounds">Sounds</a></li>
    <li><a class="active" href="getBooks">Books</a></li>
    <li style="float: right"><a href="#" onclick="logout('http://localhost:8080/logout')">Logout</a></li>
</ul>
<div class="content-nav" style="margin-top: 35px">
    <ul>
        <li><a href="addBook">Add new book</a></li>
        <li><a href="getBooks">Print my books</a></li>
        <li><a href="getBooksByGenre">Get books by genre</a></li>
        <li><a href="#">Top-10 Books</a></li>
        <li><a href="#">10 last added books</a></li>
    </ul>
</div>
<br><br>
<div class="input-box">
    <div>
        <form method="post" action="getBooksByGenre">

            <label>Select genre:</label>
            <select name="genreBook">
                <% for (GenreBook genreBook : genreBooks) { %>
                <option value="<%=genreBook%>"><%=genreBook%>
                </option>
                <% } %>
            </select>

            <input type="submit" value="Select">
        </form>
    </div>
</div>
</body>
</html>
