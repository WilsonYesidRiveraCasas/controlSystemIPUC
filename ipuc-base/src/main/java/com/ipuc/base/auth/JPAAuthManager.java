
package com.ipuc.base.auth;

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
public class JPAAuthManager implements AuthManager {

    private static Logger log = LoggerFactory.getLogger(JPAAuthManager.class);

    @PersistenceContext
    private EntityManager entityManager;
    
    @Transactional
    public void create(Auth auth) throws Exception {
        try {
            entityManager.persist(auth);
            entityManager.flush();
        } catch (Exception e) {
            log.error("Exception creating auth", e);
            throw e;
        }        
    }

    public void update(Auth auth) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Auth find(String num_identificacion) throws Exception {
        try {
            return entityManager.find(Auth.class, num_identificacion);
        } catch (Exception e) {
            throw new Exception("Exception loading auth with identification : " + num_identificacion + ". Message: " + e.getMessage(), e);
        }
    }

    public List<Auth> findAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Auth findBySession(String n_identification, String session_id) throws Exception {
        try {
            String sql = "select a from Auth a where a.numeroIdentificacion =:n_identification and a.idSesion =:session_id";
            Query query = entityManager.createQuery(sql);
            query.setParameter("n_identification", n_identification);
            query.setParameter("session_id", session_id);
            
            List<Auth> auth = (List<Auth>) query.getResultList();
            
            if(auth == null || auth.isEmpty()) {
                return null;
            }
            
            return auth.get(0);
        } catch(Exception e) {
            throw new Exception("Error loading auth by session. Message: " + e.getMessage(), e);
        }
        
    }
    
}
