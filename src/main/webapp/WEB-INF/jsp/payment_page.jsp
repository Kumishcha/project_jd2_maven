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
		<input type="hidden" name="command" value="back-to-order" />
		<input type="hidden" name="numberOrder" value="${order.numberOrder}"/>
		<input type="submit" value="Back" /><br />
	</form>
		<form action="controller" method="get">
			<input type="hidden" name="command" value="go-to-index-page" /> 
			<input type="hidden" name="message" value="" />
			<input type="submit" value="Sign out" /><br />
		</form>
</div>
<div id="main-box">
<div id="left-box">
	<h3>Balance ${requestScope.balance}</h3>
	<table border="1">
		<tr>
			<th>numberOrder</th>
			<th>price</th>
			<th>status</th>
		</tr>
		<tr>
		<td><c:out value="${order.numberOrder}" /></td>
		<td><c:out value="${order.priceResult}" /></td>
		<td><c:if test="${order.status == false}">
				<c:out value="not paid" />
			</c:if> <c:if test="${order.status == true}">
				<c:out value="paid" />
			</c:if></td>
		</tr>
	</table>
	<h4><c:out value="${requestScope.notEnoughMoney}"/></h4>
	<c:if test="${order.status == false}">
    <form action="controller" method="get">
		<input type="hidden" name="command" value="back-to-order" />
		<input type="hidden" name="numberOrder" value="${order.numberOrder}"/>
		<input type="submit" value="Back to order" /><br />
	</form>
	</c:if>
	<br>
	<c:if test="${order.status == false}">
    <form action="controller" method="get">
		<input type="hidden" name="command" value="pay-for-order" />
		<input type="hidden" name="numberOrder" value="${order.numberOrder}"/>
		<input type="submit" value="Pay for order" /><br />
	</form>

	</c:if>
	<c:if test="${order.status == true}">
	Order is done, want to make a new order?
	 <form action="controller" method="get">
		<input type="hidden" name="command" value="make-new-order" />
		<input type="submit" value="Yes" /><br />
	</form>
	<br>
	 <form action="controller" method="get">
		<input type="hidden" name="command" value="go-to-index-page" />
		<input type="submit" value="No" /><br />
	</form>
	</c:if>
	</div>
	</div>
</body>
</html>