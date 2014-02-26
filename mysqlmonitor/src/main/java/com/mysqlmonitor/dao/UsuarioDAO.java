/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mysqlmonitor.dao;

import com.mysqlmonitor.entidade.Usuario;
import java.util.List;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author orlando
 */
@RequestScoped
public class UsuarioDAO extends GenericDAO {

    public void salvar(Usuario usuario) throws Exception {
        super.salvar(usuario);
    }

    public Usuario logar(String login, String senha) throws Exception {
        List<Usuario> list = super.listarPorParametrosHQL(Usuario.class, "Select u from Usuario u Where u.login = :login and senha = :senha", 0, 0, new Parametro("login", login), new Parametro("senha", senha));        
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }
}
