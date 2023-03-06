<%@ page language="java" contentType="text/html; charset=ISO-8859-1"   pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOC TYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<center>
<jsp:include page="style.jsp" />
<h1>Admin Page</h1>
<hr>

<form method="get"  action="/LibraryManagement/librarian/addBook"></br>
<input type="submit" class="blue-button"  value="Add Books"/></form><br>

<form method="get"  action="/LibraryManagement/librarian/displayAllBooks"></br>
<input type="submit"class="blue-button"  value="Book List"/></form><br>

<form method="get"  action="/LibraryManagement/librarian/displayAllBooksByStatus"></br>
<input type="submit"class="blue-button"  value="Book List by Status"/></form><br>


<form method="get"  action="/LibraryManagement/librarian/userApproval"></br>
<input type="submit" class="blue-button"  value="Users Approval"/></form><br>

</body>
<c:url value="/j_spring_security_logout" var="logoutUrl" />
<a href="${logoutUrl}">Log Out</a><br>
</html>