package com.aeox.app.security.control;

import javax.crypto.KeyGenerator;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.util.logging.Logger;

@ApplicationScoped
public class KeyGeneratorProducer {

    private static final Logger LOG = Logger.getLogger(KeyGeneratorProducer.class.getName());
    private KeyGenerator keyGenerator;


    @Produces
    public KeyGenerator getKeyGenerator(){
        if(keyGenerator == null)
            try {
                keyGenerator = KeyGenerator.getInstance("AES");
                keyGenerator.init(256);
            }catch (Exception ex){
                LOG.severe(ex.getMessage());
            }
        return keyGenerator;
    }

}
