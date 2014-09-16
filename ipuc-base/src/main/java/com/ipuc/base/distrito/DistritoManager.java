
package com.ipuc.base.distrito;

import java.util.List;

/**
 *
 * @author wilson-rivera
 */
public interface DistritoManager {
    
    public void find(int id_distrito) throws Exception;
    
    public List<Distrito> findByCodPais(String codPais) throws Exception;
    
}
