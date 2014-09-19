
package com.ipuc.base.trayectoria;

import java.util.List;

/**
 *
 * @author wilson-rivera
 */
public interface TrayectoriaManager {

    public void create(Trayectoria trayectoria)throws Exception;

    public void update(Trayectoria trayectoria) throws Exception;

    public Trayectoria find(String id_tarjeta) throws Exception;

    public List<Trayectoria> findAll() throws Exception;

}
