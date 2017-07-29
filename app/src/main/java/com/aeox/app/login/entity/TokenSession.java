package com.aeox.app.login.entity;

import com.aeox.app.common.entity.AbstractEntity;

import javax.persistence.*;

@Entity
@Table(name = "token_sessions")
public class TokenSession extends AbstractEntity{

    @ManyToOne
    @JoinColumn(name = "column_id")
    private User user;

    @Column(name = "token", nullable = false)
    private String token;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
