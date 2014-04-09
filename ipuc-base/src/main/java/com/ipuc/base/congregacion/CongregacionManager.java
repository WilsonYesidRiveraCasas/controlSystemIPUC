/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ipuc.base.congregacion;

import java.util.List;

/**
 *
 * @author wilson-rivera
 */
public interface CongregacionManager {

    public void create(Congregacion congregacion);

    public void update(Congregacion congregacion) throws Exception;

    public Congregacion find(String cod_congregacion) throws Exception;

    public List<Congregacion> findAll() throws Exception;

}
