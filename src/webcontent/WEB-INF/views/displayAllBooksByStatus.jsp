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
<th>Cart Id</th>
<th>User Id</th>
<th>Order Status</th>
<th>View Books</th>
<th>Approve</th>
<th>Reject</th>
</tr>
<c:forEach items="${cartList}" var="cart">
<tr>
<td>${cart.cartId}
<td>${cart.userId}
<td>${cart.orderStatus}
 <td><form action="/LibraryManagement/librarian/viewBooks/${cart.cartId}/${cart.userId}" method="get"/>
                            <button type="submit">View Books</button></form></td>
<td><form action="/LibraryManagement/librarian/editUserForStatusApproval/${cart.cartId}">
            <input type="submit" class="btn btn-danger" value="APPROVED"/></form></td>
            <td><form action="/LibraryManagement/librarian/editUserForStatusRejected/${cart.cartId}">
                        <input type="submit" class="btn btn-danger" value="REJECT"/></form></td>

</td>
</c:forEach>
</table>

</body>
</html>