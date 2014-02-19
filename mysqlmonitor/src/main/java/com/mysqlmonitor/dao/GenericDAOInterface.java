/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mysqlmonitor.dao;

import java.io.Serializable;

/**
 *
 * @author orlando
 */
public interface GenericDAOInterface {
    
    public void salvar(Serializable serializable);
    
    public <T extends Serializable> T consultar(Class<T> clazz, Serializable id) throws Exception;
    
    public void alterar(Serializable serializable);
    
    
    
}
