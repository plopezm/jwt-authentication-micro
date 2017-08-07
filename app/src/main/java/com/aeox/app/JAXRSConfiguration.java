package com.aeox.app;

import com.aeox.app.common.exception.NotFoundExceptionMapper;
import com.aeox.app.login.exception.ConstraintViolationExceptionMapper;
import com.aeox.app.login.exception.ExpiredJwtExceptionMapper;
import com.aeox.app.login.exception.SignatureExceptionMapper;
import com.aeox.app.login.resource.LoginResource;
import com.aeox.app.security.control.JWTValidator;
import com.aeox.app.security.exception.UnauthorizedExceptionMapper;
import com.aeox.app.security.exception.UserNotAllowedExceptionMapper;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Configures a JAX-RS endpoint. Delete this class, if you are not exposing
 * JAX-RS resources in your application.
 *
 */
@ApplicationPath("api/v1")
public class JAXRSConfiguration extends Application {
    
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        //RESOURCES
        classes.add(LoginResource.class);
        //MAPPERS
        classes.add(NotFoundExceptionMapper.class);
        classes.add(UnauthorizedExceptionMapper.class);
        classes.add(ExpiredJwtExceptionMapper.class);
        classes.add(SignatureExceptionMapper.class);
        classes.add(UserNotAllowedExceptionMapper.class);
        classes.add(ConstraintViolationExceptionMapper.class);
        //PROVIDERS
        classes.add(JWTValidator.class);
        return classes;
    }

    @Override
    public Set<Object> getSingletons() {
        final Set<Object> instances = new HashSet<>();
        //instances.add(new JacksonJaxbJsonProvider());
        return instances;
    }
}
