
package com.ipuc.base.tipoIdentificacion;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author wilson-rivera
 */
public class JPATipoIdentificacionManager implements TipoIdentificacionManager {
    
    private static Logger log = LoggerFactory.getLogger(JPATipoIdentificacionManager.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void create(TipoIdentificacion tipoIdentificacion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void update(TipoIdentificacion tipoIdentificacion) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public TipoIdentificacion find(String codTipoIdentificacion) throws Exception {
        try {
            return entityManager.find(TipoIdentificacion.class, codTipoIdentificacion);
        } catch (Exception e) {
            throw new Exception("Exception loading identification type with code : " + codTipoIdentificacion + ". Message: " + e.getMessage(), e);
        }
    }

    public List<TipoIdentificacion> findAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
