/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeox.app.login.encoder.control;

import com.aeox.app.login.entity.User;
import com.aeox.app.login.encoder.boundary.PasswordEncoded;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pablolm
 */
@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
@PasswordEncoded
public class PasswordEncoder {

    @PersistenceContext
    private EntityManager em;
    
    public static String getSecurePassword(String passwordToHash, byte[] salt, String digestAlgorithm) {
        String generatedPassword = passwordToHash;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance(digestAlgorithm);
            //Add password bytes to digest
            md.update(salt);
            //Get the hash's bytes 
            byte[] bytes = md.digest(passwordToHash.getBytes());
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } 
        catch (NoSuchAlgorithmException e) {
            Logger.getLogger(PasswordEncoder.class.getName()).log(Level.SEVERE, null, e);
        }
        return generatedPassword;
    }
     
    //Add salt
    public static byte[] generateSalt(String secureRandomType, String secureRandomImpl){
        //Create array for salt
        byte[] salt = new byte[64];
        
        //Always use a SecureRandom generator
        SecureRandom sr;
        try {
            sr = SecureRandom.getInstance(secureRandomType, secureRandomImpl);
            //Get a random salt
            sr.nextBytes(salt);
        } catch (NoSuchAlgorithmException | NoSuchProviderException ex) {
            Logger.getLogger(PasswordEncoder.class.getName()).log(Level.SEVERE, null, ex);
        }
        //return salt
        return salt;
    }

    public byte[] getExistingUserSalt(String username){
        Query query = em.createNamedQuery(User.NAMED_GET_SALT_BY_USERNAME, byte[].class);
        query.setParameter("username", username);
        return (byte[]) query.getSingleResult();
    }
    
    @AroundInvoke
    public Object intercept(InvocationContext ctx) throws Exception{
        Method method = ctx.getMethod();
        PasswordEncoded userPasswordHashed = method.getDeclaredAnnotation(PasswordEncoded.class);
        
        if(userPasswordHashed == null)
            return ctx.proceed();
        
        final String digestAlgorithm = userPasswordHashed.digestAlgorithm();
        final String secureRandomType = userPasswordHashed.secureRandomType();
        final String secureRandomImpl = userPasswordHashed.secureRandomImpl();
        
        Arrays.asList(ctx.getParameters()).forEach((obj) -> {
                if(obj instanceof User){
                    User user = (User) obj;

                    try {
                        byte[] userSalt = this.getExistingUserSalt(user.getUsername());
                        user.setSalt(userSalt);
                    }catch (NoResultException ex){
                        user.setSalt(generateSalt(secureRandomType, secureRandomImpl));
                    }

                    user.setPassword(getSecurePassword(user.getPassword(), user.getSalt(), digestAlgorithm));
//                    user.setPassword(getSecurePassword(user.getPassword(), user.getUsername().getBytes(), digestAlgorithm));
                }
        });
        return ctx.proceed();
    }
}
