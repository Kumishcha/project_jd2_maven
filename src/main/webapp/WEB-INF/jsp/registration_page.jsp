<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
   
<!DOCTYPE html> <!-- PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<h2>This is registration page</h2>
	<h3>Please, fill in all fields</h3>
	<form action="controller" method="post">
		<input type="hidden" name="command" value="registration" />
		<p>
		<input type="text" name="name" required> <label for="name">Name</label>
		</p>
		<p>
			<input type="text" name="surname" required> <label for="surname">Surname</label>
		</p>
		<p>
			<input type="email" name="email" required> <label for="e-mail">E-mail</label>
		</p>
		<c:out value="${requestScope.messageLoginAlreadyExists}" />
		<c:out value="${requestScope.messageErrorEmailAndPasswordFormat}" />
		<p>
			<input type="text" name="login" required> <label for="login">Login</label>
		</p>
		<p>
			<input type="password" name="password" required> <label
				for="password">Password</label>
		</p>
		<c:out value="${requestScope.messageWrongPassword}" />
	    <p>
			<input type="password" name="password2" required> <label
				for="password">Re-enter password</label>
		</p>
		<button type="submit">Create your account</button>
	</form>
	<br>
	<form action="controller" method="get">
		<input type="hidden" name="command" value="go-to-index-page" />
		<input type="submit" value="Back" /><br />
	</form>
</body>
</html>