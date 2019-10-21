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
		<input type="hidden" name="command" value="go-to-view-all-products-page" />
		<input type="submit" value="Back" /><br />
	</form>
		<form action="controller" method="get">
			<input type="hidden" name="command" value="go-to-index-page" /> 
			<input type="hidden" name="message" value="" />
			<input type="submit" value="Sign out" /><br />
		</form>
</div>
<table border="1">
   <tr>
    <th> product </th>
    <th> quantity in stock </th>
    <th> price </th>
   </tr>
   <tr>
				<td><c:out value = "${product.productName}"/></td>
				<td>
					<form action="controller" method="post">
						<input type="hidden" name="command" value="increase-quantity-in-stock" /> 
						<input type="hidden" name="productName" value="${product.productName}"> 
						<input type="text" name="productCount"  value = "${product.quantityInStock}">
						<button type="submit">Change increase</button>
					</form>
				</td>
				<td> <form action="controller" method="post">
						<input type="hidden" name="command" value="change-price" /> 
						<input type="hidden" name="productName" value="${product.productName}"> 
						<input type="text" name="newPrice" value = "${product.price}">
						<button type="submit">Change price</button>
					</form>
				</td>
				</tr>
  </table>
  * max quantity in stock = 10000<br>
  * max price = 10
 <h3><c:out value="${requestScope.message}"/></h3>
</body>
</html>