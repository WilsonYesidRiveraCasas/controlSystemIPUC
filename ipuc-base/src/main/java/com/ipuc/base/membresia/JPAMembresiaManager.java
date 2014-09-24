
package com.ipuc.base.membresia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author wilson-rivera
 */
public class JPAMembresiaManager implements MembresiaManager {

    private static Logger log = LoggerFactory.getLogger(JPAMembresiaManager.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void create(Membresia membresia) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void update(Membresia membresia) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Membresia find(String cod_congregacion) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Membresia> findAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public long getMembresia(int codCongregacion) throws Exception {
        try {
            String strQuery = "select count(m) from Membresia m where m.congregacion.codCongregacion =:codCongregacion";
            Query query = entityManager.createQuery(strQuery);
            query.setParameter("codCongregacion", codCongregacion);
            return (Long) query.getSingleResult();
        } catch(Exception e) {
            throw new Exception("Error count membresia. " + e.getMessage());
        }
    }
    
}
