package com.sopra.api;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/* Nécessaire au fonctionnement d'une api JAX-RS */

@ApplicationPath("api")
public class UserApplication extends Application{

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> s = new HashSet<Class<?>>();
		s.add(UserResource.class);
		return s;
	}
	
}
