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

    @Transactional
    public void create(Congregacion congregacion) throws Exception {
        try {
            entityManager.persist(congregacion);            
            entityManager.flush();
            System.out.println("cod: " + congregacion.getCodCongregacion());
        } catch (Exception e) {
            log.error("Exception creando congregación", e);
            throw e;
        }
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
    
    public Congregacion getCongregacionByPastor(String numIdentificacion) throws Exception {
        try {
            String jpa = " Select c from Congregacion c where c.pastor.numeroIdentificacion =:numIdentificacion";
            Query query = entityManager.createQuery(jpa);
            query.setParameter("numIdentificacion", numIdentificacion);
            List result = query.getResultList();
            
            if(result == null || result.isEmpty()) {
                return null;
            }
            
            return (Congregacion) result.get(0);
        } catch(Exception e) {
            throw new Exception("Error buscando la congregación administrada por el pastor " + numIdentificacion);
        }
    }
    
}
