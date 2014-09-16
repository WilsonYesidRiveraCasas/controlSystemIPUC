
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
            String sql = " select * from region where pais =?1";
            Query query = entityManager.createNativeQuery(sql, Region.class);
            query.setParameter(1, codPais);
            return query.getResultList();
        } catch(Exception e) {
            throw new Exception("Error get congregations. " + e.getMessage());
        }
    }

    
}
