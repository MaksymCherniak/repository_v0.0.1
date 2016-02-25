<%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 25.02.2016
  Time: 12:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>BookService</title>
</head>
<body>
    <h1>Delete book page</h1>
    <p><b><a href="main.jsp">Main page</a> </b></p>
    <form name="deleteBook" action="delete.do">
        Enter book id <input type="text" name="id"><br>
        <input type="submit" value="Enter">
    </form>
</body>
</html>
