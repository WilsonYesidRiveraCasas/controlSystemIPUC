
package com.ipuc.base.congregacion;

import java.util.List;

/**
 *
 * @author wilson-rivera
 */
public interface CongregacionManager {

    public void create(Congregacion congregacion) throws Exception;

    public void update(Congregacion congregacion) throws Exception;

    public Congregacion find(String cod_congregacion) throws Exception;

    public List<Congregacion> findAll() throws Exception;
    
    public long countCongregacion() throws Exception;

}
