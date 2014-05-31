/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mysqlmonitor.mb;

import br.com.mysqlmonitor.dao.UsuarioDAO;
import com.mysqlmonitor.entidade.Usuario;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author orlando
 */
@Named
@SessionScoped
public class UsuarioLogadoMB extends Face implements Serializable {

    @Inject
    private UsuarioDAO usuarioDAO;
    private Usuario usuario;
    private String login;
    private String senha;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Boolean isLogado() {
        return usuario != null;
    }

    public Boolean isAdmin() {
        return usuario != null && usuario.getTipo().equals("ADMIN");
    }

    public String logar() {
        try {
            usuario = usuarioDAO.logar(login, senha);
            if (isLogado()) {
                return "index";
            }
        } catch (Exception ex) {
            Logger.getLogger(UsuarioLogadoMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        addMensagem("Login ou senha inválidos!", FacesMessage.SEVERITY_WARN);
        return "login";
    }

    public String logOut() {
        usuario = null;
        return "login";
    }
}
