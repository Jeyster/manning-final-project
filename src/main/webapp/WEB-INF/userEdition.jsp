<%@page import="com.sopra.Alert"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.sopra.User"%>
    <%@page import="com.sopra.Constants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Edition</title>
</head>

<%User user = (User) request.getSession().getAttribute(Constants.CONNECTED_USER_ATTRIBUTE); %>
<h1>Modify <%=user.getLogin() %> </h1>

<div><div class="alert">${alert}</div>	</div>
<%Alert.setAlert(""); %>

<h3>Edit login</h3>
<form method='post' class="container" max-width: 960px>
	<label class= "row"><span class="three columns"> New login: </span><input class="nine columns" type="text" name = "login"></label>
	<button>Edit login</button>
</form>

<h3>Edit email</h3>
<form method='post' class="container" max-width: 960px>
	<label class= "row"><span class="three columns"> New email: </span><input class="nine columns" type="email" name = "email"></label>
	<button>Edit email</button>
</form>

<h3>Edit password</h3>
<form method="post" class="container" max-width: 960px>
	<label class= "row"><span class="three columns"> Password: </span><input class="nine columns" type="password" name = "password"></label>
	<label class= "row"><span class="three columns"> Confirm password: </span><input class="nine columns" type="password" name = "password-confirmation"></label>
	
	<button>Edit password</button>
	
<div>Return <a href='<%=Constants.HOME_PAGE%>'>home</a></div>

	
<link rel="stylesheet" href="Skeleton-2.0.4/css/normalize.css">
<link rel="stylesheet" href="Skeleton-2.0.4/css/skeleton.css">
</form>





</body>
</html>