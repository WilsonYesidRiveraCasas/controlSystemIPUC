
package com.ipuc.base.historialTarjeta;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author wilson-rivera
 */
public class JPAHistorialTarjetaManager implements HistorialTarjetaManager {

    private static Logger log = LoggerFactory.getLogger(JPAHistorialTarjetaManager.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void create(HistorialTarjeta historial) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void update(HistorialTarjeta historial) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public HistorialTarjeta find(String cod_congregacion) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<HistorialTarjeta> findAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
