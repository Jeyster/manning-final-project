<%@page import="com.sopra.Constants"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>
	<h1>Login</h1>
	<div class="alert"><%=request.getAttribute(Constants.ALERT_ATTRIBUTE) %></div>	
	<form method="post" class="container" max-width: 960px>
    <label class= "row"><span class="three columns">login</span> <input class="nine columns"  type="text" name="login" data-bind="textInput: displayName, aria-label=" Saisissez votre login."></label>
    <label class= "row"><span class="three columns">password </span><input class="nine columns" type="password" name="password" data-bind="textInput: displayName, aria-label=" Saisissez votre mot de passe"></label>
    <button>Log In</button>
</form>
	<div>Not registered yet ? <a href="register">Register now!</a></div>
</body>
</html>

