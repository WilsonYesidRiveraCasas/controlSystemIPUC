
package com.ipuc.base.tipoIdentificacion;

import java.util.List;

/**
 *
 * @author wilson-rivera
 */
public interface TipoIdentificacionManager {
    
    public void create(TipoIdentificacion tipoIdentificacion);

    public void update(TipoIdentificacion tipoIdentificacion) throws Exception;

    public TipoIdentificacion find(String codTipoIdentificacion) throws Exception;

    public List<TipoIdentificacion> findAll() throws Exception;
}
