package com.aeox.app.security.exception;

import com.aeox.app.login.dto.ErrorCode;
import com.aeox.app.login.dto.ErrorMessage;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import java.util.logging.Logger;

public class UserNotAllowedExceptionMapper implements ExceptionMapper<UserNotAllowedException>{

    private static final Logger LOG = Logger.getLogger(UserNotAllowedExceptionMapper.class.getName());

    @Override
    public Response toResponse(UserNotAllowedException userNotAllowedException) {
        LOG.warning("NOT ALLOWED -> "+ userNotAllowedException.getUser());

        return Response.status(Response.Status.UNAUTHORIZED)
                .entity(new ErrorMessage(ErrorCode.PERMISSIONS_NOT_ALLOWED, "Not allowed"))
                .build();
    }
}
