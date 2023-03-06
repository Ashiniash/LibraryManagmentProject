<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import ="java.util.*" %>
<html>
<head>
    <jsp:include page="style.jsp" />
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Books</title>
</head>
<body>
   <center>
      <h1>Search Book</h1>
  <div align="center">
     <form:form method="get" modelAttribute="searchedBookId">
        <table border="0" cellpadding="5">
                  <tr>
                   <td>Book Id: </td>
                   <td>${book.bookId}</td>
                  <tr> <td>Book Title: </td>
                   <td>${book.bookTitle}</td></tr>
                    <tr> <td>Author Name: </td>
                    <td>${book.authorName}</td>
                     <tr> <td>Genre : </td>
                   <td>${book.genre}</td>
                   <tr> <td>Publication Date : </td>
                  <td>${book.publicationDate}</td>
                  <tr> <td>Publisher : </td>
                  <td>${book.publisher}</td>
                          </table>
               </form:form>
    </center>
</body>
</html>