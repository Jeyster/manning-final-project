<%@page import="com.sopra.LoginServlet"%>
<%@page import="com.sopra.Constants"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>



<jsp:useBean id="coyote" class="com.sopra.Constants" />





<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>

	<h1>Login</h1>
	<div class = "alert"></div>
	<div class="alert"><%=request.getAttribute(Constants.ALERT_ATTRIBUTE) %></div>
	<form method="post" class="container" max-width: 960px>
    <label class= "row"><span class="three columns">login</span> <input class="nine columns"  type="text" name="login"  ></label>
    <label class= "row"><span class="three columns">password </span><input class="nine columns" type="password" name="password" ></label>
    <button>Log In</button>
</form>
	<div>Not registered yet ? <a href="register">Register now!</a></div>
	
<link rel="stylesheet" href="Skeleton-2.0.4/css/normalize.css">
<link rel="stylesheet" href="Skeleton-2.0.4/css/skeleton.css">

</html>

