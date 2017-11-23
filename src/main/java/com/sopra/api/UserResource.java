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
public class UserResource {

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
		List<User> trains = um.findAllUsers();
		return Response
				.status(Status.OK)
				.entity(trains)
				.build();
	}
	

}