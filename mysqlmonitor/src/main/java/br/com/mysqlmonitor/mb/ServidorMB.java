/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mysqlmonitor.mb;

import com.mysqlmonitor.dao.GrupoServidorDAO;
import com.mysqlmonitor.dao.ServidorDAO;
import com.mysqlmonitor.entidade.GrupoServidor;
import com.mysqlmonitor.entidade.Servidor;
import java.util.List;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

/**
 *
 * @author Orlando
 */
@Model
public class ServidorMB extends Face {

    @Inject
    private ServidorDAO servidorDAO;
    @Inject
    private Servidor servidor;
    @Inject
    private GrupoServidorDAO grupoServidorDAO;
    private List<GrupoServidor> grupoServidores;

    public Servidor getServidor() {
        return servidor;
    }

    public void setServidor(Servidor servidor) {
        this.servidor = servidor;
    }

    public List<GrupoServidor> getGrupoServidores() {
        if(grupoServidores == null){
            //grupoServidores = grupoServidorDAO.get
        }
        return grupoServidores;
    }
    
    public void salvar() {
        servidorDAO.salvar(servidor);
        addMensagem("Cadrastro realizado com sucesso!", FacesMessage.SEVERITY_INFO);
    }

}
