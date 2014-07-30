
package com.ipuc.base.region;

import java.util.List;

/**
 *
 * @author wilson-rivera
 */
public interface RegionManager {

    public void create(Region region);

    public void update(Region region) throws Exception;

    public Region find(int id_region) throws Exception;

    public List<Region> findAll() throws Exception;
    
}
