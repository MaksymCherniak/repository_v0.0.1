<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script>
    function logout(url) {
        if (confirm(' Logout? ')) {
            document.location=url;
            return true;
        }
        return false;
    }
    function check() {
        if (document.contact.surname.value == "") {
            alert("Enter surname please");
            return false;
        }
        if (document.contact.name.value == "") {
            alert("Enter name please");
            return false;
        }
        if (document.contact.patronym.value == "") {
            alert("Enter full name please");
            return false;
        }
        if (document.contact.mobile.value == "") {
            alert("Enter mobile phone number please");
            return false;
        }
    }
</script>

<head>
    <meta charset="utf-8">
    <title>Add contact</title>
    <spring:url value="/resources/css/login.css" var="loginCss" />
    <link href="${loginCss}" rel="stylesheet" />
</head>
<body>
<span style="float: right;padding-right: 30px">
        <a href="addContactGet.do?lang=en"><img src="/resources/images/us.gif"></a>
        <a href="addContactGet.do?lang=ru"><img src="/resources/images/ru.gif"></a>
    </span>
<div class="container">
    <section id="content" style="width: 550px">
        <form name="contact" action="addContact.do" method="post" onSubmit="return check()">
            <h1><spring:message code="add contact form"/></h1>
            <div>
                <h4><spring:message code="enter"/> <spring:message code="contact surname"/>: <b style="color: red">*</b> </h4>
                <input type="text" required="" name="surname" style="background: #eae7e7" />
            </div>
            <div>
                <h4><spring:message code="enter"/> <spring:message code="contact name"/>: <b style="color: red">*</b> </h4>
                <input type="text" required="" name="name" style="background: #eae7e7" />
            </div>
            <div>
                <h4><spring:message code="enter"/> <spring:message code="contact patronym"/>: <b style="color: red">*</b> </h4>
                <input type="text" required="" name="patronym" style="background: #eae7e7" />
            </div>
            <div>
                <h4><spring:message code="enter"/> <spring:message code="contact mobile"/>: <b style="color: red">*</b> </h4>
                <input type="text" required="" name="mobile" style="background: #eae7e7" />
            </div>
            <div>
                <h4><spring:message code="enter"/> <spring:message code="contact home"/>: </h4>
                <input type="text" name="home" style="background: #eae7e7;" />
            </div>
            <div>
                <h4><spring:message code="enter"/> <spring:message code="contact address"/>: </h4>
                <input type="text" name="address" style="background: #eae7e7" />
            </div>
            <div>
                <h4><spring:message code="enter"/> <spring:message code="contact email"/>: </h4>
                <input type="text" name="email" style="background: #eae7e7" />
            </div>
            <div>
                <input type="submit" value="<spring:message code="add"/>" style="float: inherit" />
            </div>
        </form>
        <div class="button">
            ${info}
        </div>
        <br>
        <h1><spring:message code="actions with user"/></h1>
        <div class="button">
            <a href="userMainPage.do"><spring:message code="user main page"/></a>
        </div>
        <div class="button">
            <a href="#" onclick="logout('http://localhost:8080/logOut.do')"><spring:message code="logout"/></a>
        </div>
    </section>
</div>
</body>
</html>
