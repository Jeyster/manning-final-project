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
<body>
<%User user = (User) request.getSession().getAttribute(Constants.CONNECTED_USER_ATTRIBUTE); %>
<h1>Modify <%=user.getLogin() %> </h1>

<div><div class="alert"><%=request.getAttribute(Constants.ALERT_ATTRIBUTE) %></div>	</div>


<form method='post'  >
	
	<label> New login : <input type="text" name = "login"></label><br>
	<label> Password : <input type="password" name = "password"></label><br>
	<label> Confirm password : <input type="password" name = "password-confirmation"></label><br>
	
	<button>Edit</button>
	</form>





</body>
</html>