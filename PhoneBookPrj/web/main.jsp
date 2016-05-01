<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<div class="container">
    <section id="content">
        <form name="loginForm" action="authorization.do" onSubmit="return check()">
            <h1>Login Form</h1>
            <div>
                <input type="text" placeholder="Username" required="" name="login" id="username" />
            </div>
            <div>
                <input type="password" placeholder="Password" required="" name="password" id="password" />
            </div>
            <div>
                <input type="submit" value="Log in" />
                <a href="registerPage.jsp">Register</a>
            </div>
        </form><!-- form -->
        <div class="button">
            ${info}
        </div><!-- button -->
    </section><!-- content -->
</div><!-- container -->
</body>
</body>
</html>
</html>