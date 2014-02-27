/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mysqlmonitor.mb;

import br.com.mysqlmonitor.Interceptor.Login;
import com.mysqlmonitor.dao.GrupoServidorDAO;
import com.mysqlmonitor.entidade.GrupoServidor;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

/**
 *
 * @author Orlando
 */
@Login
@Model
public class GrupoServidorMB extends Face {

    @Inject
    private GrupoServidorDAO grupoServidorDAO;
    @Inject
    private GrupoServidor grupoServidor;
    private List<GrupoServidor> grupoServidores;
    private String tab;

    public GrupoServidor getGrupoServidor() {
        return grupoServidor;
    }

    public void setGrupoServidor(GrupoServidor grupoServidor) {
        this.grupoServidor = grupoServidor;
    }

    public List<GrupoServidor> getGrupoServidores() {
        if (grupoServidores == null) {
            carregarGrupoServidores();
        }
        return grupoServidores;
    }

    public void setGrupoServidores(List<GrupoServidor> grupoServidores) {
        this.grupoServidores = grupoServidores;
    }

    public String getTab() {
        return tab;
    }

    public void setTab(String tab) {
        this.tab = tab;
    }

    private void carregarGrupoServidores() {
        try {
            grupoServidores = grupoServidorDAO.findAll();
        } catch (Exception e) {
            addMensagem("Erro ao carregar", FacesMessage.SEVERITY_ERROR);
        }
    }

    public void salvar() {
        try {
            if (grupoServidor.getIdGrupoServidor() == null || grupoServidor.getIdGrupoServidor() == 0) {
                grupoServidorDAO.salvar(grupoServidor);
                addMensagem("Cadastro realizado com sucesso!", FacesMessage.SEVERITY_INFO);
            } else {
                grupoServidorDAO.alterar(grupoServidor);
                addMensagem("Cadastro alterado com sucesso!", FacesMessage.SEVERITY_INFO);
            }
            carregarGrupoServidores();
            grupoServidor = new GrupoServidor();
        } catch (Exception ex) {
            addMensagem("Erro ao cadastrar!", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(GrupoServidorMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void excluir(GrupoServidor g){
        
    }
    
    public void tabCadastro() {
        tab = "1";
    }
}
