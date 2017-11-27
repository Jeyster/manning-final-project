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
<body>
<!-- SCRIPT POUR UTILISER JQUERY -->

	<script src="http://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

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
			
			FB.getLoginStatus(function(response) {
				if (response.status === 'connected'){
					console.log("Connected to Facebook")
				} else if (response.status === 'unknown'){
					console.log("Not connected to Facebook yet");
				}
				
			});
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
	
<!-- FIN DES SCRIPTS FACEBOOK -->




	<h1>Login</h1>


<div class = "alert">${alert}</div>
	<%Alert.setAlert(""); %>

<form method="post" class="container" max-width: 960px>
    <label class= "row"><span class="three columns">login/email</span> <input class="nine columns"  type="text" name="connexionField"></label>
    <label class= "row"><span class="three columns">password </span><input class="nine columns" type="password" name="password"></label>
    
    <button>Log In</button>
</form>

	<div> Forgot your password? <a href="forgotPass.jsp">Recover now!</a></div>
	<div class = "link">Not registered yet ? <a href="<%=Constants.REGISTER_PAGE%>">Register now!</a></div><br>
	
	<div>Continue with Facebook : <fb:login-button scope="public_profile,email" onlogin="window.location='http://localhost:8080/projet-final-1.0-SNAPSHOT/facebookConnection.html'">
	</fb:login-button></div>
	


	
</body>

</html>

