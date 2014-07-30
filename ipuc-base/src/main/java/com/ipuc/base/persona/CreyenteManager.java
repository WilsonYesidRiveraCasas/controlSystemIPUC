/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ipuc.base.persona;

import java.util.List;

/**
 *
 * @author wilson-rivera
 */
public interface CreyenteManager {

    public void create(Creyente creyente);

    public void update(Creyente creyente) throws Exception;

    public Creyente find(String num_identificacion) throws Exception;

    public List<Creyente> findAll() throws Exception;

}
