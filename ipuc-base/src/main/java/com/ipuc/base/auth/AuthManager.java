
package com.ipuc.base.auth;

import java.util.List;

/**
 *
 * @author wilson-rivera
 */
public interface AuthManager {
    
    public void create(Auth auth) throws Exception;

    public void update(Auth auth) throws Exception;

    public Auth find(String num_identificacion) throws Exception;

    public List<Auth> findAll() throws Exception;
    
}
