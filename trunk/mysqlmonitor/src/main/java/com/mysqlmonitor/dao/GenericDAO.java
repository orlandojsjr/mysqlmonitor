/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mysqlmonitor.dao;

import java.io.Serializable;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 * @author orlando
 */
public class GenericDAO{

    @Inject private EntityManager em;

    protected void salvar(Serializable object) {
        em.getTransaction().begin();
        em.persist(object);
        em.getTransaction().commit();        
    }

    protected <T extends Serializable> T consultar(Class<T> clazz, Serializable id) throws Exception {        
        T retorno = em.find(clazz, id);        
        return retorno;
    }
    
    protected void alterar(Serializable serializable) {        
        em.merge(serializable);        
    }
}
