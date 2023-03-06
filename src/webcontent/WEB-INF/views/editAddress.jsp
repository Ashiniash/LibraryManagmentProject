<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="style.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Address edit</title>
</head>
<body>
<div align="center">
<h2>User Detail</h2>
<hr>
<form:form action="/LibraryManagement/addressUpdate" method="post" modelAttribute='addressDTO'>
<table border="0" cellpadding="5">
<tr>
<td>Address Id: </td>
<td>${addressDTO.addressId}
<form:hidden path="addressId"/>
</td>
</tr>
<tr>
<td>User Id: </td>
<td>
<form:input path="userId" /></td>
</tr>
<tr>
<tr>
<td>House Number: </td>
<td>
<form:input path="houseNumber" /></td>
</tr>
<tr>
<td>Street: </td>
<td><form:input path="street" /></td>
</tr>
<tr>
<td>Town: </td>
<td><form:input path="town" /></td>
</tr>
<tr>
<tr>
<td>District: </td>
<td><form:input path="district" /></td>
</tr>
<tr>
<td>Country: </td>
<td><form:input path="country" /></td>
</tr>
<tr>
<td>PhoneNumber: </td>
<td><form:input path="phoneNumber" /></td>
</tr>
<tr>
<td>PinCode: </td>
<td><form:input path="pinCode" /></td>
</tr>
<tr>
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