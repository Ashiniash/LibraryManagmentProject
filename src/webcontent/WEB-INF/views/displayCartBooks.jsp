<%@page import="org.example.service.implementation.*,org.example.model.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import ="java.util.*" %>
<html>
<head>
<jsp:include page="style.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<center>
<h1>Cart Books</h1>
<table border="1" width="90%">
<tr>
<th>Book Title</th>
<th>Author Name</th>
<th>Publication date</th>
<th>Publisher</th>
</tr>
<c:forEach items="${bookList}" var="book">
<tr>
<td>${book.bookTitle}
<td>${book.authorName}
<td>${book.publicationDate}
<td>${book.publisher}
</td>
</c:forEach>
</table>
<form action="/LibraryManagement/saveOrderBook/${cartId}" method="post">
    <button type="submit">Order</button>
</form>
</body>
</html>