package com.aeox.app.login.boundary;

import com.aeox.app.login.entity.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.constraints.NotNull;

/**
 * Created by pablolm on 23/7/17.
 */
@Stateless
public class LoginService {

    @PersistenceContext
    private EntityManager em;

    public User findByUsernameAndPassword(@NotNull String username, @NotNull String password){
        Query query = em.createNamedQuery(User.NAMED_GET_BY_USER_AND_PASSWORD);
        query.setParameter("username", username);
        query.setParameter("password", password);
        return (User) query.getSingleResult();
    }

    public User createUser(@NotNull String username, @NotNull String password){
        User userToCreate = new User();
        userToCreate.setUsername(username);
        userToCreate.setPassword(password);
        em.persist(userToCreate);
        em.flush();
        em.refresh(userToCreate);
        return userToCreate;
    }


}
