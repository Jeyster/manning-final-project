#projet-final

This is a **Java** project about a login application.

#what you have to do beforehand

using wildfly10x, create a mySQL datasource with the next properties:

name: LoginDS
JNDI name: java:/LoginDS
connection url: jdbc:mysql://localhost:3306/login

#what you have to know about this login app

* the connected user is registered in the session: if you want to disconnect, go to the login page (its first effect is to remove the user from the session)

## The package contains :
* constants class : it contains all constants (strings, mainly) that could be use many times. Please check it to not create twice the same constant.
* LoginServlet: it contains all needed to connect a user. Once authentified, it redirects to the home page.
* ModifyUserServlet: it contains all needed to modify a user. Once modified, it redirects to the home page.
* RegisterServlet: it contains all needed to add a new user. Once added, it redirects to the home page.