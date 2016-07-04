<%@ page import="Catalog.Entity.Serial" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Contacts</title>
    <spring:url value="/resources/css/main.css" var="mainCss" />
    <spring:url value="/resources/css/content.css" var="contentCss" />
    <spring:url value="/resources/css/table.css" var="tableCss" />
    <link href="${mainCss}" rel="stylesheet" />
    <link href="${contentCss}" rel="stylesheet" />
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

<% List<Serial> listOfSerials = (List<Serial>) request.getAttribute("listOf");
int i = 0; %>

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
<div class="content-list">
    <h1 style="margin-left: 25px;">Serials</h1>
    <% while (i < listOfSerials.size()) {%>
    <table>
        <tbody>
        <tr>
            <% for (int j = i; j < listOfSerials.size() && j < i + 6; j++ ) {%>
            <td>
                <div class="content-tile">
                    <a class="content-tile__link" href="printSerial?serial_id=<%=listOfSerials.get(j).getId()%>">
                        <span class="content-tile__title-info-items">Title: <%=listOfSerials.get(j).getTitle()%></span>
                        <span class="content-tile__title-info-items">Producer: <%=listOfSerials.get(j).getProducer()%></span>
                        <span class="content-tile__title-info-items">Country: <%=listOfSerials.get(j).getCountry()%></span>
                        <span class="content-tile__title-info-items">Genre: <%=listOfSerials.get(j).getGenreFilm()%></span>
                        <span class="content-tile__title-info-items">Released: <%=listOfSerials.get(j).getReleased()%></span>
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
