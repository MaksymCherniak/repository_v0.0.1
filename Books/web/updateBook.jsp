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
    <h1>Update book page</h1>
    <p><b><a href="main.jsp">Main page</a> </b></p>
    <form name="updateBook" action="update.do">
        Enter book id <input type="text" name="bookId"><br>
        Change author <input type="text" name="author"><br>
        Change title <input type="text" name="title"><br>
        Change genre <input type="text" name="genre"><br>
        Change price <input type="text" name="price"><br>
        Change publish date <input type="text" name="publishDate"><br>
        Change description <input type="text" name="description"><br>
        <input type="submit" value="Enter">
    </form>
</body>
</html>
