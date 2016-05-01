<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page import="PhoneBook.Entity.User" %>
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
<% User user = (User) request.getAttribute("user");%>
<head>
    <meta charset="utf-8">
    <title>Add contact</title>
    <spring:url value="/resources/css/login.css" var="loginCss" />
    <link href="${loginCss}" rel="stylesheet" />
</head>
<body>
<div class="container">
    <section id="content" style="width: 550px">
        <form name="contact" action="addContact.do" method="post" onSubmit="return check()">
            <input type="hidden" name="user_id" value="<%=user.getId()%>">
            <h1>Add contact form</h1>
            <div>
                <h4>Enter contact surname: <b style="color: red">*</b> </h4>
                <input type="text" required="" name="surname" style="background: #eae7e7" />
            </div>
            <div>
                <h4>Enter contact name: <b style="color: red">*</b> </h4>
                <input type="text" required="" name="name" style="background: #eae7e7" />
            </div>
            <div>
                <h4>Enter contact patronym: <b style="color: red">*</b> </h4>
                <input type="text" required="" name="patronym" style="background: #eae7e7" />
            </div>
            <div>
                <h4>Enter contact mobile phone number: <b style="color: red">*</b> </h4>
                <input type="text" required="" name="mobile" style="background: #eae7e7" />
            </div>
            <div>
                <h4>Enter contact home phone number: </h4>
                <input type="text" name="home" style="background: #eae7e7;" />
            </div>
            <div>
                <h4>Enter contact address: </h4>
                <input type="text" name="address" style="background: #eae7e7" />
            </div>
            <div>
                <h4>Enter contact e-mail: </h4>
                <input type="text" name="email" style="background: #eae7e7" />
            </div>
            <div>
                <input type="submit" value="Add contact" style="float: inherit" />
            </div>
        </form><!-- form -->
        <div class="button">
            ${info}
        </div><!-- button -->
        <br>
        <h1>Actions with user</h1>
        <div class="button">
            <a href="authorization.do?login=<%=user.getLogin()%>&password=<%=user.getPassword()%>">User main page</a>
        </div><!-- button -->
        <div class="button">
            <a href="#" onclick="logout('http://localhost:8080/logOut.do')">Logout</a>
        </div><!-- button -->
    </section><!-- content -->
</div><!-- container -->
</body>
</html>
