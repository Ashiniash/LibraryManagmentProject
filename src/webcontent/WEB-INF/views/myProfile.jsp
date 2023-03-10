<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="style.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Profile edit</title>
</head>
<body>
<div align="center">
<h2>My Profile</h2>
<hr>
<form:form >
<table border="0" cellpadding="5">
 <tr>
<td>User Id: </td>
 <td>${user.userId}</td>
 <tr> <td>User Name: </td>
 <td>${user.username}</td></tr>
 <tr> <td>Password: </td>
 <td>${user.password}</td>
 <tr> <td>Confirm Password : </td>
 <td>${user.confirmPassword}</td>
 <tr> <td>Email : </td>
 <td>${user.email}</td>
 </tr>
</tr>
<br>
</tr>
</table>
</form:form>
<form method="get"  action="/LibraryManagement/editProfile/${userId}"></br>
<input type="submit" class="blue-button"  value="Edit Profile"/></form><br>

<form method="get"  action="/LibraryManagement/addAddress/${userId}"></br>
<input type="submit" class="blue-button"  value="Add Address"/></form><br>

<form method="get"  action="/LibraryManagement/displayAddress/${userId}"></br>
<input type="submit" class="blue-button"  value="View Address"/></form><br>
</div>
</body>
</html>