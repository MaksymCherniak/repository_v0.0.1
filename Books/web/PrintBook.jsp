<%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 25.02.2016
  Time: 20:07
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
    <p><b><a href="printAllBooks.do">All books</a></b></p>
    <h2>Book #${book.index}</h2>
    <ul>
        <li>Id: ${book.index}</li>
        <li>Author: ${book.author}</li>
        <li>Title: ${book.title}</li>
        <li>Genre: ${book.genre}</li>
        <li>Price: ${book.price}</li>
        <li>Publish date: ${book.publishDate}</li>
        <li>Description: ${book.description}</li>
    </ul>
</body>
</html>
