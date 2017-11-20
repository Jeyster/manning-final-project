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
<h1>User Edition : </h1>


<% User user = (User) request.getSession().getAttribute(Constants.CONNECTED_USER_ATTRIBUTE); %>

<div><div class="alert"><%=request.getAttribute(Constants.ALERT_ATTRIBUTE) %></div>	</div>


<form method='post'  >
	
	<input  type='hidden'  name='userId' value="<%=user.getId() %>"  />
	<label> New login : <input type="text" name = "login"></label>
	<label> Password : <input type="password" name = "password"></label>
	<label> Confirm password : <input type="password" name = "password-confirmation"></label>
	
	<button>Edit</button>
	</form>





</body>
</html>