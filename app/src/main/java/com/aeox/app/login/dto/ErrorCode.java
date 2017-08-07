package com.aeox.app.login.dto;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ErrorCode {
    PERMISSIONS_NOT_ALLOWED,
    JWT_SIGNATURE_EXCEPTION,
    JWT_EXPIRED_EXCEPTION,
    VALIDATION_ERROR;


    @JsonValue
    public int toValue() {
        return this.ordinal();
    }
}
