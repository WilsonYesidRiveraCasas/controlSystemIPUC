
package com.ipuc.base.region;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author wilson-rivera
 */
public class JPARegionManager implements RegionManager {

    @PersistenceContext
    private EntityManager entityManager;

    public Region find(int id_region) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Region> getRegionByPais(String codPais) throws Exception {
        try {
            String strQuery = "select r from Region r where r.codigoPais := codPais";
            Query query = entityManager.createQuery(strQuery);
            query.setParameter("codPais", codPais);
            return query.getResultList();
        } catch(Exception e) {
            throw new Exception("Error get congregations. " + e.getMessage());
        }
    }

    
}
