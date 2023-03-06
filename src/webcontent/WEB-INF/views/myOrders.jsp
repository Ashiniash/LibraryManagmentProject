<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.*" %>
<html>
<head>
<jsp:include page="style.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<center>
<h1>My Orders</h1>
<table border="1" width="90%">
<tr>
<th>Book Id</th>
<th>Book Title</th>
<th>Author Name</th>
<th>Publication Date</th>
<th>Publisher</th>
</tr>
<c:forEach items="${bookList}" var="book">
<tr>
     <td>${book.bookId}</td>
     <td>${book.bookTitle}</td>
      <td>${book.authorName}</td>
      <td>${book.publicationDate}</td>
      <td>${book.publisher}</td>
</tr>
</c:forEach>
</table>
</body>
</html>

