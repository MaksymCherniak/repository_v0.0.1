<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script>
    function check(){
        if (document.register.login.value == "") {
            alert("Enter login please");
            return false;
        }
        if (document.register.password.value == "") {
            alert("Enter password please");
            return false;
        }
        if (document.register.fullName.value == "") {
            alert("Enter full name please");
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
<div class="container">
    <section id="content" style="width: 550px">
        <form name="register" action="register.do" method="post" onSubmit="return check()">
            <h1>Registration Form</h1>
            <div>
                <h4>Enter your login: </h4>
                <input type="text" required="" name="login" style="background: #eae7e7" />
            </div>
            <div>
                <h4>Enter your password: </h4>
                <input type="password" required="" name="password" style="background: #eae7e7" />
            </div>
            <div>
                <h4>Enter your full name: </h4>
                <input type="text" required="" name="fullName" style="background: #eae7e7" />
            </div>
            <div>
                <input type="submit" value="Registration" style="float: inherit" />
            </div>
        </form><!-- form -->
        <div class="button">
            ${info}
        </div><!-- button -->
    </section><!-- content -->
</div><!-- container -->
</body>
</html>
