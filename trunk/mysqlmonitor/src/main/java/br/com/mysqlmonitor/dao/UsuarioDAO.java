/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mysqlmonitor.dao;

import br.com.mysqlmonitor.interceptador.TransacaoJPA;
import com.mysqlmonitor.entidade.Usuario;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;

/**
 *
 * @author orlando
 */
@RequestScoped
@TransacaoJPA
public class UsuarioDAO extends GenericDAO {

    public UsuarioDAO() {
    }

    public UsuarioDAO(EntityManager em) {
        super(em);
    }

    public void salvar(Usuario usuario) throws Exception {
        usuario.setIdUsuario(null);
        super.salvar(usuario);
    }

    public void alterar(Usuario usuario) throws Exception {
        super.alterar(usuario);
    }

    public Usuario consultar(int id) throws Exception {
        return super.consultar(Usuario.class, id);
    }

    public List<Usuario> findAll() throws Exception {
        return super.listarPorParametrosHQL(Usuario.class, "Select u from Usuario u order by u.nome", 0, 0);
    }

    public Usuario logar(String login, String senha) throws Exception {
        List<Usuario> list = super.listarPorParametrosHQL(Usuario.class, "Select u from Usuario u Where u.login = :login and senha = :senha", 0, 0, new Parametro("login", login), new Parametro("senha", senha));
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }
}
