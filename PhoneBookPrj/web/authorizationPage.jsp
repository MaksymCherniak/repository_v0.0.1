<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script>
    function check(){
        if (document.authorization.login.value == "") {
            alert("Enter surname please");
            return false;
        }
        if (document.authorization.password.value == "") {
            alert("Enter name please");
            return false;
        }
    }
</script>
<head>
    <title>Authorization</title>
</head>
<body bgcolor="#dcdcdc">
<form name="authorization" action="authorization.do" onSubmit="return check()">
    <table align="left" border="0" cellspacing="0" cellpadding="0">
        <caption><b>Authorization form</b></caption>
        <tr>
            <td style="width: 170px"><b>Enter your login: </b></td>
            <td><input type="text" name="login"></td>
        </tr>
        <tr>
            <td style="width: 170px"><b>Enter your password: </b></td>
            <td><input type="password" name="password"></td>
        </tr>
        <tr>
            <td align="left"><input type="submit" value="Enter"></td>
        </tr>
    </table>
</form>
</body>
</html>
