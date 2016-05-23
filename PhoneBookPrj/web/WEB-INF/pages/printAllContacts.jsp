<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page import="PhoneBook.Entity.Contact" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Contacts</title>
    <spring:url value="/resources/css/login.css" var="loginCss" />
    <link href="${loginCss}" rel="stylesheet" />
</head>

<script>
    function logout(url) {
        if (confirm(' Logout? ')) {
            document.location=url;
            return true;
        }
        return false;
    }
    function deleteContact(url) {
        if (confirm(' Delete contact? ')) {
            document.location=url;
            return true;
        }
        return false;
    }
</script>

<% List<Contact> listOfContacts = (List<Contact>) request.getAttribute("listOfContacts"); %>

<body>
<div class="container" style="width: 1380px">
    <span style="float: right;padding-right: 30px">
        <a href="printAllContacts.do?lang=en"><img src="/resources/images/us.gif"></a>
        <a href="printAllContacts.do?lang=ru"><img src="/resources/images/ru.gif"></a>
    </span>
    <section id="content" style="width: 1200px">
        <h1><spring:message code="all contacts"/></h1>
        <div>
        <table class="button" align="center" border="1" cellspacing="0" cellpadding="0" style="border-color: gray" style="width: 900px">
            <thead>
            <tr align="left" style="font-size: 8pt;font-family: Tahoma;color: black;height: 30px">
                <th align="center" style="width: 120px"><b><spring:message code="surname"/></b></th>
                <th align="center" style="width: 120px"><b><spring:message code="name"/></b></th>
                <th align="center" style="width: 120px"><b><spring:message code="patronym"/></b></th>
                <th align="center" style="width: 150px"><b><spring:message code="email"/></b></th>
                <th align="center" style="width: 150px"><b><spring:message code="address"/></b></th>
                <th align="center" style="width: 150px"><b><spring:message code="mobile phone number"/></b></th>
                <th align="center" style="width: 150px"><b><spring:message code="home phone number"/></b></th>
                <th align="center" style="width: 150px"><b><spring:message code="edit contact"/></b></th>
                <th align="center" style="width: 150px"><b><spring:message code="delete contact"/></b></th>
            </tr>
            </thead>
            <tbody>

            <%  if (listOfContacts.size() != 0) {
                for (int i = 0; i < listOfContacts.size(); i++) { %>
            <tr align="left" style="font-size: 8pt;font-family: Tahoma;color: black; height: 30px">
                <td align="center"><b><%=listOfContacts.get(i).getSurname()%></b></td>
                <td align="center"><b><%=listOfContacts.get(i).getName()%></b></td>
                <td align="center"><b><%=listOfContacts.get(i).getPatronym()%></b></td>
                <td align="center"><b><%=listOfContacts.get(i).getEmail()%></b></td>
                <td align="center"><b><%=listOfContacts.get(i).getAddress()%></b></td>
                <td align="center"><b><%=listOfContacts.get(i).getMobile()%></b></td>
                <td align="center"><b><%=listOfContacts.get(i).getHome()%></b></td>
                <td align="center"><input type="button" value="<spring:message code="edit contact"/>" style="width: 130px"
                           onclick="location.href='http://localhost:8080/editContactGet.do?contact_id=<%=listOfContacts.get(i).getId()%>'"></td>
                <td align="center"><input type="button" value="<spring:message code="delete contact"/>" style="width: 130px"
                           onclick="deleteContact('http://localhost:8080/deleteContact.do?contact_id=<%=listOfContacts.get(i).getId()%>')"></td>
            </tr>
            <% }
            %>
            </tbody>
        </table>
            </div>
        <div class="button">
            <a href="addContactGet.do"><spring:message code="add contact"/></a>
        </div>
        <% } %>
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
