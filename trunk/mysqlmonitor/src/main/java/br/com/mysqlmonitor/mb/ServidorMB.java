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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.model.SelectItem;
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
    private List<Servidor> servidores;
    private List<Servidor> servidoresFiltro;
    private List<SelectItem> grupoServidoresItem;
    private List<SelectItem> grupoServidoresItemFiltro;
    private boolean existeServidorMaster;
    private String tab;

    public Servidor getServidor() {
        return servidor;
    }

    public void setServidor(Servidor servidor) {
        this.servidor = servidor;
    }

    public boolean isExisteServidorMaster() {
        return existeServidorMaster;
    }

    public List<Servidor> getServidoresFiltro() {
        return servidoresFiltro;
    }

    public void setServidoresFiltro(List<Servidor> servidoresFiltro) {
        this.servidoresFiltro = servidoresFiltro;
    }

    public String getTab() {
        return tab;
    }

    public void setTab(String tab) {
        this.tab = tab;
    }

    public List<Servidor> getServidores() {
        try {
            if (servidores == null) {
                servidores = servidorDAO.findAll();
            }
        } catch (Exception ex) {
            addMensagem("Erro ao carregar Servidores!", FacesMessage.SEVERITY_ERROR);
        }
        return servidores;
    }

    public List<SelectItem> getGrupoServidoresItemFiltro() {
        try {
            if (grupoServidoresItemFiltro == null) {
                grupoServidoresItemFiltro = new ArrayList<SelectItem>();
                grupoServidoresItemFiltro.add(new SelectItem("", " -- Todos -- "));
                for (GrupoServidor grupoServidor : grupoServidorDAO.findAll()) {
                    grupoServidoresItemFiltro.add(new SelectItem(grupoServidor.getBancoDados()));
                }
            }
        } catch (Exception ex) {
            addMensagem("Erro ao carregar Grupo Servidores!", FacesMessage.SEVERITY_ERROR);
        }
        return grupoServidoresItemFiltro;
    }

    public List<SelectItem> getGrupoServidores() {
        try {
            if (grupoServidoresItem == null) {
                grupoServidoresItem = new ArrayList<SelectItem>();
                for (GrupoServidor grupoServidor : grupoServidorDAO.findAll()) {
                    grupoServidoresItem.add(new SelectItem(grupoServidor.getIdGrupoServidor(), grupoServidor.getBancoDados()));
                }
            }
        } catch (Exception ex) {
            addMensagem("Erro ao carregar Grupo Servidores!", FacesMessage.SEVERITY_ERROR);
        }
        return grupoServidoresItem;
    }

    public void salvar() {
        try {
            if (!verificarServidorMaster()) {
                servidor.setTipo("MASTER");
            }
            servidorDAO.salvar(servidor);
            addMensagem("Cadrastro realizado com sucesso!", FacesMessage.SEVERITY_INFO);
            servidor = new Servidor();
            tab = "1";
        } catch (Exception ex) {
            Logger.getLogger(ServidorMB.class.getName()).log(Level.SEVERE, null, ex);
            addMensagem("Erro ao cadastrar!", FacesMessage.SEVERITY_ERROR);
        }
    }

    public void alterar() {
        try {
            if (!verificarServidorMaster()) {
                servidor.setTipo("MASTER");
            }
            servidorDAO.alterar(servidor);
            addMensagem("Cadrastro alterado com sucesso!", FacesMessage.SEVERITY_INFO);
            servidor = new Servidor();
            tab = "0";
        } catch (Exception ex) {
            Logger.getLogger(ServidorMB.class.getName()).log(Level.SEVERE, null, ex);
            addMensagem("Erro ao cadastrar!", FacesMessage.SEVERITY_ERROR);
        }
    }
    
    public void excluir() {
        try {            
            servidorDAO.excluir(servidor);
            System.out.println("afdfds");
            servidor = new Servidor();            
        } catch (Exception ex) {
            Logger.getLogger(ServidorMB.class.getName()).log(Level.SEVERE, null, ex);
            addMensagem("Erro ao excluir!", FacesMessage.SEVERITY_ERROR);
        }
    }

    public boolean verificarServidorMaster() {
        try {
            existeServidorMaster = servidorDAO.existeServidorMasterNo(servidor.getGrupoServidor());
        } catch (Exception ex) {
            addMensagem("Erro!", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(ServidorMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return existeServidorMaster;
    }

    public void tabCadastro() {
        tab = "1";
    }

}
