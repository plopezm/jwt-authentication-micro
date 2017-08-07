package com.aeox.app.security.control;

import com.aeox.app.login.dto.ErrorCode;
import com.aeox.app.login.dto.ErrorMessage;
import com.aeox.app.login.entity.Permission;
import com.aeox.app.login.entity.User;
import com.aeox.app.security.boundary.JWTSecurized;
import com.aeox.app.security.exception.UserNotAllowedException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

import javax.crypto.KeyGenerator;
import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.lang.reflect.Method;
import java.security.Key;
import java.util.Arrays;
import java.util.logging.Logger;

@Provider
@JWTSecurized
public class JWTValidator implements ContainerRequestFilter{

    private static final Logger LOG = Logger.getLogger(JWTValidator.class.getName());

    @Context
    private ResourceInfo resourceInfo;

    @Inject
    private Key serverKey;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        String token = authorizationHeader.substring("Bearer".length()).trim();

        // Validate the token
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(serverKey).parseClaimsJws(token);
        LOG.info(claimsJws.toString());

        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.convertValue(claimsJws.getBody().get("user"), User.class);

        Method method = resourceInfo.getResourceMethod();
        JWTSecurized securizer = method.getDeclaredAnnotation(JWTSecurized.class);

        if(securizer.permissions().length == 0)
            return;

        if(!Arrays.stream(securizer.permissions()).anyMatch(s -> user.getGroup().getPermissions().contains(Permission.fromString(s)))){
            throw new UserNotAllowedException(user);
        }
    }


}
