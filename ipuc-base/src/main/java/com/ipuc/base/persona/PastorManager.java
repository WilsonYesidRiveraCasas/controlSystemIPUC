
package com.ipuc.base.persona;

/**
 *
 * @author wilson-rivera
 */
public interface PastorManager {

    public void create(Pastor pastor) throws Exception;

    public void update(Pastor pastor) throws Exception;

    public Pastor find(String num_identificacion) throws Exception;

}
