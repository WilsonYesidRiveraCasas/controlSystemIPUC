
package com.ipuc.base.pastor;

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
