package com.aeox.app.login.entity;

import com.aeox.app.common.entity.AbstractEntity;

import javax.persistence.*;

/**
 * Created by pablolm on 23/7/17.
 */
@Entity
@Table(name = "Users")
@NamedQueries({
    @NamedQuery(name = User.NAMED_GET_BY_USER_AND_PASSWORD, query = "SELECT u FROM User u WHERE u.username = :username AND u.password = :password")
})
public class User extends AbstractEntity {

    public static final String NAMED_GET_BY_USER_AND_PASSWORD = "login.entity.User.getByUserAndPassword";

    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
