package com.aeox.app.login.resource;

import com.aeox.app.login.boundary.LoginService;
import com.aeox.app.login.entity.User;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * Created by pablolm on 23/7/17.
 */


@Path("users")
public class LoginResource {

    @Inject
    private LoginService loginService;

    @GET
    @Path("{username}/{password}")
    @Produces(value = {"application/json"})
    public Response login(@PathParam("username") String username, @PathParam("password")String password){
        User userToLogin = new User(username, password);
        User userLogged = loginService.findByUsernameAndPassword(userToLogin);
        return Response.ok().entity(userLogged).build();
    }

    @POST
    @Produces(value = {"application/json"})
    public Response createUser(User user){

        return null;
    }

}
