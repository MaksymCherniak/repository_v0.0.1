<%@ page import="Catalog.Entity.Film" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Contacts</title>
    <spring:url value="/resources/css/main.css" var="mainCss" />
    <spring:url value="/resources/css/content.css" var="contentCss" />
    <link href="${mainCss}" rel="stylesheet" />
    <link href="${contentCss}" rel="stylesheet" />
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

<% List<Film> listOfFilms = (List<Film>) request.getAttribute("listOf");
int i = 0; %>

<body>
<div class="navbar">
    <li><a href="main">Home</a></li>
    <li><a class="active" href="getFilms">Films</a></li>
    <li><a href="getSerials">Serials</a></li>
    <li><a href="getCartoons">Cartoons</a></li>
    <li><a href="getSounds">Sounds</a></li>
    <li><a href="getBooks">Books</a></li>
    <li style="float: right"><a href="#" onclick="logout('http://localhost:8080/logout')">Logout</a></li>
</div>
<div class="content-nav" style="margin-top: 51px">
    <ul>
        <li><a href="addFilm">Add new film</a></li>
        <li><a href="getFilms">Print my films</a></li>
        <li><a href="getFilmsByGenre">Get films by genre</a></li>
        <li><a href="#">Top-10 Films</a></li>
        <li><a href="#">10 last added films</a></li>
    </ul>
</div>
<br><br>
<div class="content-list">
    <h1 style="margin-left: 25px;">Films</h1>
    <% while (i < listOfFilms.size()) {%>
    <table>
        <tbody>
        <tr>
            <% for (int j = i; j < listOfFilms.size() && j < i + 6; j++ ) {%>
            <td>
                <div class="content-tile">
                    <a class="content-tile__link" href="printFilm?film_id=<%=listOfFilms.get(j).getId()%>">
                        <span class="content-tile__title-info-items">Title: <%=listOfFilms.get(j).getTitle()%></span>
                        <span class="content-tile__title-info-items">Producer: <%=listOfFilms.get(j).getProducer()%></span>
                        <span class="content-tile__title-info-items">Country: <%=listOfFilms.get(j).getCountry()%></span>
                        <span class="content-tile__title-info-items">Genre: <%=listOfFilms.get(j).getGenreFilm()%></span>
                        <span class="content-tile__title-info-items">Released: <%=listOfFilms.get(j).getReleased()%></span>
                    </a>
                </div>
            </td>
            <% } i += 6; %>
        </tr>
        </tbody>
    </table>
    <% } %>
</div>
</body>
</html>
