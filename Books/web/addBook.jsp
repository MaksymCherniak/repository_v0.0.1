<%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 25.02.2016
  Time: 11:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>BookService</title>
</head>
<body>
<h1>Add book page</h1>
<p><b><a href="main.jsp">Main page</a> </b></p>
<form name="add" action="add.do">
    <p>
        Enter author: <input type="text" name="author"><br>
        Enter title: <input type="text" name="title"><br>
        Enter genre: <input type="text" name="genre"><br>
        Enter price: <input type="text" name="price"><br>
        Enter publish date: <input type="text" name="publishDate"><br>
        Description:<br>
        <textarea name="description" cols="40" rows="3"></textarea><br>
        <input type="submit" value="Enter">
    </p>
</form>
</body>
</html>
