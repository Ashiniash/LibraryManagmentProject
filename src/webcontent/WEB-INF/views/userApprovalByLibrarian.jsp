<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import ="java.util.*" %>
<html>
<head>
<jsp:include page="style.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<center>
<h1>User Approval</h1>
<table border="1" width="90%">
<tr>
<th> User Id</th>
<th> User Name</th>
<th>Email</th>
<th>Active</th>
<th>Edit</th>
</tr>
<c:forEach items="${userList}" var="user">
<tr>
<td>${user.userId}
<td>${user.username}
<td>${user.email}
<td>${user.active}
</td>
<td><a href="/LibraryManagement/librarian/editUserApprovalByLibrarian/${user.userId}">Edit User</a></td>
</c:forEach>
</table>
</body>
</html>