<%@ page import="PhoneBook.Entity.User" %>
<%@ page import="org.springframework.web.servlet.ModelAndView" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main page</title>
</head>
<% User user = (User) request.getAttribute("user"); %>
<body bgcolor="#dcdcdc">
${info}<br>
<b><a href="printAllContacts.do?user_id=<%=user.getId()%>">Print all contacts</a></b>
<TABLE align="right">
    <tr>
        <td><a href="logOut.do">logout</a></td>
    </tr>
</TABLE>
<br>
<b><a href="addContact.do?user_id=<%=user.getId()%>">Add contact</a></b>
</body>
</html>
