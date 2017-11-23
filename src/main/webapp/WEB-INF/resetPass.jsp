<%@page import="com.sopra.Alert"%>
<%@page import="com.sopra.Constants"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ResetPassword</title>
</head>

	<h1>Reset Password</h1>

	
	<div class="alert"><%=request.getAttribute(Constants.ALERT_ATTRIBUTE) %></div>
	
    <div class = "alert">${alert}</div>
	<%Alert.setAlert(""); %>

<form action = "ResetPassServlet" method="post" class="container" max-width: 960px>
    <label class= "row"><span class="three columns">New password</span> <input class="nine columns"  type="password" name="newPass"></label>
    <label class= "row"><span class="three columns">Re-enter new password </span><input class="nine columns" type="password" name="reNewPass"></label>
    
    <button>Reset</button>
</form>
	
<link rel="stylesheet" href="Skeleton-2.0.4/css/normalize.css">
<link rel="stylesheet" href="Skeleton-2.0.4/css/skeleton.css">

</html>