<%@ page import="PhoneBook.Entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script>
    function check(){
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
        if (document.contact.mobilePhone.value == "") {
            alert("Enter mobile phone number please");
            return false;
        }
    }
</script>
<% User user = (User) request.getAttribute("user");%>
<head>
    <title>Add contact</title>
</head>
<body bgcolor="#dcdcdc">
<form name="contact" method="post" action="addContact.do" onSubmit="return check()">
    <TABLE align="right">
        <tr><td><a href="logOut.do">logout</a></td></tr>
    </TABLE><br>
    <table align="left" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td style="width: 170px"><b>Enter contact surname: </b></td>
            <td><input type="text" name="surname"></td>
        </tr>
        <tr>
            <td style="width: 170px"><b>Enter contact name: </b></td>
            <td><input type="text" name="name"></td>
        </tr>
        <tr>
            <td style="width: 170px"><b>Enter contact patronym: </b></td>
            <td><input type="text" name="patronym"></td>
        </tr>
        <tr>
            <td style="width: 170px"><b>Enter contact mobile phone number: </b></td>
            <td><input type="text" name="mobilePhone"></td>
        </tr>
        <tr>
            <td style="width: 170px"><b>Enter contact home phone number: </b></td>
            <td><input type="text" name="homePhone"></td>
        </tr>
        <tr>
            <td style="width: 170px"><b>Enter contact address: </b></td>
            <td><input type="text" name="address"></td>
        </tr>
        <tr>
            <td style="width: 170px"><b>Enter contact e-mail: </b></td>
            <td><input type="text" name="email"></td>
        </tr>
        <tr>
            <td><input type="submit" value="Enter"></td>
        </tr>
    </table>
</form>
</body>
</html>
