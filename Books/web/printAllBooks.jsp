<%@ page import="DAO.XmlBookDAO" %>
<%@ page import="Entity.Book" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 25.02.2016
  Time: 13:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>BookService</title>
</head>
<body>
    <h1>Print all books page</h1>
    <p><b><a href="main.jsp">Main page</a> </b></p>
    <% List<Book> listOfBooks = new XmlBookDAO().getAllBooks();%>
    <% for (int i = 0; i < listOfBooks.size(); i++) {%>
    <form name="<%=i%>">
        <p><a href="printBook.do?id=<%=listOfBooks.get(i).getIndex()%>">Book #<%= listOfBooks.get(i).getIndex()%></a></p>
    </form>
    <%}%>
</body>
</html>
