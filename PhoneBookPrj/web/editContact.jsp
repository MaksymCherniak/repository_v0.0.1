<%@ page import="PhoneBook.Entity.Contact" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Contact</title>
</head>
<body bgcolor="#dcdcdc">
<% Contact contact = (Contact) request.getAttribute("contact"); %>
${info}
<TABLE align="right">
    <tr><td><a href="logOut.do">logout</a></td></tr>
</TABLE><br>
<table align="center" bgcolor="#8fbc8f" border="1" cellspacing="0" cellpadding="0" style="border-color: gray">
    <caption><b>Edit contact</b></caption>
    <form action="editContact.do" method="post">
        <input type="hidden" name="contact_id" value="<%=contact.getId()%>">
        <tr align="left" style="font-size: 8pt;font-family: Tahoma;color: black;height: 20px">
            <td align="center" style="width: 150px"><b>Attribute</b></td>
            <td align="center" style="width: 150px"><b>Value</b></td>
            <td align="center" style="width: 150px"><b>New value</b></td>
        </tr>
        <tr align="left" style="font-size: 8pt;font-family: Tahoma;color: black;height: 20px">
            <td align="center" style="width: 150px"><b style="color: mediumblue">Name</b></td>
            <td align="center" style="width: 150px"><b><%=contact.getName()%></b></td>
            <td align="center" style="width: 150px">
                <input type="text" name="name">
            </td>
        </tr>
        <tr align="left" style="font-size: 8pt;font-family: Tahoma;color: black;height: 20px">
            <td align="center" style="width: 150px"><b style="color: mediumblue">Surname</b></td>
            <td align="center" style="width: 150px"><b><%=contact.getSurname()%></b></td>
            <td align="center" style="width: 150px">
                <input type="text" name="surname">
            </td>
        </tr>
        <tr align="left" style="font-size: 8pt;font-family: Tahoma;color: black;height: 20px">
            <td align="center" style="width: 150px"><b style="color: mediumblue">Patronym</b></td>
            <td align="center" style="width: 150px"><b><%=contact.getPatronym()%></b></td>
            <td align="center" style="width: 150px">
                <input type="text" name="patronym">
            </td>
        </tr>
        <tr align="left" style="font-size: 8pt;font-family: Tahoma;color: black;height: 20px">
            <td align="center" style="width: 150px"><b style="color: mediumblue">E-mail</b></td>
            <td align="center" style="width: 150px"><b><%=contact.getEmail()%></b></td>
            <td align="center" style="width: 150px">
                <input type="text" name="email">
            </td>
        </tr>
        <tr align="left" style="font-size: 8pt;font-family: Tahoma;color: black;height: 20px">
            <td align="center" style="width: 150px"><b style="color: mediumblue">Address</b></td>
            <td align="center" style="width: 150px"><b><%=contact.getAddress()%></b></td>
            <td align="center" style="width: 150px">
                <input type="text" name="address">
            </td>
        </tr>
        <tr align="left" style="font-size: 8pt;font-family: Tahoma;color: black;height: 20px">
            <td align="center" style="width: 150px"><b style="color: mediumblue">Mobile phone number</b></td>
            <td align="center" style="width: 150px"><b><%=contact.getMobilePhoneNumber()%></b></td>
            <td align="center" style="width: 150px">
                <input type="text" name="mobile">
            </td>
        </tr>
        <tr align="left" style="font-size: 8pt;font-family: Tahoma;color: black;height: 20px">
            <td align="center" style="width: 150px"><b style="color: mediumblue">Home phone number</b></td>
            <td align="center" style="width: 150px"><b><%=contact.getHomePhoneNumber()%></b></td>
            <td align="center" style="width: 150px">
                <input type="text" name="home">
            </td>
        </tr>
        <tr align="left" style="font-size: 8pt;font-family: Tahoma;color: black;height: 20px">
            <td align="center" style="width: 150px; border-right: hidden"></td>
            <td align="center" style="width: 150px"></td>
            <td align="center" style="width: 150px">
                <input type="submit" value="Edit">
            </td>
        </tr>
    </form>
</table>
</body>
</html>
