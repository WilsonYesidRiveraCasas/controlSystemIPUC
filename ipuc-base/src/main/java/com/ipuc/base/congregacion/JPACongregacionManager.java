/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ipuc.base.congregacion;

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
public class JPACongregacionManager implements CongregacionManager {

    private static Logger log = LoggerFactory.getLogger(JPACongregacionManager.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void create(Congregacion congregacion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void update(Congregacion congregacion) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Congregacion find(String cod_congregacion) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Congregacion> findAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Transactional
    public long countCongregacion() throws Exception {
        try {
            String strQuery = "select count(c) from Congregacion c";
            Query query = entityManager.createQuery(strQuery);
            return (Long) query.getSingleResult();
        } catch(Exception e) {
            throw new Exception("Error count congregations. " + e.getMessage());
        }
    }
    
}
