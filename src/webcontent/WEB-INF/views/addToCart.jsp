<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
<center>
<jsp:include page="style.jsp" />
</head>
<h1>Place Order</h1><hr>

${bookId}
<body>
<form:form method="post" modelAttribute="cartDetail" action="/LibraryManagement/saveCart/${bookId}/${userId}">
<table>
<tr>
   <th cols pan="2">Place Order</th>
</tr>
<tr>
       <td><form:label path="orderDate">Order Date:</form:label></td>
       <td><form:input path="orderDate" size="30" type="date" maxlength="30"></form:input></td>
</tr>
<tr>
       <td><form:label path="returnDate">Return Date:</form:label></td>
       <td><form:input path="returnDate" size="30" type="date" maxlength="30"></form:input></td>
</tr>
 <tr>
       <td cols pan="2"><input type="submit"
         class="blue-button" /></td>
</tr>
</table>
</form:form>
</br>
</center>
</body>
</html>