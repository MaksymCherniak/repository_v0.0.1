<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h1>Get route page</h1>
<p><b><a href="main.jsp">Main page</a></b></p>
<form name="selectRoute" action="selectRoute.do">
    Enter leaving stations <input type="text" name="leavingStation"><br>
    Enter arrival stations <input type="text" name="arrivalStation"><br>
    Enter leaving date ("DD-MM-YYYY") <input type="text" name="leavingDate"><br>
    <input type="submit" value="Enter">
</form>
</body>
</html>