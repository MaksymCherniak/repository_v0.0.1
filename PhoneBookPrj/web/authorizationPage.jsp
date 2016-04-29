<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
<form:form method="post" name="authorization" commandName="user" action="authorization.do" onSubmit="return check()">
    <table align="left" border="0" cellspacing="0" cellpadding="0">
        <caption><b>Authorization form</b></caption>
        <tr>
            <td style="width: 170px"><b>Enter your login: </b></td>
            <td><form:input path="login"/></td>
        </tr>
        <tr>
            <td style="width: 170px"><b>Enter your password: </b></td>
            <td><form:password path="password"/></td>
        </tr>
        <tr>
            <td align="left"><input type="submit" value="Enter"/></td>
        </tr>
    </table>
</form:form>
${info}
</body>
</html>
