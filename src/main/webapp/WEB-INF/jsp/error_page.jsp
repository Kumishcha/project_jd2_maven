<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
<h1>!!! Something went wrong, check back later !!!</h1>
<h2><c:out value="${requestScope.errorMessage}" /></h2>
<br>
		<form action="controller" method="get">
			<input type="hidden" name="command" value="go-to-index-page" /> <input
				type="hidden" name="message"/> <input type="submit"
				value="to Home page" />
		</form>
</body>
</html>