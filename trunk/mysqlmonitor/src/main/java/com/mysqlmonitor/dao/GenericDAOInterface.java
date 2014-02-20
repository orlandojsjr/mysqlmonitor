/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mysqlmonitor.dao;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author orlando
 */
public interface GenericDAOInterface {
    
    public void salvar(Serializable serializable) throws Exception;
    
    public <T extends Serializable> T consultar(Class<T> clazz, Serializable id) throws Exception;
    
    public void alterar(Serializable serializable) throws Exception;
    
    public <T extends Serializable> List<T> listarPorParametrosHQL(Class<T> clazz, String hql, int inicio, int limite, Parametro... parametros) throws Exception;
    
}
