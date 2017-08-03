package com.aeox.app.security.control;

import com.aeox.app.security.boundary.JWTSecurized;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

import javax.crypto.KeyGenerator;
import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.security.Key;
import java.util.logging.Logger;

@Provider
@JWTSecurized
public class JWTValidator implements ContainerRequestFilter{

    private static final Logger LOG = Logger.getLogger(JWTValidator.class.getName());
    @Inject
    private Key serverKey;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        String token = authorizationHeader.substring("Bearer".length()).trim();

        try {

            // Validate the token
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(serverKey).parseClaimsJws(token);
            LOG.info(claimsJws.toString());


        } catch (Exception e) {
            LOG.severe("Invalid token : " + token);
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }


}
