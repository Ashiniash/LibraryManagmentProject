<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
<center>
<jsp:include page="style.jsp" />
</head>
<body>
<form:form method="post" modelAttribute="book" action="/LibraryManagement/librarian/saveBook">
<table>
<tr>
   <th colspan="2">Add Books</th>
</tr>
<tr>
   <td><form:label path="bookTitle">Book  Title:</form:label ></td>
   <td><form:input path="bookTitle" size="30" maxlength="30" ></form:input></td>
</tr>
<tr>
   <td><form:label path="authorName">Author Name:</form:label></td>
   <td><form:input path="authorName" size="30" maxlength="30" ></form:input></td>
</tr>
<tr>
   <td><form:label path="genre">Genre:</form:label></td>
   <td><form:input path="genre" size="30" maxlength="30" ></form:input></td>
</tr>
<tr>
   <td><form:label path="publicationDate">Publication Date:</form:label></td>
   <td><form:input path="publicationDate" type="date" size="30" maxlength="30" ></form:input></td>
</tr>
<tr>
   <td><form:label path="publisher">Publisher:</form:label></td>
   <td><form:input path="publisher" size="30" maxlength="30" ></form:input></td>
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

