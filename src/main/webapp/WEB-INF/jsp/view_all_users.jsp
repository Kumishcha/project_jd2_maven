<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="m" uri="mytags"%>
<%@ page import="java.util.*" %>
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
			<input type="hidden" name="command" value="go-to-admin-page" /> <input
				type="submit" value="Back" /><br />
		</form>
		<form action="controller" method="get">
			<input type="hidden" name="command" value="go-to-index-page" /> <input
				type="hidden" name="message" value="" /> <input type="submit"
				value="Sign out" /><br />
		</form>
</div>
<br>
<div id="main-box-admin">
		<div id="left-box-admin">
		<h2>Hello, ${sessionScope.user.name}</h2>
			<form action="controller" method="get">
				<input type="hidden" name="command" value="view" /> <br>
				<button type="submit">View users list</button>
			</form>
			<form action="controller" method="get">
				<input type="hidden" name="command" value="view-all-stock" /> <br>
				<button type="submit">View products list</button>
			</form>
		</div>
		<div id="right-box-admin">
<c:set var="users" scope="session" value="${users}"/>
<c:set var="totalCount" scope="session" value="${users.size()}"/>
<c:set var="perPage" scope="session"  value="${3}"/>
<c:set var="pageStart" value="${param.start}"/>
<c:if test="${empty pageStart or pageStart < 0}">
       <c:set var="pageStart" value="0"/>
</c:if>
<c:if test="${totalCount <= pageStart}">
       <c:set var="pageStart" value="${pageStart - perPage}"/>
</c:if>
<div id="into-right-box-admin">
<div id="into-left">
    <form action="controller" method="get">
	  <input type="hidden" name="command" value="pagination" /> 
	  <input type="hidden" name="start" value="${pageStart - perPage}"> 
	  <input type="submit" value="back" />
	</form>
	</div>
	<div id="into-center">
	${pageStart + 1} - ${pageStart + perPage}
	</div>
	<div id="into-right">
	<form action="controller" method="get">
	  <input type="hidden" name="command" value="pagination" /> 
	  <input type="hidden" name="start" value="${pageStart + perPage}"> 
	  <input type="submit" value="forward" />
	</form>
</div>
</div>
		<table border="1">
			<caption>Users</caption>
			<tr>
				<th>id</th>
				<th>name</th>
				<th>surname</th>
				<th>email</th>
				<th>role</th>
				<th>balance</th>
				<th></th>
			</tr>
			<c:forEach var="info" items="${users}" begin="${pageStart}" end="${pageStart + perPage - 1}">
				<tr>
					<td><c:out value="${info.userId}" /></td>
					<td><c:out value="${info.name}" /></td>
					<td><c:out value="${info.surname}" /></td>
					<td><c:out value="${info.email}" /></td>
					<td><c:out value="${info.role}" /></td>
					<c:if test="${info.role eq 'admin'}">
						<td><c:out value="-" /></td>
					</c:if>
					<c:if test="${info.role eq 'client'}">
						<td><c:out value="${info.balance}" /></td>
					</c:if>
					<td><c:if test="${info.userId != 1}">
							<form action="controller" method="get">
								<input type="hidden" name="command" value="user-details" /> <input
									type="hidden" name="userId" value="${info.userId}"> <input
									type="submit" value="Details" /><br />
							</form>
						</c:if></td>
				</tr>
			</c:forEach>
		</table>
		</div>
	</div>
	<m:today/>
	</body>
</html>