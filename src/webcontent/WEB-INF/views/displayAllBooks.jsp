<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import ="java.util.*" %>
<html>
<head>
<jsp:include page="style.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<center>
<h1>Books</h1>
   Enter ID to Search:
      <input type="number" id="bookId" >
      <button onclick='window.location ="/LibraryManagement/searchBook/${bookId}"+document.getElementById("bookId").value'
      class="blue-button">Submit  </button>
<table border="1" width="90%">
<tr>
<th>Book Id</th>
<th> Book Title</th>
<th>Author Name</th>
<th>Publication Date</th>
<th>Publisher</th>
<th>Delete Book</th>
</tr>
<c:forEach items="${bookList}" var="book">
<tr>
<td>${book.bookId}
<td>${book.bookTitle}
<td>${book.authorName}
<td>${book.publicationDate}
<td>${book.publisher}
</td>
<td><a href="http://localhost:8080/LibraryManagement/librarian/deleteBook/${book.bookId}">Delete Book</a></td>
</c:forEach>
</table>
</body>
</html>