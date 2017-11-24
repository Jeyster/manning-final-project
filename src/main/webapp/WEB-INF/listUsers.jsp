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
<title>List of Users</title>
</head>
<body>



<h1>List of Users : </h1>

<% List<User> listUsers = (List<User>) request.getAttribute("listUsers"); %>

 
<table>

<tr>


<th>Login</th>
<th>Email</th>
<th>Rank</th>
<th>Action</th>



</tr>

<% Password password = new Password(); 
for (int i=0; i<listUsers.size(); i++){%>

<tr>

<td><%= listUsers.get(i).getLogin() %></td>
<td><%= listUsers.get(i).getEmail() %></td>
<td><%=listUsers.get(i).getRank()%></td>
<!--  
<td><%=password.toHex(listUsers.get(i).getPassword())%></td>
<td><%=listUsers.get(i).getToken()%></td>
-->

<td>

<form method='post' action="sendEmail">
	
	<input  type='hidden'  name='token' value='<%=listUsers.get(i).getToken()%>'/>
	<button>Send Email to reset password</button>
	</form>
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