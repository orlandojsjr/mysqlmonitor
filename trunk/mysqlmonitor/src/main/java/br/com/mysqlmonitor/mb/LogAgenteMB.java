/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mysqlmonitor.mb;

import br.com.mysqlmonitor.dao.LogAgenteDAO;
import br.com.mysqlmonitor.interceptador.Login;
import com.mysqlmonitor.entidade.LogAgente;
import java.util.List;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

/**
 *
 * @author orlando
 */
@Model
@Login
public class LogAgenteMB extends Face {

    @Inject
    private LogAgenteDAO logAgenteDAO;
    private List<LogAgente> listaLog;

    public List<LogAgente> getListaLog() {
        try {
            if (listaLog == null) {
                listaLog = logAgenteDAO.findAll();
            }
        } catch (Exception e) {
            addMensagem("Erro ao carregar logs.", FacesMessage.SEVERITY_ERROR);
        }
        return listaLog;
    }

    public void setListaLog(List<LogAgente> listaLog) {
        this.listaLog = listaLog;
    }

}
