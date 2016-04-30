<%@ page import="java.util.List" %>
<%@ page import="PhoneBook.Entity.Contact" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript" src="/path/to/jquery-latest.js"></script>
    <script type="text/javascript" src="/path/to/jquery.tablesorter.js"></script>
    <script type="text/javascript" src="sortable.js"></script>
    <title>Contacts</title>
</head>
<script>
    function deleteContact(url) {
        if (confirm(' Delete contact? ')) {
            document.location=url;
            return true;
        }
        return false;
    }
</script>
<body bgcolor="#dcdcdc">
<% List<Contact> listOfContacts = (List<Contact>) request.getAttribute("listOfContacts");
%>
<TABLE align="right">
    <tr><td><a href="logOut.do">logout</a></td></tr>
</TABLE><br>
<table id="table" align="center" bgcolor="#8fbc8f" border="1" cellspacing="0" cellpadding="0" style="border-color: gray" class="sortable">
    <caption><b>Contacts</b></caption>
    <thead>
    <tr align="left" style="font-size: 8pt;font-family: Tahoma;color: black;height: 20px">
        <th align="center" style="width: 150px"><b>Surname</b></th>
        <th align="center" style="width: 150px"><b>Name</b></th>
        <th align="center" style="width: 150px"><b>Patronym</b></th>
        <th class="unsortable" align="center" style="width: 150px"><b>E-mail</b></th>
        <th class="unsortable" align="center" style="width: 150px"><b>Address</b></th>
        <th align="center" style="width: 150px"><b>Mobile phone number</b></th>
        <th class="unsortable" align="center" style="width: 150px"><b>Home phone number</b></th>
        <th class="unsortable" align="center" style="width: 150px"><b>Action</b></th>
    </tr>
    </thead>
    <tbody>
<% for (int i = 0; i < listOfContacts.size(); i++) { %>
    <tr align="left" style="font-size: 8pt;font-family: Tahoma;color: black">
        <td align="center" style="width: 150px"><b><%=listOfContacts.get(i).getSurname()%></b></td>
        <td align="center" style="width: 150px"><b><%=listOfContacts.get(i).getName()%></b></td>
        <td align="center" style="width: 150px"><b><%=listOfContacts.get(i).getPatronym()%></b></td>
        <td align="center" style="width: 150px"><b><%=listOfContacts.get(i).getEmail()%></b></td>
        <td align="center" style="width: 150px"><b><%=listOfContacts.get(i).getAddress()%></b></td>
        <td align="center" style="width: 150px"><b><%=listOfContacts.get(i).getMobilePhoneNumber()%></b></td>
        <td align="center" style="width: 150px"><b><%=listOfContacts.get(i).getHomePhoneNumber()%></b></td>
        <td align="center" style="width: 150px">
            <input type="button" value="Edit contact"
                   onclick="location.href='http://localhost:8080/editContact.do?contact_id=<%=listOfContacts.get(i).getId()%>'"><br>
            <input type="button" value="Delete contact"
                   onclick="deleteContact('http://localhost:8080/deleteContact.do?contact_id=<%=listOfContacts.get(i).getId()%>')"></td>
    </tr>
<% } %>
    </tbody>
</table>
<TABLE align="center">
    <tr><td><input type="button" value="Add new contact"
                   onclick="location.href='http://localhost:8080/addContact.do?user_id=<%=listOfContacts.get(0).getUser().getId()%>'"></td></tr>
</TABLE><br>
</body>
</html>
