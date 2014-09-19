
package com.ipuc.base.persona;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
    
    public long countPastores() throws Exception {
        try {
            String strQuery = "select count(p) from Pastor p";
            Query query = entityManager.createQuery(strQuery);
            return (Long) query.getSingleResult();
        } catch(Exception e) {
            throw new Exception("Error count pastores. " + e.getMessage());
        }
    }

    public List<Pastor> getPastoresDelDistritoSinCongregacion() throws Exception {
        try {
            String sql = " select * from pastor where numero_identificacion not in (select pastor from trayectoria)";
            Query query = entityManager.createNativeQuery(sql, Pastor.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new Exception("Error consultando los pastores sin congregación. " + e.getMessage());
        }
    }
  
}
