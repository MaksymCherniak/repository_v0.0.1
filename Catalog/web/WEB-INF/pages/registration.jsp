<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script>
    function check(){
        if (document.register.login.value.length < 3) {
            alert("Invalid login format");
            return false;
        }
        if (document.register.password.value.length < 5) {
            alert("Invalid password format");
            return false;
        }
        if (document.register.name.value.length < 2) {
            alert("Invalid name format");
            return false;
        }
        if (document.register.surname.value.length < 2) {
            alert("Invalid surname format");
            return false;
        }
    }
</script>
<head>
    <meta charset="utf-8">
    <title>Registration</title>
    <spring:url value="/resources/css/login.css" var="loginCss" />
    <link href="${loginCss}" rel="stylesheet" />
</head>
<body>
<span style="float: right;padding-right: 30px">
        <a href="registerPage?lang=en"><img src="/resources/images/us.gif"></a>
        <a href="registerPage?lang=ru"><img src="/resources/images/ru.gif"></a>
    </span>
<div class="container">
    <section id="content" style="width: 550px">
        <form name="register" action="registration" method="post" onSubmit="return check()">
            <h1>Registration form</h1>
            <div>
                <h4>Enter login: </h4>
                <input type="text" required="" name="login" style="background: #eae7e7" />
            </div>
            <div>
                <h4>Enter password: </h4>
                <input type="password" required="" name="password" style="background: #eae7e7" />
            </div>
            <div>
                <h4>Enter last name: </h4>
                <input type="text" required="" name="surname" style="background: #eae7e7" />
            </div>
            <div>
                <h4>Enter first name: </h4>
                <input type="text" required="" name="name" style="background: #eae7e7" />
            </div>
            <div>
                <h4>Enter email: </h4>
                <input type="text" required="" name="email" style="background: #eae7e7" />
            </div>
            <div>
                <input type="submit" value="Registration" style="float: inherit" />
            </div>
        </form>
        <div class="button">
            <a href="/">Login page</a>
        </div>
        <div class="button">
            ${info}
        </div>
    </section>
</div>
</body>
</html>
