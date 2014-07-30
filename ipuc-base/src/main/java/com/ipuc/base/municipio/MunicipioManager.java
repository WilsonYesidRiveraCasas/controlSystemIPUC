
package com.ipuc.base.municipio;

import java.util.List;

/**
 *
 * @author wilson-rivera
 */
public interface MunicipioManager {

    public void create(Municipio municipio);

    public void update(Municipio municipio) throws Exception;

    public Municipio find(int id_municipio) throws Exception;

    public List<Municipio> findAll() throws Exception;

}
