
package com.ipuc.base.historialTarjeta;

import java.util.List;

/**
 *
 * @author wilson-rivera
 */
public interface HistorialTarjetaManager {

    public void create(HistorialTarjeta historial);

    public void update(HistorialTarjeta historial) throws Exception;

    public HistorialTarjeta find(String cod_congregacion) throws Exception;

    public List<HistorialTarjeta> findAll() throws Exception;
}
