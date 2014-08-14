
package com.ipuc.base.persona;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author wilson-rivera
 */
public class JPAPastorManager implements PastorManager {

    private static Logger log = LoggerFactory.getLogger(JPAPastorManager.class);

    @PersistenceContext
    private EntityManager entityManager;
    
    @Transactional
    public void create(Pastor pastor) throws Exception {
        try {
            entityManager.persist(pastor);
            entityManager.flush();
        } catch (Exception e) {
            log.error("Exception creating pastor", e);
            throw e;
        }
    }

    public void update(Pastor pastor) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Pastor find(String num_identificacion) throws Exception {
        try {
            return entityManager.find(Pastor.class, num_identificacion);
        } catch (Exception e) {
            throw new Exception("Exception loading pastor with identification : " + num_identificacion + ". Message: " + e.getMessage(), e);
        }
    }

  
}
