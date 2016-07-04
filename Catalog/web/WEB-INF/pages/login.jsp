<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<script>
    function check(){
        if (document.loginForm.login.value == "") {
            alert("Enter surname please");
            return false;
        }
        if (document.loginForm.password.value == "") {
            alert("Enter name please");
            return false;
        }
    }
</script>
<head>
    <meta charset="utf-8">
    <title>Main</title>
    <spring:url value="/resources/css/login.css" var="loginCss" />
    <link href="${loginCss}" rel="stylesheet" />
</head>
<body>
<body>
<span style="float: right;padding-right: 30px">
        <a href="/?lang=en"><img src="/resources/images/us.gif"></a>
        <a href="/?lang=ru"><img src="/resources/images/ru.gif"></a>
    </span>
<div class="container">
    <section id="content">
        <form name="loginForm" action="login" onSubmit="return check()">
            <h1>Login form</h1>
            <div>
                <input type="text" placeholder="Username" required="" name="login" id="username" />
            </div>
            <div>
                <input type="password" placeholder="Password" required="" name="password" id="password" />
            </div>
            <div>
                <input type="submit" value="Login" />
                <a href="registerPage">Registration</a>
            </div>
        </form>
        <div class="button">
            ${info}
        </div>
    </section>
</div>
</body>
</body>
</html>
</html>