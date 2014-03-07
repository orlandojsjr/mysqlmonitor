/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mysqlmonitor.dao;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author orlando
 */
public class JPAUtil {

    @Produces
    @ApplicationScoped
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

    @Produces
    @RequestScoped
    public EntityManager getEntityManager() {        
        EntityManager em = emf.createEntityManager();        
        return em;
    }

    public void finaliza(@Disposes EntityManager manager) {
        emf.close();
    }
}
