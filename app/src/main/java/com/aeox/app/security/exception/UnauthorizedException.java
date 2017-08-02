package com.aeox.app.security.exception;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class UnauthorizedException extends Exception{
}
