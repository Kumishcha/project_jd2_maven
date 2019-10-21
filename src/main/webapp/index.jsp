<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Index Page</title>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename = "localization.local" var="loc"/>
<fmt:message bundle = "${loc}" key="local.welcomeMessage" var="welcomeMessage"/>
<fmt:message bundle = "${loc}" key="local.registrationMessage" var="registrationMessage"/>
<fmt:message bundle = "${loc}" key="local.enterMessage" var="enterMessage"/>
<fmt:message bundle = "${loc}" key="local.loginMessage" var="loginMessage"/>
<fmt:message bundle = "${loc}" key="local.passwordMessage" var="passwordMessage"/>
<fmt:message bundle = "${loc}" key="local.locbutton.name.ru" var="ru_button"/>
<fmt:message bundle = "${loc}" key="local.locbutton.name.en" var="en_button"/>
<fmt:message bundle = "${loc}" key="local.locbutton.name.registration" var="registration_button"/>
<fmt:message bundle = "${loc}" key="local.locbutton.name.singIn" var="singIn_button"/>
</head>
<body>
<div id="header1">
<form action="controller" method="get">
        <input type="hidden" name="command" value="local" />
        <input type="hidden" name="local" value="ru" />
		<input type="submit" value="${ru_button}" />
</form>
<form action="controller" method="get">
<input type="hidden" name="command" value="local" />
        <input type="hidden" name="local" value="en" />
		<input type="submit" value="${en_button}" />
</form>
</div>
	<div id="main-box">
	<div id="left-box">
	<h1><c:out value= "${welcomeMessage}"/></h1>
	<h3><c:out value= "${registrationMessage}"/></h3>
	<form action="controller" method="get">
		<input type="hidden" name="command" value="go-to-registration-page" />
		<input type="submit" value="${registration_button}" />
	</form>
<h3><c:out value="${requestScope.signOutMessage}" /></h3>	
<h4><c:out value= "${enterMessage}"/></h4>
	<form action="controller" method="post">
		<input type="hidden" name="command" value="authorization" />
		<div>
			<input type="text" name="login"> <label for="login"><c:out value= "${loginMessage}"/></label>
		</div>
		<br>
		<div>
			<input type="password" name="password"> <label for="pass"><c:out value= "${passwordMessage}"/></label>
		</div>
		<br>
		<input type="submit" value="${singIn_button}"  /><br />
	</form>
	</div>
	</div>
</body>
</html>
