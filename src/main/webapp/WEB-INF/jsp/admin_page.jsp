<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="m" uri="mytags"%>
<%-- <% response.setHeader("Cache-Control", "no-cahce, no-store, must-revalidate"); %> --%>
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
				type="submit" value="Sign out" /><br />
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
	</div>
	<m:today/>
</body>
</html>