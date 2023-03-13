<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="style.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Approval By Librarian</title>
</head>
<body>
<div align="center">
<h2>Confirm To Reject</h2>
<hr>
<form:form action="/LibraryManagement/librarian/userStatusRejectByLibrarian?cart=${cartId}" method="post" modelAttribute="cart">
<table border="0" cellpadding="5">
<tr>
<td>Cart Id: </td>
<td>${cart.cartId}
<form:hidden path="cartId"/>
</td>
<tr>
   <td colspan="2"><input type="submit"
    class="blue-button" /></td>
</tr>
</tr>
<br>
</tr>
</table>
</form:form>
</div>
</body>
</html>
