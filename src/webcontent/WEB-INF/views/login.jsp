<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
<center><jsp:include page="style.jsp" />
<head>
<title> Login Page</title>
</head>
<body onload='document.loginForm.j_username.focus();'>
<h1> Login Page</h1><hr>
<%
String errorString = (String)request.getAttribute("error");
if(errorString != null && errorString.trim().equals("true")){
out.println("<span class=\"dark\">Incorrect login name or password. Please try again");
}
%>
<form name='loginForm'  action="<c:url value='login'/>""
method='POST'>
<table>
<tr>
<td>User:</td>
<td><input type='text' name='username' value=''>
</td>
</tr>
<tr>
<td>Password:</td>
<td><input type='password' name='password' />
</td>
</tr>
<tr>
<td><input name="submit" type="submit"
value="submit" />
</td>
<td><input name="reset" type="reset" />
</td>
</tr>
</table>
</form>
</body>
</html>