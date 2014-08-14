
package com.ipuc.base.persona;

import com.ipuc.base.pais.Pais;
import java.util.List;

/**
 *
 * @author wilson-rivera
 */
public interface PastorManager {

    public void create(Pastor pastor) throws Exception;

    public void update(Pastor pastor) throws Exception;

    public Pastor find(String num_identificacion) throws Exception;
    
    public List<Pais> getPaises() throws Exception;

}
