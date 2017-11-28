package com.sopra.api;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.sopra.User;
import com.sopra.UsersManagement;

import java.util.List;

/* Service REST interrogé par notre application */

@Path("users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource{
	
	public UserResource() {
	}
	
	@EJB
	private UsersManagement um;

	/* Méthode GET 
	 * -----------------
	 * Path : api/users 
	 * Retourne la liste des users au format JSON 
	 * ----------------- */
	@GET
	public Response getUsers() {
		List<User> users = um.findAllUsers();
		return Response
				.status(Status.OK)
				.entity(users)
				.build();
	}
	
	/* Méthode GET 
	 * -----------------
	 * Path : api/users/{token}
	 * Retourne le user de token {token}
	 * ----------------- */
	@GET
	@Path("{token}")
	public Response getUserByToken(@PathParam("token") String token) {
		User user = um.findByToken(token);
		return Response
				.status(Status.OK)
				.entity(user)
				.build();
	}
	
	
	/* !!!! BUG - issue with the password in bytes sended by the client : Can not deserialize instance of byte[] out of START_OBJECT token
 at [Source: io.undertow.servlet.spec.ServletInputStreamImpl@7852eda; line: 4, column: 15] (through reference chain: com.sopra.User["password"]) !!!!*/
	
	/* Méthode POST 
	 * -----------------
	 * Path : api/users/new-user
	 * Retourne le user ajouté
	 * ----------------- */
	/*
	@POST
	@Path("new-user")
	public Response createUser(User user) {

		String passwd = "";
		try {
			System.out.println("!!! DANS LE TRY !!!");
			passwd = new String(user.getPassword(), "UTF-8");
		} catch (Exception e) {System.out.println("!!! ERREUR CONVERSION : CATCH !!!");}
		um.addUser(user.getLogin(), passwd, user.getEmail());
		return Response
				.status(Status.OK)
				.entity(user)
				.build();
	}
	*/
	
	
	/* Méthode POST 
	 * -----------------
	 * Path : api/users/update 
	 * Retourne le user modifié
	 * ----------------- */
	@POST
	@Path("update")
	public Response updateUser(User user) {
		um.updateUser(user);
		return Response
				.status(Status.OK)
				.entity(user)
				.build();
	}
	
	/* Méthode DELETE 
	 * -----------------
	 * Path : api/users/{id}
	 * Retourne le user supprimé
	 * ----------------- */
	@DELETE
	@Path("{id}")
	public Response deleteUserById(@PathParam("id") int id) {
		User user = um.deleteUserById(id);
		return Response
				.status(Status.OK)
				.entity(user)
				.build();
	}


	

}
