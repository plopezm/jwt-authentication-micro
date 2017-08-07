package com.aeox.app.security.exception;

import com.aeox.app.login.entity.User;

public class UserNotAllowedException extends RuntimeException{

    private User user;

    public UserNotAllowedException(User user) {
        super("User \""+user.getUsername()+"\" not allowed.");
    }

    public User getUser() {
        return user;
    }
}
