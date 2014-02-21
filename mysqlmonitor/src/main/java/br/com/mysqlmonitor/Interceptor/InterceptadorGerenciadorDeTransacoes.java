/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mysqlmonitor.Interceptor;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 *
 * @author orlando
 */
@Interceptor @TransacaoJPA @Priority(Interceptor.Priority.APPLICATION)
public class InterceptadorGerenciadorDeTransacoes {

    @Inject
    private EntityManager entityManager;

    @AroundInvoke
    public Object gerenciaTransacao(InvocationContext context) throws Exception {

        EntityTransaction tx = null;
        try {
            System.out.println("opa");
            tx = entityManager.getTransaction(); 
            tx.begin();
            Object retorno = context.proceed();
            tx.commit();
            return retorno;
        } catch (RuntimeException e) {
            if(tx != null) tx.rollback();
            throw e;
        }
        

    }
}
