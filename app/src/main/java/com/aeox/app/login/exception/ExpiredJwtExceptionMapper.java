package com.aeox.app.login.exception;

import com.aeox.app.login.dto.ErrorCode;
import com.aeox.app.login.dto.ErrorMessage;
import io.jsonwebtoken.ExpiredJwtException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class ExpiredJwtExceptionMapper implements ExceptionMapper<ExpiredJwtException> {
    @Override
    public Response toResponse(ExpiredJwtException e) {
        return Response.status(Response.Status.UNAUTHORIZED)
                .entity(new ErrorMessage(ErrorCode.JWT_EXPIRED_EXCEPTION, e.getMessage()))
                .build();
    }
}
