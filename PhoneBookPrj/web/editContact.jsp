<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page import="PhoneBook.Entity.Contact" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Edit Contact</title>
    <spring:url value="/resources/css/login.css" var="loginCss" />
    <link href="${loginCss}" rel="stylesheet" />
</head>

<% Contact contact = (Contact) request.getAttribute("contact"); %>

<body>
<div class="container">
    <section id="content" style="width: 550px">
        <form name="contact" action="editContact.do" method="post">
            <input type="hidden" name="contact_id" value="<%=contact.getId()%>">
            <h1>Edit contact form</h1>
            <div>
                <h4>Surname (<%=contact.getSurname()%>) </h4>
                <input type="text" name="surname" style="background: #eae7e7" />
            </div>
            <div>
                <h4>Name (<%=contact.getName()%>) </h4>
                <input type="text" name="name" style="background: #eae7e7" />
            </div>
            <div>
                <h4>Patronym (<%=contact.getPatronym()%>) </h4>
                <input type="text" name="patronym" style="background: #eae7e7" />
            </div>
            <div>
                <h4>Mobile (<%=contact.getMobile()%>) </h4>
                <input type="text" name="mobile" style="background: #eae7e7" />
            </div>
            <div>
                <h4>Home phone number(<%=contact.getHome()%>) </h4>
                <input type="text" name="home" style="background: #eae7e7;" />
            </div>
            <div>
                <h4>Address (<%=contact.getAddress()%>) </h4>
                <input type="text" name="address" style="background: #eae7e7" />
            </div>
            <div>
                <h4>E-mail (<%=contact.getEmail()%>) </h4>
                <input type="text" name="email" style="background: #eae7e7" />
            </div>
            <div>
                <input type="submit" value="Edit contact" style="float: inherit" />
            </div>
        </form><!-- form -->
        <div class="button">
            ${info}
        </div><!-- button -->
        <br>
        <h1>Actions with user</h1>
        <div class="button">
            <a href="userMainPage.jsp">User main page</a>
        </div><!-- button -->
        <div class="button">
            <a href="#" onclick="logout('http://localhost:8080/logOut.do')">Logout</a>
        </div><!-- button -->
    </section><!-- content -->
</div><!-- container -->
</body>
</html>
