/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ipuc.base.ceremonia;

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
public class JPACeremoniaManager implements CeremoniaManager{

    private static Logger log = LoggerFactory.getLogger(JPACeremoniaManager.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void create(Ceremonia ceremonia) throws Exception {
        try {
            entityManager.persist(ceremonia);
            entityManager.flush();
        } catch (Exception e) {
            log.error("Exception creating ceremonia", e);
            throw e;
        }
    }

    public void update(Ceremonia ceremonia) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Ceremonia find(String cod_congregacion) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Ceremonia> findAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
