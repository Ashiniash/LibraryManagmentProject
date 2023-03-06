<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.*" %>
<html>
<head>
<jsp:include page="style.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<center>
<h1>Address</h1>
<table border="1" width="90%">
<tr>
<th>Address Id</th>
<th>House Number</th>
<th>Street</th>
<th>Town</th>
<th>District</th>
<th>Country</th>
<th>Phone Number</th>
<th>PinCode</th>
<th>Edit Address</th>
</tr>
<c:forEach items="${addressDTO}" var='addressDTO'>
<tr>
<td>${addressDTO.addressId}
<td>${addressDTO.houseNumber}</td>
<td>${addressDTO.street}</td>
<td>${addressDTO.town}</td>
<td>${addressDTO.district}</td>
<td>${addressDTO.country}</td>
<td>${addressDTO.phoneNumber}</td>
<td>${addressDTO.pinCode}</td>
<td><a href="/LibraryManagement/editAddress?userId=${userId}&addressId=${addressDTO.addressId}">Edit Address</a></td>
</tr>
</c:forEach>
</table>
</body>
</html>

