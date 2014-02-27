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
 * @author orlando
 */

@Login
@Model
public class UsuarioMB extends Face implements Serializable {

    @Inject
    private UsuarioDAO usuarioDAO;
    @Inject
    private Usuario usuario;
    private String confirmaSenha;
    private String tab;
    private List<Usuario> usuarios;
    private List<SelectItem> tipos;

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

    public void setTab(String tab) {
        this.tab = tab;
    }

    public String getTab() {
        return tab;
    }

    public List<Usuario> getUsuarios() {
        if (usuarios == null) {
            carregarListaUsuario();
        }
        return usuarios;
    }

    public List<SelectItem> getTipos() {
        if(tipos == null){
            tipos = new ArrayList<SelectItem>();
            tipos.add(new SelectItem("ADMIN"));
            tipos.add(new SelectItem("DBA"));
        }
        return tipos;
    }

    private void carregarListaUsuario() {
        try {
            usuarios = usuarioDAO.findAll();
        } catch (Exception ex) {
            addMensagem("Erro ao carregar lista Usuário.", FacesMessage.SEVERITY_WARN);
        }
    }
    
    public void novo(){
        usuario = new Usuario();        
    }

    public void salvar() {
        try {
            if (usuario.getIdUsuario() == null || usuario.getIdUsuario() == 0) {
                usuarioDAO.salvar(usuario);                
                addMensagem("Cadastro realizado.", FacesMessage.SEVERITY_INFO);
            } else {                
                usuarioDAO.alterar(usuario);
                addMensagem("Cadastro alterado.", FacesMessage.SEVERITY_INFO);
            }
            novo();
            carregarListaUsuario();            
        } catch (Exception ex) {
            addMensagem("Erro.", FacesMessage.SEVERITY_INFO);
            Logger.getLogger(UsuarioMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void excluir(Usuario u) {

    }

    public void tabCadastro() throws Exception {
        tab = "1";        
    }
}
