package com.aeox.app.login.resource;

import com.aeox.app.login.boundary.LoginService;
import com.aeox.app.login.entity.User;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * Created by pablolm on 23/7/17.
 */


@Path("users")
public class LoginResource {

    @Inject
    private LoginService loginService;

    @GET
    @Path("{user}/{pass}")
    @Produces(value = {"application/json"})
    public Response login(@PathParam("user") String user, @PathParam("pass")String password){
        User userLogged = loginService.findByUsernameAndPassword(user, password);
        return Response.ok().entity(userLogged).build();
    }

}
