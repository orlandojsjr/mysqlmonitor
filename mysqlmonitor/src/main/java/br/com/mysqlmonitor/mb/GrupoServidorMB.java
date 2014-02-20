/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.mysqlmonitor.mb;

import com.mysqlmonitor.dao.GrupoServidorDAO;
import com.mysqlmonitor.entidade.GrupoServidor;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

/**
 *
 * @author Orlando
 */
@Model
public class GrupoServidorMB extends Face{
    @Inject
    private GrupoServidorDAO grupoServidorDAO;
    @Inject
    private GrupoServidor grupoServidor;

    public GrupoServidor getGrupoServidor() {
        return grupoServidor;
    }

    public void setGrupoServidor(GrupoServidor grupoServidor) {
        this.grupoServidor = grupoServidor;
    }
    
    public void salvar(){
        grupoServidorDAO.salvar(grupoServidor);
        addMensagem("Cadastro realizado com sucesso!", FacesMessage.SEVERITY_INFO);
    }
    
}
