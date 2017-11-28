<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.sopra.Alert"%>
<%@page import="com.sopra.Constants" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>forgotPass</title>
</head>
<body>
<h1>forgot Password</h1>
<div class="alert">${alert}</div>
	<%Alert.setAlert(""); %>


<form method="post" action= "forgotPass" class="container" max-width: 960px>
    <label class= "row"><span class="three columns">your email:</span> <input class="nine columns"  type="text" name="email"></label>
  
    <button>send mail</button>
</form>
	
<link rel="stylesheet" href="Skeleton-2.0.4/css/normalize.css">
<link rel="stylesheet" href="Skeleton-2.0.4/css/skeleton.css">


</body>
</html>