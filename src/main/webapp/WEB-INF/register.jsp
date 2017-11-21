<%@page import="com.sopra.Alert"%>
<%@page import="com.sopra.Constants"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Register</title>
</head>

	<h1>Register</h1>
	<div class="alert">${alert}</div>
	<%Alert.setAlert(""); %>
	<form method="post" class="container" max-width: 960px>
		<label class= "row"><span class="three columns">login: </span><input class="nine columns" type="text" name="login"></label>
		<label class= "row"><span class="three columns"> email: </span><input class="nine columns" type="email" name="email"></label>
		<label class= "row"><span class="three columns">password: </span><input class="nine columns" type="password" name="password"></label>
		<label class= "row"><span class="three columns">confirm password: </span><input class="nine columns" type="password" name="password-confirmation"></label>
		<button>Register</button>
	</form>

<link rel="stylesheet" href="Skeleton-2.0.4/css/normalize.css">
<link rel="stylesheet" href="Skeleton-2.0.4/css/skeleton.css">
</html>