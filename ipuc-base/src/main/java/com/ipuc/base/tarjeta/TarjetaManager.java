/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ipuc.base.tarjeta;

import java.util.List;

/**
 *
 * @author wilson-rivera
 */
public interface TarjetaManager {

    public void create(Tarjeta tarjeta);

    public void update(Tarjeta tarjeta) throws Exception;

    public Tarjeta find(String id_tarjeta) throws Exception;

    public List<Tarjeta> findAll() throws Exception;
}
