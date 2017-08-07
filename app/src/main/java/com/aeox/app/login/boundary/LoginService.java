package com.aeox.app.login.boundary;

import com.aeox.app.login.entity.TokenSession;
import com.aeox.app.login.entity.User;
import com.aeox.app.login.security.boundary.PasswordEncoded;
import com.aeox.app.security.exception.UnauthorizedException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.jboss.logging.Logger;

import javax.crypto.KeyGenerator;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.constraints.NotNull;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by pablolm on 23/7/17.
 */
@Stateless
public class LoginService {

    private static final Logger LOG = Logger.getLogger(LoginService.class.getName());

    @Inject
    private Key serverKey;

    @PersistenceContext
    private EntityManager em;

    @PasswordEncoded
    public User createUser(@NotNull User user){
        em.persist(user);
        em.flush();
        em.refresh(user);
        return user;
    }

    public TokenSession createTokenSession(@NotNull TokenSession tokenSession){
        em.persist(tokenSession);
        em.flush();
        em.refresh(tokenSession);
        return tokenSession;
    }

    public User findByUsernameAndPassword(@NotNull User user){
        LOG.info(user);
        Query query = em.createNamedQuery(User.NAMED_GET_BY_USER_AND_PASSWORD);
        query.setParameter("username", user.getUsername());
        query.setParameter("password", user.getPassword());
        return (User) query.getSingleResult();
    }

    @PasswordEncoded
    public String loginUser(String uriInfo, User user) throws UnauthorizedException {
        try {
            User userFound = this.findByUsernameAndPassword(user);

//            TokenSession tokenSession = new TokenSession();
//            tokenSession.setToken(issueToken(uriInfo, userFound.getUsername()));
//            tokenSession.setUser(userFound);
//            this.createTokenSession(tokenSession);

//            return tokenSession.getToken();
            return issueToken(uriInfo, userFound);
        }catch(NoResultException nre){
            LOG.warn(nre.getMessage());
            throw new UnauthorizedException();
        }
    }

    private String issueToken(String uriInfo, User user) {
        String jwtToken = Jwts.builder()
                .setIssuer(uriInfo)
                .setSubject(user.getUsername())
                .claim("user", user)
                .setIssuedAt(new Date())
                .setExpiration(new Date(LocalDateTime.now().plusMinutes(15L).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()))
                .signWith(SignatureAlgorithm.HS512, serverKey)
                .compact();
        return jwtToken;
    }

    public User getUser(Long id){
        return em.find(User.class, id);
    }


}
