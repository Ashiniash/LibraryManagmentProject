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

      <h1>Books</h1>
      <h2>Search Book</h2>Enter ID to Search:
      <input type="number" id="bookId" >
      <button onclick='window.location ="/LibraryManagement/searchBook/${bookId}"+document.getElementById("bookId").value'
      class="blue-button">Submit  </button>

        <table>
            <c:forEach var="genre" items="${uniqueGenres}">
                <tr>
                    <td><b>${genre}</td>
                </tr>
                <tr>
                <th>Book Id</th>
                <th>Book Title</th>
                <th>Author Name</th>
                 <th>Publication  Date</th>
                  <th>Publisher</th>
                  <th>Order</th>
                </tr>
                <c:forEach var="book" items="${bookList}">
                    <c:if test="${book.genre == genre}">
                        <tr>
                        <td>${book.bookId}</td>
                            <td>${book.bookTitle}</td>
                            <td>${book.authorName}</td>
                            <td>${book.publicationDate}</td>
                           <td>${book.publisher}</td>
                           <td> <a href="/LibraryManagement/addToCart?bookId=${book.bookId}&userId=${userId}">Add to Cart</a></td>
                        </tr>
                    </c:if>
                </c:forEach>
            </c:forEach>
        </table>
    </center>
</body>
</html>
