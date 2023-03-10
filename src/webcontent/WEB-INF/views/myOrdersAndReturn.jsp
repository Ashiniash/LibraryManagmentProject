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
<tr>
<th>Cart Id</th>
<th>User Id</th>
<th>View Books</th>
<th>Return Book</th>
</tr>
<c:forEach items="${cartList}" var="cart">
<tr>
<td>${cart.cartId}</td>
<td>${cart.userId}</td>
<td><form action="/LibraryManagement/viewBooks/${cart.cartId}/${userId}" method="get"/>
<button type="submit">View Books</button></form></td>
<td><form action="/LibraryManagement/returnBook/${cart.cartId}/${userId}" method="post"/>
<button type="submit">Return Book</button></td></form></tr>
</c:forEach>
</table>
</body>
</html>