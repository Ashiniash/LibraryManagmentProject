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
<h2>User Detail</h2>
<hr>
<form:form action="/LibraryManagement/librarian/userApprovalUpdateByAdmin?userId=${userId}" method="post" modelAttribute="user">
<table border="0" cellpadding="5">
<tr>
<td>User Id: </td>
<td>${user.userId}
<form:hidden path="userId"/>
</td>
</tr>
<tr>
  <td>Username: </td>
  <td>${user.username}</td>
</tr>
<tr>
  <td>Email: </td>
  <td>${user.email}</td>
</tr>
<tr>
<td>Approval: </td>
<td><form:select path="active">
<form:option value="true" label="True" />
 <form:option value="false" label="False" /></td>
</tr>
  </form:select>
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
