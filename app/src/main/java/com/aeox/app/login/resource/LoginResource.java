package com.aeox.app.login.resource;

import com.aeox.app.login.boundary.LoginService;
import com.aeox.app.login.entity.Permission;
import com.aeox.app.login.entity.User;
import com.aeox.app.security.boundary.JWTSecurized;
import com.aeox.app.security.exception.UnauthorizedException;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

/**
 * Created by pablolm on 23/7/17.
 */


@Path("users")
@Produces(value = {"application/json"})
public class LoginResource {

    @Inject
    private LoginService loginService;

    @Context
    private UriInfo uri;

    @GET
    @Path("{username}/{password}")
    public Response login(@PathParam("username") String username, @PathParam("password")String password) throws UnauthorizedException {
        User userToLogin = new User(username, password);
        String token = loginService.loginUser(uri.getBaseUri().toString(), userToLogin);
        return Response.ok().header(HttpHeaders.AUTHORIZATION, "Bearer " + token).build();
    }

    @POST
    @Consumes(value = {"application/json"})
    @JWTSecurized(permissions = {"ALL","CREATE"})
    public Response createUser(User user){
        User userCreated = loginService.createUser(user);
        return Response.created(URI.create("/users")).build();
    }

    @GET
    @Path("/{id}")
    @JWTSecurized(permissions = {"ALL", "READ"})
    public Response getUser(@PathParam("id") Long id){
        User userFound = loginService.getUser(id);
        return Response.ok().entity(userFound).build();
    }



}
