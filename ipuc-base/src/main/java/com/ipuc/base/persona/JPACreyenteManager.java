/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ipuc.base.persona;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author wilson-rivera
 */
public class JPACreyenteManager implements CreyenteManager {

    private static Logger log = LoggerFactory.getLogger(JPACreyenteManager.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void create(Creyente creyente) throws Exception {
        try {
            entityManager.persist(creyente);
            entityManager.flush();
        } catch (Exception e) {
            log.error("Exception creating creyente", e);
            throw e;
        }
    }

    public void update(Creyente creyente) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Creyente find(String num_identificacion) throws Exception {
        try {
            return entityManager.find(Creyente.class, num_identificacion);
        } catch (Exception e) {
            throw new Exception("Exception loading creyente with identification : " + num_identificacion + ". Message: " + e.getMessage(), e);
        }
    }

    public List<Creyente> findAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public long countCreyentes() throws Exception {
        try {
            String strQuery = "select count(c) from Creyente c";
            Query query = entityManager.createQuery(strQuery);
            return (Long) query.getSingleResult();
        } catch(Exception e) {
            throw new Exception("Error count creyentes. " + e.getMessage());
        }
    }
    
}
