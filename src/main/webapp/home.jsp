<%@page import="com.sopra.Tools"%>
<%@page import="com.sopra.Constants"%>
<%@page import="com.sopra.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="Skeleton-2.0.4/css/normalize.css">
<link rel="stylesheet" href="Skeleton-2.0.4/css/skeleton.css">
<title>Home</title>
</head>
<body>

	<%User user = (User) request.getSession().getAttribute(Constants.CONNECTED_USER_ATTRIBUTE);;%>
	
<span>
<%=user.getLogin() %> is connected.
</span>

<h4>Hello world!</h4>
	<%Tools tools = new Tools();
	if (!tools.isAFacebookUser(user)){ %>
	<a href="edition.html">Modify user</a><br>
	<%}
	if (user.getRank()>1){%>
	<a href ="users">List of users</a><br>
	<a href ="go-to-angular">List of users with NgCli</a><br>
	<%} %>
	<a href ="disconnect">Sign out</a><br><br>


	

Compteur : <%= user.getCount() %>

	

</body>
</html>