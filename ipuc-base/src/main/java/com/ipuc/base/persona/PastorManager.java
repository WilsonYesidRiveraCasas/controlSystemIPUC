
package com.ipuc.base.persona;

import java.util.List;

/**
 *
 * @author wilson-rivera
 */
public interface PastorManager {

    public void create(Pastor pastor);

    public void update(Pastor pastor) throws Exception;

    public Pastor find(String cod_congregacion) throws Exception;

    public List<Pastor> findAll() throws Exception;
}
