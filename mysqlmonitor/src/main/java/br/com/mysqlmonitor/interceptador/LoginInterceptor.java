/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mysqlmonitor.interceptador;

import br.com.mysqlmonitor.mb.UsuarioLogadoMB;
import java.io.Serializable;
import javax.annotation.Priority;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 *
 * @author orlando
 */
@Interceptor
@Login @Priority(Interceptor.Priority.APPLICATION)
public class LoginInterceptor implements Serializable {

    @Inject
    private UsuarioLogadoMB usuarioLogadoMB;

    @AroundInvoke
    public Object test(InvocationContext ic) {        
        if (!usuarioLogadoMB.isLogado()) {            
            FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "/login.xhtml?faces-redirect=true");
        }
        try {
            return ic.proceed();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
