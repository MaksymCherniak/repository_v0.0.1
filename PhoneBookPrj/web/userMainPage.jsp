<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>User main page</title>
    <spring:url value="/resources/css/login.css" var="loginCss"/>
    <link href="${loginCss}" rel="stylesheet"/>
</head>
<script>
    function logout(url) {
        if (confirm(' Logout? ')) {
            document.location = url;
            return true;
        }
        return false;
    }
</script>

<body>
<div class="container">
    <section id="content">
        <h1>Actions with contacts</h1>
        <div class="button">
            <a href="printAllContacts.do">Print all contacts</a>
        </div><!-- button -->
        <div class="button">
            <a href="addContact.jsp">Add contact</a>
        </div><!-- button -->
        <br>
        <h1>Actions with user</h1>
        <div class="button">
            <a href="logOut.do" onclick="logout('http://localhost:8080/logOut.do')">Logout</a>
        </div><!-- button -->
        <div class="button">
            ${info}
        </div><!-- button -->
    </section><!-- content -->
</div><!-- container -->
</body>
</html>
