<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script>
    function check(){
        if (document.register.login.value == "") {
            alert("Enter login please");
            return false;
        }
        if (document.register.password.value == "") {
            alert("Enter password please");
            return false;
        }
        if (document.register.fullName.value == "") {
            alert("Enter full name please");
            return false;
        }
    }
</script>
<head>
    <title>Register</title>
</head>
<body bgcolor="#dcdcdc">
<form name="register" method="post" action="register.do" onSubmit="return check()">
    <table align="left" border="0" cellspacing="0" cellpadding="0">
        <caption><b>Registration form</b></caption>
        <tr>
            <td style="width: 170px"><b>Enter your login: </b></td>
            <td><input type="text" name="login"></td>
        </tr>
        <tr>
            <td style="width: 170px"><b>Enter your password: </b></td>
            <td><input type="password" name="password"></td>
        </tr>
        <tr>
            <td style="width: 170px"><b>Enter your full name: </b></td>
            <td><input type="text" name="fullName"></td>
        </tr>
        <tr>
            <td align="left"><input type="submit" value="Enter"></td>
        </tr>
    </table>
</form>
</body>
</html>
