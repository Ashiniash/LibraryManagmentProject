<%@ page language="java" contentType="text/html; charset=ISO-8859-1"   pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOC TYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<center>
<jsp:include page="style.jsp" />
<h1>Welcome ${user.username}</h1>
<hr>
<form method="get"  action="/LibraryManagement/myProfile/${user.userId}"></br>
<input type="submit" class="blue-button"  value="My Profile"/></form><br>
<form method="get"  action="/LibraryManagement/book/${user.userId}"></br>
<input type="submit"class="blue-button"  value="Book List"/></form><br>

  <form method="get"  action="/LibraryManagement/displayCartBooks/${user.userId}"></br>
  <input type="submit"class="blue-button"  value="Cart List"/></form><br>

<form method="get"  action="/LibraryManagement/pendingBooks/${user.userId}"></br>
<input type="submit" class="blue-button" value="Pending Books"/></form><br>

<form method="get"  action="/LibraryManagement/loanedBooks/${user.userId}"></br>
<input type="submit" class="blue-button" value="Loaned Books"/></form><br>



</body>
<c:url value="/j_spring_security_logout" var="logoutUrl" />
<a href="${logoutUrl}">Log Out</a><br>
</html>