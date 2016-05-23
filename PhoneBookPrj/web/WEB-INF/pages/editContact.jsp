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
<span style="float: right;padding-right: 30px">
        <a href="editContactGet.do?contact_id=<%=contact.getId()%>&lang=en"><img src="/resources/images/us.gif"></a>
        <a href="editContactGet.do?contact_id=<%=contact.getId()%>&lang=ru"><img src="/resources/images/ru.gif"></a>
    </span>
<div class="container">
    <section id="content" style="width: 650px">
        <form name="contact" action="editContact.do" method="post">
            <input type="hidden" name="contact_id" value="<%=contact.getId()%>">
            <h1><spring:message code="edit contact form"/></h1>
            <div>
                <h4><spring:message code="surname"/> (<%=contact.getSurname()%>) </h4>
                <input type="text" name="surname" style="background: #eae7e7" />
            </div>
            <div>
                <h4><spring:message code="name"/> (<%=contact.getName()%>) </h4>
                <input type="text" name="name" style="background: #eae7e7" />
            </div>
            <div>
                <h4><spring:message code="patronym"/> (<%=contact.getPatronym()%>) </h4>
                <input type="text" name="patronym" style="background: #eae7e7" />
            </div>
            <div>
                <h4><spring:message code="mobile phone number"/> (<%=contact.getMobile()%>) </h4>
                <input type="text" name="mobile" style="background: #eae7e7" />
            </div>
            <div>
                <h4><spring:message code="home phone number"/>(<%=contact.getHome()%>) </h4>
                <input type="text" name="home" style="background: #eae7e7;" />
            </div>
            <div>
                <h4><spring:message code="address"/> (<%=contact.getAddress()%>) </h4>
                <input type="text" name="address" style="background: #eae7e7" />
            </div>
            <div>
                <h4><spring:message code="email"/> (<%=contact.getEmail()%>) </h4>
                <input type="text" name="email" style="background: #eae7e7" />
            </div>
            <div>
                <input type="submit" value="<spring:message code="edit contact"/>" style="float: inherit;width: 135px" />
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
