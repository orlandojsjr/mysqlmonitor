/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mysqlmonitor.mb;

import br.com.mysqlmonitor.Interceptor.Login;
import com.mysqlmonitor.dao.UsuarioDAO;
import com.mysqlmonitor.entidade.Usuario;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author orlando
 */
@Named
@Login
@RequestScoped
public class UsuarioMB extends Face implements Serializable {

    @Inject
    private UsuarioDAO usuarioDAO;
    @Inject
    private Usuario usuario;
    private String confirmaSenha;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getConfirmaSenha() {
        return confirmaSenha;
    }

    public void setConfirmaSenha(String confirmaSenha) {
        this.confirmaSenha = confirmaSenha;
    }

    public void salvar() {
        try {
            usuarioDAO.salvar(usuario);
            addMensagem("Cadastro realizado.", FacesMessage.SEVERITY_INFO);
        } catch (Exception ex) {
            addMensagem("Erro.", FacesMessage.SEVERITY_INFO);
            Logger.getLogger(UsuarioMB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
