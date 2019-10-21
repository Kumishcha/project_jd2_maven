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
<c:if test = "${sessionScope.user.role ne 'client'}">
<div id="header">
<form action="controller" method="get">
			<input type="hidden" name="command" value="go-to-view-all-users-page" /> <input
				type="submit" value="Back" /><br />
		</form>
		<br>
		<form action="controller" method="get">
			<input type="hidden" name="command" value="go-to-index-page" /> <input
				type="hidden" name="message" value="" /> <input type="submit"
				value="Sign out" /><br />
		</form>
</div>
<br>
<table border="1">
		<tr>
			<th>id</th>
			<th>name</th>
			<th>surname</th>
			<th>email</th>
			<th>role</th>
			<th>balance</th>
			<th>status</th>
			<th></th>
			<th></th>
		</tr>
		<tr>
				<td><c:out value="${info.userId}" /></td>
				<td><c:out value="${info.name}" /></td>
				<td><c:out value="${info.surname}" /></td>
				<td><c:out value="${info.email}" /></td>
				<td><c:out value="${info.role}" /></td>
				<td><c:out value="${info.balance}" /></td>
				<td><c:if test="${info.blocked == true}">
							<c:out value="block" />
						</c:if> <c:if test="${info.blocked == false}">
							<c:out value="active" />
						</c:if></td>
						<td><form action="controller" method="get">
						<input type="hidden" name="command" value="block" /> <input
							type="hidden" name="userId" value="${info.userId}">
						<button type="submit">Block</button>
					</form></td>
					<td>
					<form action="controller" method="get">
						<input type="hidden" name="command" value="unblock" /> <input
							type="hidden" name="userId" value="${info.userId}">
						<button type="submit">Unblock</button>
					</form></td>
                </tr>
                </table>
		<br>
		<form action="controller" method="get">
						<input type="hidden" name="command" value="change-role" /> 
						<input type="hidden" name="userId" value="${info.userId}"> 
						<button type="submit">Change role</button>
					</form>
			<br>
		<form action="controller" method="get">
			<input type="hidden" name="command" value="view-all-user-orders" />
			<input type="hidden" name="userId" value="${info.userId}">
			<input type="submit" value="View all orders" /><br />
		</form>
		<br>
		<div id="main-box">
		<div id="left-box">
		<c:if test="${empty requestScope.orders}">
		<h4><c:out value="${requestScope.messageOrders}" /></h4>
		 </c:if> 
		 	
		<c:if test="${not empty requestScope.orders}">
		<table border="1">
		<tr>
			<th>numberOrder</th>
			<th>price</th>
			<th>status</th>
			<th></th>
		</tr>
		<c:forEach var="order" items="${requestScope.orders}">
		<tr>
		<td><c:out value="${order.numberOrder}" /></td>
		<td><c:out value="${order.priceResult}" /></td>
		<td><c:if test="${order.status == true}">
				<c:out value="paid" />
			</c:if>
		</td>
		<td><form action="controller" method="get">
			        <input type="hidden" name="command" value="order-details" /> 
			        <input type="hidden" name="userId" value="${info.userId}"/> 
			       <input type="hidden" name="numberOrder" value="${order.numberOrder}"/>
			        <input type="submit" value="Details" /><br />
		          </form></td>
		</tr>
		</c:forEach>
	</table>
	</c:if>
	</div>
		<div id="right-box">
	<c:if test="${not empty requestScope.detailsOfOrder}">
	<table border="1">
					<tr>
					    <th>numberOrder</th>
						<th>id</th>
						<th>coffee</th>
						<th>price</th>
						<th>count</th>
					</tr>
					<c:forEach var="drinkInOrder" items="${requestScope.detailsOfOrder}">
						<tr>
						    <td><c:out value="${drinkInOrder.numberOrder}" /></td>
							<td><c:out value="${drinkInOrder.drinkId}" /></td>
							<td><c:out value="${drinkInOrder.typeOfCoffee}" /></td>
							<td><c:out value="${drinkInOrder.price}" /></td>
							<td><c:out value="${drinkInOrder.count}" /></td>
						</tr>
					</c:forEach>
				</table>
				</c:if>
				</div>
				</div>
		</c:if>
<c:if test = "${sessionScope.user.role eq 'client'}">
<div id="header">
 <form action="controller" method="get">
		<input type="hidden" name="command" value="back-to-order" />
		<input type="submit" value="Back" /><br />
	</form>
		<br>
		<form action="controller" method="get">
			<input type="hidden" name="command" value="go-to-index-page" /> <input
				type="hidden" name="message" value="" /> <input type="submit"
				value="Sign out" /><br />
		</form>
</div>
	<div id="main-box">
		<div id="left-box">
		<c:if test="${empty requestScope.orders}">
		<h4><c:out value="${requestScope.messageOrders}" /></h4>
		 </c:if> 
	
		<c:if test="${not empty requestScope.orders}">
		<table border="1">
		<tr>
			<th>numberOrder</th>
			<th>price</th>
			<th>status</th>
			<th></th>
		</tr>
		<c:forEach var="order" items="${requestScope.orders}">
		<c:if test="${order.status !=false}">
		<tr>
		<td><c:out value="${order.numberOrder}" /></td>
		<td><c:out value="${order.priceResult}" /></td>
		<td><c:if test="${order.status == true}">
				<c:out value="paid" />
			</c:if>
		</td>
		<td><form action="controller" method="get">
			        <input type="hidden" name="command" value="order-details" /> 
			        <input type="hidden" name="userId" value="${info.userId}"/> 
			       <input type="hidden" name="numberOrder" value="${order.numberOrder}"/>
			        <input type="submit" value="Details" /><br />
		          </form></td>
		</tr>
		</c:if>
		</c:forEach>
	</table>
	</c:if>
	</div>
		<div id="right-box">
	<c:if test="${not empty requestScope.detailsOfOrder}">
	<table border="1">
					<tr>
					    <th>numberOrder</th>
						<th>id</th>
						<th>coffee</th>
						<th>price</th>
						<th>count</th>
					</tr>
					<c:forEach var="drinkInOrder" items="${requestScope.detailsOfOrder}">
						<tr>
						    <td><c:out value="${drinkInOrder.numberOrder}" /></td>
							<td><c:out value="${drinkInOrder.drinkId}" /></td>
							<td><c:out value="${drinkInOrder.typeOfCoffee}" /></td>
							<td><c:out value="${drinkInOrder.price}" /></td>
							<td><c:out value="${drinkInOrder.count}" /></td>
						</tr>
					</c:forEach>
				</table>
				</c:if>
				
				</div>
				</div>
				</c:if>
</body>
</html>