
package com.ipuc.base.persona;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author wilson-rivera
 */
public class JPAPersonaManager implements PersonaManager {

    private static Logger log = LoggerFactory.getLogger(JPAPersonaManager.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void create(Persona persona) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void update(Persona persona) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Persona find(String num_identificacion) throws Exception {
        try {
            return entityManager.find(Persona.class, num_identificacion);
        } catch (Exception e) {
            throw new Exception("Exception loading person with identification : " + num_identificacion + ". Message: " + e.getMessage(), e);
        }
    }

    public List<Persona> findAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
