/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mysqlmonitor.dao;

import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 * @author orlando
 */

public class GenericDAO {

    @Inject
    private EntityManager em;

    protected void salvar(Serializable object) throws Exception {        
        em.persist(object);        
    }

    protected <T extends Serializable> T consultar(Class<T> clazz, Serializable id) throws Exception {
        T retorno = em.find(clazz, id);
        return retorno;
    }

    protected void alterar(Serializable serializable) throws Exception {
        em.merge(serializable);
    }

    protected <T extends Serializable> List<T> listarPorParametrosHQL(Class<T> clazz, String hql, int inicio, int limite, Parametro... parametros) throws Exception {
        javax.persistence.Query query = em.createQuery(hql);
        if (parametros != null) {
            for (Parametro p : parametros) {
                query.setParameter(p.getNomeParametro(), p.getParametro());
            }
        }
        if (inicio != 0) {
            query.setFirstResult(inicio);
        }
        if (limite != 0) {
            query.setMaxResults(limite);
        }
        return (List<T>) query.getResultList();
    }
}
