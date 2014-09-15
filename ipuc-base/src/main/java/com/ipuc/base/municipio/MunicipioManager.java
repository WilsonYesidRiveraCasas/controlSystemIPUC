
package com.ipuc.base.municipio;

import java.util.List;

/**
 *
 * @author wilson-rivera
 */
public interface MunicipioManager {

    public Municipio find(int id_municipio) throws Exception;
    
    public List<Municipio> getMunicipioByCodRegion(int idRegion) throws Exception;

}
