package com.aeox.app.login.entity;

import com.aeox.app.common.entity.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Created by pablolm on 23/7/17.
 */
@Entity
@Table(name = "Users",
        indexes = {
            @Index(name = "index_users_username", columnList = "username", unique = true)
        })
@NamedQueries({
    @NamedQuery(name = User.NAMED_GET_BY_USER_AND_PASSWORD, query = "SELECT u FROM User u WHERE u.username = :username AND u.password = :password"),
    @NamedQuery(name = User.NAMED_GET_SALT_BY_USERNAME, query = "SELECT u.salt FROM User u WHERE u.username = :username")
})
public class User extends AbstractEntity {

    public static final String NAMED_GET_BY_USER_AND_PASSWORD = "login.entity.User.getByUserAndPassword";
    public static final String NAMED_GET_SALT_BY_USERNAME = "login.entity.User.getSaltByUsername";

    @Column(nullable = false, unique = true)
    private String username;
    @JsonIgnore
    @Column(nullable = false)
    private String password;
    @JsonIgnore
    private byte[] salt;
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

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

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        return getId()== ((User) o).getId() && getUsername().equals(user.getUsername());
    }

    @Override
    public int hashCode() {
        return getUsername().hashCode();
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", group=" + group +
                '}';
    }
}
