
package com.ipuc.base.persona;

import java.util.List;

/**
 *
 * @author wilson-rivera
 */
public interface PersonaManager {

    public void create(Persona persona);

    public void update(Persona persona) throws Exception;

    public Persona find(String num_identificacion) throws Exception;

    public List<Persona> findAll() throws Exception;

}
