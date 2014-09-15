
package com.ipuc.base.region;

import java.util.List;

/**
 *
 * @author wilson-rivera
 */
public interface RegionManager {

    public Region find(int id_region) throws Exception;
    
    public List<Region> getRegionByPais(String codPais) throws Exception;
    
}
