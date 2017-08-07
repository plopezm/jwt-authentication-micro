package com.aeox.app.login.exception;

import com.aeox.app.login.dto.ErrorCode;
import com.aeox.app.login.dto.ErrorMessage;
import io.jsonwebtoken.SignatureException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class SignatureExceptionMapper implements ExceptionMapper<SignatureException>{
    @Override
    public Response toResponse(SignatureException e) {
        return Response.status(Response.Status.UNAUTHORIZED)
                .entity(new ErrorMessage(ErrorCode.JWT_SIGNATURE_EXCEPTION, e.getMessage()))
                .build();
    }
}
