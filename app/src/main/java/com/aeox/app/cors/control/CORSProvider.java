/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeox.app.cors.control;

import java.io.IOException;
import java.lang.reflect.Method;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author plopezm
 */
@Provider
@CORSEnabled
public class CORSProvider implements ContainerResponseFilter {

   @Context
   private ResourceInfo resourceInfo;
    
   @Override
   public void filter(final ContainerRequestContext requestContext,
                      final ContainerResponseContext cres) throws IOException {
       
      Method resourceMethod = resourceInfo.getResourceMethod();
      CORSEnabled cors = resourceMethod.getDeclaredAnnotation(CORSEnabled.class);
      
      if(cors == null){
        Class<?> resourceClass = resourceInfo.getResourceClass();
        cors = resourceClass.getDeclaredAnnotation(CORSEnabled.class);
      }
      
      cres.getHeaders().add("Access-Control-Allow-Origin", cors.domain());
      cres.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
      cres.getHeaders().add("Access-Control-Allow-Credentials", Boolean.toString(cors.allowCredentials()));
      cres.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
      cres.getHeaders().add("Access-Control-Max-Age", Long.toString(cors.maxAge()));
   }

}
