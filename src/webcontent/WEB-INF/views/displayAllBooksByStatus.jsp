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
<table border="1" width="90%">
</tr>
<tr>
<th>Order Id</th>
<th>Book Id</th>
<th>User Id</th>
<th>Order Status</th>
<th>Order Date</th>
<th>Return Date</th>
<th>Approve</th>
<th>Reject</th>
</tr>
<c:forEach items="${orderBookList}" var="orderBook">
<tr>
<td>${orderBook.orderId}
<td>${orderBook.bookId}
<td>${orderBook.userId}
<td>${orderBook.orderStatus}
<td>${orderBook.orderDate}
<td>${orderBook.returnDate}
<td><form action="/LibraryManagement/librarian/editUserForStatusApproval/${orderBook.orderId}">
            <input type="submit" class="btn btn-danger" value="APPROVED"/></form></td>
            <td><form action="/LibraryManagement/librarian/editUserForStatusRejected/${orderBook.orderId}">
                        <input type="submit" class="btn btn-danger" value="REJECT"/></form></td>
</td>
</c:forEach>
</table>
</body>
</html>