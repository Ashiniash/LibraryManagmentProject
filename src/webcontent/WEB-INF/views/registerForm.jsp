<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
<center>
<jsp:include page="style.jsp" />
</head>
<h1>Register Form</h1><hr>
<body>
<form:form method="post" modelAttribute="user" action="/LibraryManagement/saveUser">
<table>
<tr>
   <th colspan="2">Add User</th>
</tr>
<tr>
       <td><form:label path="username" >Username:</form:label></td>
       <td><form:input path="username" size="30" maxlength="30"></form:input></td>
</tr>
<tr>
       <td><form:label path="password">Password:</form:label></td>
       <td><form:input path="password" size="30" maxlength="30"></form:input></td>
</tr>
<tr>
       <td><form:label path="confirmPassword">Confirm Password:</form:label></td>
       <td><form:input path="confirmPassword" size="30" maxlength="30"></form:input></td>
</tr>
<tr>
      <td><form:label path="email">Email:</form:label></td>
      <td><form:input path="email" size="30" maxlength="30"></form:input></td>
</tr>

<tr>
       <td colspan="2"><input type="submit"
         class="blue-button" /></td>
</tr>
</table>
</form:form>
</br>
</center>
</body>
</html>

