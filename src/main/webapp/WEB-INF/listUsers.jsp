<%@page import="com.sopra.Tools"%>
<%@page import="com.sopra.Constants"%>
<%@page import="com.sopra.Password"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.sopra.User"%>
<%@ page import="java.util.Map, java.util.HashMap, java.util.List, java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="Skeleton-2.0.4/css/listUsers.css">
<link rel="stylesheet" href="Skeleton-2.0.4/css/normalize.css">
<link rel="stylesheet" href="Skeleton-2.0.4/css/skeleton.css">
<title>List of Users</title>
</head>
<body>



<h1>Who is registered ? </h1>

<% List<User> listUsers = (List<User>) request.getAttribute("listUsers");
Tools tools = new Tools();%>

 
<table>

<tr>


<th>Login</th>
<th>Email</th>
<th>Rank</th>
<th>Count</th>
<th>Date creation</th>
<th>Last Connexion</th>
<th>Action</th>



</tr>

<% Password password = new Password(); 
for (int i=0; i<listUsers.size(); i++){%>

<tr>

<td><%= listUsers.get(i).getLogin() %></td>
<td><%= listUsers.get(i).getEmail() %></td>
<td><%=listUsers.get(i).getRank()%></td>
<td><%=listUsers.get(i).getCount()%></td>
<td><%=listUsers.get(i).getCreateTime()%></td>
<td><%=listUsers.get(i).getLastConnexion()%></td>
<td>
<%if (!tools.isAFacebookUser(listUsers.get(i))){ %>

<form method='post' action="sendEmail">
	
	<input  type='hidden'  name='token' value='<%=listUsers.get(i).getToken()%>'/>
	<button>Send Email to reset password</button>
	</form>
	<%} %>
</td>

	<% } %>


</table>
	
	<br>
	<br>
	
	

<a href="home.jsp" >Home</a>
<br>
<a href="edition.html" >Modify profil</a>
<br>
<a href="disconnect" >Sign Out</a>




</body>
</html>