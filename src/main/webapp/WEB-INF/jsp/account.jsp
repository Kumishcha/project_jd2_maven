<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
       <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<div id="header">
	<form action="controller" method="get">
		<input type="hidden" name="command" value="go-to-client-page" />
		<input type="hidden" name="message"/>
		<input type="submit" value="Back" /><br />
	</form>
		<form action="controller" method="get">
			<input type="hidden" name="command" value="go-to-index-page" /> 
			<input type="hidden" name="message" value="" />
			<input type="submit" value="Sign out" /><br />
		</form>
</div>
<h1>  ${sessionScope.user.name}</h1>
<h3><c:out value="${requestScope.incorrectCountFormat}"/></h3>
<form action="controller" method="post">
		<input type="hidden" name="command" value="add-amount" />
		<input type="hidden" name="numberOrder" value="${sessionScope.numberOrder}" />
		<input type="hidden" name="userId" value="${sessionScope.user.userId}">
		<table border="1">
   <tr>
    <th> balance </th>
    <th> amount of money added </th>
   </tr>
   <tr>
   <td>${requestScope.balance}</td>
   <td><input type="text" name="count" value="*max 10000" required></td>
   </tr>
  </table>
  <br>
		<input type="submit" value="Add" /><br />
	</form>
</body>
</html>