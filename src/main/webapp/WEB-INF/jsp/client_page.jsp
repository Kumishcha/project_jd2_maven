<%@page import="by.htp.jd2.bean.User"%>
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
	<div id="header1">
		<form action="controller" method="get">
			<input type="hidden" name="command" value="go-to-index-page" /> <input
				type="hidden" name="message" /> 
				<input type="submit" value="Sign out" /><br />
		</form>
	</div>
	<div id="main-box">
		<div id="left-box">
			<h1>Hello, ${sessionScope.user.name}</h1>
			<h2>
				<c:out value="${requestScope.welcomeMessage}"></c:out>
			</h2>
			<h3>Balance ${requestScope.balance}</h3>
			<form action="controller" method="get">
				<input type="hidden" name="command" value="add-money" />
				<button type="submit">Add money to the account</button>
			</form>
			<br>
				<form action="controller" method="get">
			<input type="hidden" name="command" value="view-all-user-orders" />
			<input type="hidden" name="userId" value="${sessionScope.user.userId}">
			<input type="submit" value="See all my orders" /><br />
		</form>
			<br>
			<h3>Please, select a drink</h3>
			<h4>
				<c:out value="${requestScope.message}" />
			</h4>
			<form action="controller" method="post">
				<input type="hidden" name="command" value="choose-a-type-of-coffee" />
				<button type="submit" name="typeOfCoffee" value="espresso">espresso</button>
				<button type="submit" name="typeOfCoffee" value="cappuccino">cappuccino</button>
				<button type="submit" name="typeOfCoffee" value="latte">latte</button>
			</form>
			
					<form action="controller" method="post">
						<input type="hidden" name="command" value="choose-a-drink" />
						<input type="hidden" name="typeOfCoffee" value="${requestScope.typeOfCoffee}" />
						<table> 
						<c:if test="${requestScope.typeOfCoffee eq 'espresso'}">
						<tr>
						<td><input type="radio" name="volume" value="50">50 ml<br>
						    <input type="radio" name="volume" value="100">100 ml </td>
						<td>
						<br><input type="radio" name="fortress" value="1">strength 1<br>
							<input type="radio" name="fortress" value="2">strength 2<br>
							<input type="radio" name="fortress" value="3">strength 3</td>
						<td><input type="radio" name="countOfSugar" value="0">without sugar <br>
							<input type="radio" name="countOfSugar" value="1">sugar</td>
						<td> 
						<br><input type="radio" name="typeOfSyrup" value="without">without syrup <br>
							<input type="radio" name="typeOfSyrup" value="coconut">coconut syrup <br>
							<input type="radio" name="typeOfSyrup" value="cherry">cherry syrup</td>
						</tr>
						</c:if>
						<c:if test="${requestScope.typeOfCoffee eq 'cappuccino'}">
						<tr>
						<td><input type="radio" name="volume" value="300">300 ml <br>
							<input type="radio" name="volume" value="400">400 ml</td>
						<td> 
						<br><input type="radio" name="fortress" value="1">strength 1 <br>
							<input type="radio" name="fortress" value="2">strength 2 <br>
							<input type="radio" name="fortress" value="3">strength 3</td>
						<td><input type="radio" name="countOfSugar" value="0">without sugar <br>
							<input type="radio" name="countOfSugar" value="1">sugar </td>
						<td>
						<br><input type="radio" name="typeOfSyrup" value="without">without syrup <br>
							<input type="radio" name="typeOfSyrup" value="coconut">coconut syrup <br>
							<input type="radio" name="typeOfSyrup" value="cherry">cherry syrup</td>
						</tr>
						</c:if>
						<c:if test="${requestScope.typeOfCoffee eq 'latte'}">
						<tr>
						<td><input type="radio" name="volume" value="300">300 ml <br>
							<input type="radio" name="volume" value="400">400 ml</td>
						<td>
						<br><input type="radio" name="fortress" value="1">strength 1<br>
							<input type="radio" name="fortress" value="2">strength 2<br>
							<input type="radio" name="fortress" value="3">strength 3</td>
						<td><input type="radio" name="countOfSugar" value="0">without sugar <br>
							<input type="radio" name="countOfSugar" value="1">sugar</td>
						<td>
						<br><input type="radio" name="typeOfSyrup" value="without">without syrup <br>
							<input type="radio" name="typeOfSyrup" value="coconut">coconut syrup <br>
							<input type="radio" name="typeOfSyrup" value="cherry">cherry syrup</td>
						</tr>
						</c:if>
						</table>
			            <br>
			<button type="submit">Add to cart</button>
			</form>
		</div>
		<div id="right-box">
			<c:if test="${ not empty sessionScope.listDrinksInOrder}">
				<h2>Cart</h2>

				<h4>
					<c:out value="${requestScope.messageCountOfDrink}" />
				</h4>
			*max 3 drink
			<table border="1">
					<tr>
						<th></th>
						<th>id</th>
						<th>coffee</th>
						<th>price</th>
						<th>count (*max 3)</th>
						<th>numberOrder</th>
					</tr>
					<c:forEach var="drinkInOrder"
						items="${sessionScope.listDrinksInOrder}">
						<tr>
							<td>
								<form action="controller" method="post">
									<input type="hidden" name="command" value="remove-drink" /> <input
										type="hidden" name="drinkId" value="${drinkInOrder.drinkId}" />
									<input type="hidden" name="numberOrder"
										value="${drinkInOrder.numberOrder}" />
									<button type="submit">delete</button>
								</form>
							</td>
							<td><c:out value="${drinkInOrder.drinkId}" /></td>
							<td><c:out value="${drinkInOrder.typeOfCoffee}" /></td>
							<td><c:out value="${drinkInOrder.price}" /></td>
							<td>
								<form action="controller" method="post">
									<input type="hidden" name="command"
										value="change-count-of-drink" /> <input type="hidden"
										name="drinkId" value="${drinkInOrder.drinkId}" /> <input
										type="hidden" name="numberOrder"
										value="${drinkInOrder.numberOrder}" /> <input type="text"
										name="countOfDrinks" value="${drinkInOrder.count}" />
									<button type="submit">change</button>
								</form>
							</td>
							<td><c:out value="${drinkInOrder.numberOrder}" /></td>
						</tr>
					</c:forEach>
				</table>
			<br>
				<form action="controller" method="get">
					<input type="hidden" name="command" value="order" />
					<button type="submit">pay</button>
				</form>
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
		<td><c:if test="${order.status == false}">
				<c:out value="not paid" />
			</c:if>
			<c:if test="${order.status == true}">
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
	</div>
</body>
</html>