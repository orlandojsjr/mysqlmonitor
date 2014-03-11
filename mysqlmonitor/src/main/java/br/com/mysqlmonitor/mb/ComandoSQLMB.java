/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mysqlmonitor.mb;

import br.com.mysqlmonitor.dao.ComandoSqlDAO;
import br.com.mysqlmonitor.dao.GrupoServidorDAO;
import br.com.mysqlmonitor.dao.ServidorDAO;
import br.com.mysqlmonitor.interceptador.Login;
import com.mysqlmonitor.entidade.ComandoSql;
import com.mysqlmonitor.entidade.GrupoServidor;
import com.mysqlmonitor.entidade.Servidor;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

/**
 *
 * @author orlando
 */
@Login
@Model
public class ComandoSQLMB extends Face {

    private String query;
    private List<SelectItem> grupoServidoresItem;
    @Inject
    private GrupoServidor grupoServidor;
    @Inject
    private GrupoServidorDAO grupoServidorDAO;
    @Inject
    private ServidorDAO servidorDAO;
    @Inject
    private ComandoSqlDAO comandoSqlDAO;
    @Inject
    private UsuarioLogadoMB usuarioLogadoMB;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public GrupoServidor getGrupoServidor() {
        return grupoServidor;
    }

    public void setGrupoServidor(GrupoServidor grupoServidor) {
        this.grupoServidor = grupoServidor;
    }

    public List<SelectItem> getGrupoServidoresItem() {
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

    public void setGrupoServidoresItem(List<SelectItem> grupoServidoresItem) {
        this.grupoServidoresItem = grupoServidoresItem;
    }

    public void executar() {
        try {
            for (Servidor servidor : servidorDAO.findAll(grupoServidor)) {
                try {
                    servidorDAO.executarQueryUpdate(servidor, query);
                    comandoSqlDAO.salvar(new ComandoSql(query, "SUCESSO",usuarioLogadoMB.getUsuario(), grupoServidor));
                    addMensagem("Servidor " + servidor.getTipo() + " " + servidor.getIp() + " atualizado com sucesso!", FacesMessage.SEVERITY_INFO);
                } catch (Exception ex) {
                    comandoSqlDAO.salvar(new ComandoSql(query, "ERRO",usuarioLogadoMB.getUsuario(), grupoServidor));
                    addMensagem("Erro ao  atualizar servidor " + servidor.getIp() + "!", FacesMessage.SEVERITY_ERROR);
                    ex.printStackTrace();
                }
            }
        } catch (Exception ex) {
            addMensagem("Erro ao consultar servidores!", FacesMessage.SEVERITY_ERROR);
        }
    }
}
