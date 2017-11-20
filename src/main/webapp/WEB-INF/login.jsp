<%@page import="com.sopra.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>
	<h1>Login</h1>
	<div class="alert"><%=request.getAttribute(Constants.ALERT_ATTRIBUTE) %></div>	
	<form method="post">
		<label>login : <input type="text" name="login"></label><br>
		<label>password : <input type="password" name="password"></label><br>
		<button>Sign in</button>
	</form>
</body>
</html>