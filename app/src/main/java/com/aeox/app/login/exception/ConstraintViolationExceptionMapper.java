package com.aeox.app.login.exception;

import com.aeox.app.login.dto.ErrorCode;
import com.aeox.app.login.dto.ErrorMessage;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException>{
    @Override
    public Response toResponse(ConstraintViolationException e) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(new ErrorMessage(ErrorCode.VALIDATION_ERROR, e.getMessage()))
                .build();
    }
}
