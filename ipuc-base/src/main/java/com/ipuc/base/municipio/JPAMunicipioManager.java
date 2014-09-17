
package com.ipuc.base.municipio;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author wilson-rivera
 */
public class JPAMunicipioManager implements MunicipioManager {
    
    @PersistenceContext
    private EntityManager entityManager;

    public Municipio find(int id_municipio) throws Exception {
        try {
            String sql = " select * from municipio where id_municipio =?1";
            Query query = entityManager.createNativeQuery(sql, Municipio.class);
            query.setParameter(1, id_municipio);
            return (Municipio) query.getSingleResult();
        } catch(Exception e) {
            throw new Exception("Error get Municipio con id " + id_municipio + ". " + e.getMessage());
        }
    }

    public List<Municipio> getMunicipioByCodRegion(int idRegion) throws Exception {
        try {
            String sql = "select * from municipio where region = ?1";
            Query query = entityManager.createNativeQuery(sql, Municipio.class);
            query.setParameter(1, idRegion);
            return query.getResultList();
        } catch(Exception e) {
            throw new Exception("Error get municipios by region " + idRegion + ". " + e.getMessage());
        }
    }


    
}
