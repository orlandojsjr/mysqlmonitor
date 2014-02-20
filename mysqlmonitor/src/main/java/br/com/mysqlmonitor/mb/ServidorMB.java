/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.mysqlmonitor.mb;

import com.mysqlmonitor.dao.ServidorDAO;
import com.mysqlmonitor.entidade.Servidor;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

/**
 *
 * @author Orlando
 */
@Model
public class ServidorMB extends Face{
    @Inject
    private ServidorDAO servidorDAO;
    @Inject
    private Servidor servidor;

    public Servidor getServidor() {
        return servidor;
    }

    public void setServidor(Servidor servidor) {
        this.servidor = servidor;
    }
    
    public void salvar(){
        servidorDAO.salvar(servidor);
        addMensagem("Cadrastro realizado com sucesso!", FacesMessage.SEVERITY_INFO);
    }
    
}
