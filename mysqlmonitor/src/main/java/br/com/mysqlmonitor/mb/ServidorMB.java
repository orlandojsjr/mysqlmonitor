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
    private List<SelectItem> grupoServidoresItem;   
    private boolean existeServidorMaster;

    public Servidor getServidor() {
        return servidor;
    }

    public void setServidor(Servidor servidor) {
        this.servidor = servidor;
    }

    public boolean isExisteServidorMaster() {
        return existeServidorMaster;
    }
    
    public List<SelectItem> getGrupoServidores() {
        try {
            if (grupoServidoresItem == null) {
                grupoServidoresItem = new ArrayList<SelectItem>();
                for (GrupoServidor grupoServidor : grupoServidorDAO.getListaGrupoServidor()) {
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
            servidorDAO.salvar(servidor);
            addMensagem("Cadrastro realizado com sucesso!", FacesMessage.SEVERITY_INFO);
        } catch (Exception ex) {
            Logger.getLogger(ServidorMB.class.getName()).log(Level.SEVERE, null, ex);
            addMensagem("Erro ao cadastrar!", FacesMessage.SEVERITY_ERROR);
        }        
    }
    
    public void verificarServidorMaster(){
        try {
            existeServidorMaster = servidorDAO.existeServidorMasterNo(servidor.getGrupoServidor());
            if(existeServidorMaster){
                servidor.setTipo("SLAVE");
            }
        } catch (Exception ex) {
            addMensagem("Erro!", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(ServidorMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
