package com.aeox.app.login.boundary;

import com.aeox.app.login.entity.User;
import com.aeox.app.login.security.boundary.PasswordEncoded;
import org.jboss.logging.Logger;

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

    private static final Logger LOG = Logger.getLogger(LoginService.class.getName());

    @PersistenceContext
    private EntityManager em;

    @PasswordEncoded
    public User findByUsernameAndPassword(@NotNull User user){
        Query query = em.createNamedQuery(User.NAMED_GET_BY_USER_AND_PASSWORD);
        query.setParameter("username", user.getUsername());
        query.setParameter("password", user.getPassword());
        return (User) query.getSingleResult();
    }

    @PasswordEncoded
    public User createUser(@NotNull User user){
        em.persist(user);
        em.flush();
        em.refresh(user);
        return user;
    }


}
