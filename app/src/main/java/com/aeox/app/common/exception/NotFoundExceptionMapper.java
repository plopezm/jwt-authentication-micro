package com.aeox.app.common.exception;

import javax.persistence.NoResultException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class NotFoundExceptionMapper implements ExceptionMapper<NoResultException>{

    @Override
    public Response toResponse(NoResultException e) {
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
