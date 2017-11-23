<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="com.sopra.User"%>
<%@ page import="java.util.Map, java.util.HashMap, java.util.List, java.util.ArrayList" %>
<%@page import="com.sopra.Alert"%>
<%@page import="com.sopra.Constants"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Change your password</title>
</head>
<body>

<h1>Change your password</h1>

<div class="alert">${alert}</div>
	<%Alert.setAlert(""); %>

<form method="post" class="container" max-width: 960px>
<label class= "row"><span class="three columns">password: </span><input class="nine columns" type="password" name="password"></label>
<label class= "row"><span class="three columns">confirm password: </span><input class="nine columns" type="password" name="password-confirmation"></label>
<button>Change Password</button>
</form>

</body>
</html>
