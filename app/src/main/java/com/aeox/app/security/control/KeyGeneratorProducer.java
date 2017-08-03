package com.aeox.app.security.control;

import javax.annotation.PostConstruct;
import javax.crypto.KeyGenerator;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.security.Key;
import java.util.logging.Logger;

@ApplicationScoped
public class KeyGeneratorProducer {

    private static final Logger LOG = Logger.getLogger(KeyGeneratorProducer.class.getName());
    private Key key;


    @Produces
    public Key getServerKey(){
        if(key == null){
            KeyGenerator keyGenerator = null;
            try {
                keyGenerator = KeyGenerator.getInstance("AES");
                keyGenerator.init(256);
                key = keyGenerator.generateKey();
            }catch (Exception ex){
                LOG.severe(ex.getMessage());
            }
        }
        return key;
    }

}
