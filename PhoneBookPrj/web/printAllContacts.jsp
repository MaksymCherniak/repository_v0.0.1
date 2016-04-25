<%@ page import="java.util.List" %>
<%@ page import="PhoneBook.Entity.Contact" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Contacts</title>
</head>
<body bgcolor="#dcdcdc">
<% List<Contact> listOfContacts = (List<Contact>) request.getAttribute("listOfContacts");
%>
<TABLE align="right">
    <tr><td><a href="logOut.do">logout</a></td></tr>
</TABLE><br>
<table align="center" bgcolor="#8fbc8f" border="1" cellspacing="0" cellpadding="0" style="border-color: gray">
    <caption><b>Contacts</b></caption>
    <tr align="left" style="font-size: 8pt;font-family: Tahoma;color: black;height: 20px">
        <td align="center" style="width: 150px"><b>Full name</b></td>
        <td align="center" style="width: 150px"><b>E-mail</b></td>
        <td align="center" style="width: 150px"><b>Address</b></td>
        <td align="center" style="width: 150px"><b>Mobile phone number</b></td>
        <td align="center" style="width: 150px"><b>Home phone number</b></td>
        <td align="center" style="width: 150px"><b>Action</b></td>
    </tr>
<% for (int i = 0; i < listOfContacts.size(); i++) { %>
    <tr align="left" style="font-size: 8pt;font-family: Tahoma;color: black">
        <td align="center" style="width: 150px"><b><%=listOfContacts.get(i).getSurname() + " "
                + listOfContacts.get(i).getName() + " " + listOfContacts.get(i).getPatronym()%></b></td>
        <td align="center" style="width: 150px"><b><%=listOfContacts.get(i).getEmail()%></b></td>
        <td align="center" style="width: 150px"><b><%=listOfContacts.get(i).getAddress()%></b></td>
        <td align="center" style="width: 150px"><b><%=listOfContacts.get(i).getMobilePhoneNumber()%></b></td>
        <td align="center" style="width: 150px"><b><%=listOfContacts.get(i).getHomePhoneNumber()%></b></td>
        <td align="center" style="width: 150px">
            <input type="button" value="Edit contact"
                   onclick="location.href='http://localhost:8080/editContact.do?contact_id=<%=listOfContacts.get(i).getId()%>'"><br>
            <input type="button" value="Delete contact"
                   onclick="location.href='http://localhost:8080/deleteContact.do?contact_id=<%=listOfContacts.get(i).getId()%>'"></td>
    </tr>
<% } %>
</table>
<TABLE align="center">
    <tr><td><input type="button" onclick="location.href='http://localhost:8080/addContact.jsp'" value="Add new contact"></td></tr>
</TABLE><br>
</body>
</html>
