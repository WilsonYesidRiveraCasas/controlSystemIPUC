
package com.ipuc.base.pais;

import java.util.List;

/**
 *
 * @author wilson-rivera
 */
public interface PaisManager {

    public void create(Pais pais);

    public void update(Pais pais) throws Exception;

    public Pais find(String cod_pais) throws Exception;

    public List<Pais> findAll() throws Exception;
    
}
