<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib prefix="m" uri="mytags"%>
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
		<input type="hidden" name="command" value="go-to-admin-page" />
		<input type="submit" value="Back" /><br />
	</form>
		<form action="controller" method="get">
			<input type="hidden" name="command" value="go-to-index-page" /> 
			<input type="hidden" name="message" value="" />
			<input type="submit" value="Sign out" /><br />
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
<table border="1">
   <caption>Products</caption>
   <tr>
    <th> product </th>
    <th> quantity in stock </th>
    <th> price </th>
     <th></th>
   </tr>
  <c:forEach var="product" items="${requestScope.products}" >
			<tr>
				<td><c:out value = "${product.productName}"/></td>
				<td><c:out value = "${product.quantityInStock}"/></td>
				<td><c:out value = "${product.price}"/></td>
				<td>
				<form action="controller" method="get">
			        <input type="hidden" name="command" value="product-details" /> 
			        <input type="hidden" name="productName" value="${product.productName}">
			        <input type="submit" value="Details" /><br />
		          </form>
		       </td>
			</tr>
	</c:forEach>
  </table>
 * min coffee = 90 <br>
 * min milk = 800 <br>
 * min sugar = 20<br>
 * min coconut syrup = 100<br>
 * min cherry syrup = 100<br>
 * min water = 10000<br>
 </div>
 </div>
 	<m:today/>
</body>
</html>