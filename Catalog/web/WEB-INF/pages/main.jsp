<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <head>
        <meta charset="utf-8">
        <title>Contacts</title>
        <spring:url value="/resources/css/main.css" var="mainCss" />
        <spring:url value="/resources/css/table.css" var="tableCss" />
        <link href="${mainCss}" rel="stylesheet" />
        <link href="${tableCss}" rel="stylesheet" />
    </head>
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

<body>
<ul class="navbar">
    <li><a class="active" href="main">Home</a></li>
    <li><a href="getFilms">Films</a></li>
    <li><a href="getSerials">Serials</a></li>
    <li><a href="getCartoons">Cartoons</a></li>
    <li><a href="getSounds">Sounds</a></li>
    <li><a href="getBooks">Books</a></li>
    <li style="float: right"><a href="#" onclick="logout('http://localhost:8080/logout')">Logout</a></li>
</ul>
<div class="content-nav" style="margin-top: 35px">
    <ul>
        <li><a href="#">Top-10 Films</a></li>
        <li><a href="#">Top-10 Serials</a></li>
        <li><a href="#">Top-10 Cartoons</a></li>
        <li><a href="#">Top-10 Music</a></li>
        <li><a href="#">Top-10 Books</a></li>
    </ul>
</div>
<br><br>
    <div class="input-box">
        <h2>Fixed Full-height Side Nav</h2>
        <h3>Try to scroll this area, and see how the sidenav sticks to the page</h3>
        <p>Notice that this div element has a left margin of 25%. This is because the side navigation is set to 25% width. If you remove the margin, the sidenav will overlay/sit on top of this div.</p>
        <p>Also notice that we have set overflow:auto to sidenav. This will add a scrollbar when the sidenav is too long (for example if it has over 50 links inside of it).</p>
        <p>Some text..</p>
        <p>Some text..</p>
        <p>Some text..</p>
        <p>Some text..</p>
        <p>Some text..</p>
        <p>Some text..</p>
        <p>Some text..</p>
    </div>

</body>
</html>
