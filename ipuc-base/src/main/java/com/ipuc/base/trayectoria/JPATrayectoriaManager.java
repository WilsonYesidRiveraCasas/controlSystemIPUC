
package com.ipuc.base.trayectoria;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author wilson-rivera
 */
public class JPATrayectoriaManager implements TrayectoriaManager {

    private static Logger log = LoggerFactory.getLogger(JPATrayectoriaManager.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void create(Trayectoria trayectoria) throws Exception {
        try {
            entityManager.persist(trayectoria);
            entityManager.flush();
        } catch (Exception e) {
            log.error("Exception creando trayectoria", e);
            throw e;
        }
    }

    public void update(Trayectoria trayectoria) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Trayectoria find(String id_tarjeta) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Trayectoria> findAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
