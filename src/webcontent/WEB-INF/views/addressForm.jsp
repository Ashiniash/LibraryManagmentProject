<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
<center>
<jsp:include page="style.jsp" />
</head>
<h1>Address Form</h1><hr>
<body>
<form:form method="post" modelAttribute='addressDTO' action="/LibraryManagement/saveAddress/${userId}">
<table>
<tr>
   <th cols pan="2">Add Address</th>
</tr>
<tr>
       <td><form:label path="houseNumber">House Number:</form:label></td>
       <td><form:input path="houseNumber" size="30" maxlength="30"></form:input></td>
</tr>
<tr>
       <td><form:label path="street">Street:</form:label></td>
       <td><form:input path="street" size="30" maxlength="30"></form:input></td>
</tr>
<tr>
     <td><form:label path="town">Town:</form:label></td>
     <td><form:input path="town" size="30" maxlength="30"></form:input></td>
</tr>
<tr>
      <td><form:label path="district">District:</form:label></td>
      <td><form:input path="district" size="30" maxlength="30"></form:input></td>
</tr>
<tr>
       <td><form:label path="country">Country:</form:label></td>
       <td><form:input path="country" size="30" maxlength="30"></form:input></td>
</tr>
<tr>
       <td><form:label path="phoneNumber">Phone Number:</form:label></td>
       <td><form:input path="phoneNumber" size="30" maxlength="30"></form:input></td>
</tr>
<tr>
       <td><form:label path="pinCode">PinCode:</form:label></td>
       <td><form:input path="pinCode" size="30" maxlength="30"></form:input></td>
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

