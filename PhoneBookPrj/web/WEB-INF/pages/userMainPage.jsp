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
<span style="float: right;padding-right: 30px">
        <a href="userMainPage.do?lang=en"><img src="/resources/images/us.gif"></a>
        <a href="userMainPage.do?lang=ru"><img src="/resources/images/ru.gif"></a>
    </span>
<div class="container">
    <section id="content">
        <h1><spring:message code="actions with contacts"/></h1>
        <div class="button">
            <a href="printAllContacts.do"><spring:message code="print all contacts"/></a>
        </div>
        <div class="button">
            <a href="addContactGet.do"><spring:message code="add contact"/></a>
        </div>
        <br>
        <h1><spring:message code="actions with user"/></h1>
        <div class="button">
            <a href="logOut.do" onclick="logout('http://localhost:8080/logOut.do')"><spring:message code="logout"/></a>
        </div>
        <div class="button">
            ${info}
        </div>
    </section>
</div>
</body>
</html>
