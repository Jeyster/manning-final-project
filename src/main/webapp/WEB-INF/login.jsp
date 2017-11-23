<%@page import="com.sopra.Alert"%>
<%@page import="com.sopra.LoginServlet"%>
<%@page import="com.sopra.Constants"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<link rel="stylesheet" href="Skeleton-2.0.4/css/normalize.css">
<link rel="stylesheet" href="Skeleton-2.0.4/css/skeleton.css">
</head>
<<<<<<< HEAD
<!-- SCRIPT POUR ACCEDER AUX DONNEES FACEBOOK -->

	<script>
		window.fbAsyncInit = function() {
			FB.init({
				appId : '1681319908591975',
				cookie : true,
				xfbml : true,
				version : 'v2.11'
			});

			FB.AppEvents.logPageView();

			trigger();

		};

		(function(d, s, id) {
			var js, fjs = d.getElementsByTagName(s)[0];
			if (d.getElementById(id)) {
				return;
			}
			js = d.createElement(s);
			js.id = id;
			js.src = "https://connect.facebook.net/en_US/sdk.js";
			fjs.parentNode.insertBefore(js, fjs);
		}(document, 'script', 'facebook-jssdk'));
	</script>

	<script>
		function trigger() {
			//some code that requires the FB object
			//such as...
			console.log('loaded')
			FB.getLoginStatus(function(response) {
				done(response);
			});
		}

		function done(response) {
			console.log('resp done', response)

			FB.api('/'+response.authResponse.userID, function(response) {
				console.log(response);

			});
			
			
			
			FB.api('/'+response.authResponse.userID, {fields: 'email'}, function(response) {
				console.log('email', response);

			});
		}
	</script>
	
<!-- FIN DES SCRIPTS FACEBOOK -->

=======
<body>
>>>>>>> 1d74f325483beae1e3190e5a23c819465c0ca7ec
	<h1>Login</h1>


<div class = "alert">${alert}</div>
	<%Alert.setAlert(""); %>

<form method="post" class="container" max-width: 960px>
    <label class= "row"><span class="three columns">login/email</span> <input class="nine columns"  type="text" name="connexionField"></label>
    <label class= "row"><span class="three columns">password </span><input class="nine columns" type="password" name="password"></label>
    
    <button>Log In</button>
</form>
<<<<<<< HEAD
	<div>Not registered yet ? <a href="register">Register now!</a></div><br><br>
	<div>Continue with Facebook : <fb:login-button scope="public_profile,email" onlogin="done()">
	</fb:login-button></div>
	
<link rel="stylesheet" href="Skeleton-2.0.4/css/normalize.css">
<link rel="stylesheet" href="Skeleton-2.0.4/css/skeleton.css">
=======

	<div class = "link">Not registered yet ? <a href="register">Register now!</a></div><br>
	<div class = "link"><a href="forgot">Forgot account?</a></div>
</body>

>>>>>>> 1d74f325483beae1e3190e5a23c819465c0ca7ec

</html>

