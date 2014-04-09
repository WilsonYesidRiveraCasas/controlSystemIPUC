
package com.ipuc.base.ceremonia;

import java.util.List;

/**
 *
 * @author wilson-rivera
 */
public interface CeremoniaManager {

    public void create(Ceremonia ceremonia);

    public void update(Ceremonia ceremonia) throws Exception;

    public Ceremonia find(String cod_congregacion) throws Exception;

    public List<Ceremonia> findAll() throws Exception;

}
