package com.aeox.app;

import com.aeox.app.login.resource.LoginResource;

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
        
        //PROVIDERS
        return classes;
    }

    @Override
    public Set<Object> getSingletons() {
        final Set<Object> instances = new HashSet<>();
        //instances.add(new JacksonJaxbJsonProvider());
        return instances;
    }
}
