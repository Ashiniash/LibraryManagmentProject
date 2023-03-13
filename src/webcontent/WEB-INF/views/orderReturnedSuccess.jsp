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
<c:if test="${!empty delayedBookList}">
<table border="1" width="90%">
<tr>
<th>Book Id</th>
<th>Order Date</th>
<th>Return date</th>
<th>Date Exceeded<th>
</tr>
<c:forEach items="${delayedBookList}" var="book">
<tr>
<td>${book.bookId}
<td>${book.orderDate}
<td>${book.returnDate}
<td>please Return before due date</td>
</td>
</c:forEach>
</table>
</c:if>

<h1>Book Returned Success</h1>
</form>
</body>