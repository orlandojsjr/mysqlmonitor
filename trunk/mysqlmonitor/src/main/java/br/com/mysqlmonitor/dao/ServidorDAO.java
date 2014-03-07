/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mysqlmonitor.dao;

import br.com.mysqlmonitor.interceptor.TransacaoJPA;
import com.mysqlmonitor.entidade.GrupoServidor;
import com.mysqlmonitor.entidade.Servidor;
import java.util.List;

/**
 *
 * @author orlando
 */
@TransacaoJPA
public class ServidorDAO extends GenericDAO {

    public void salvar(Servidor servidor) throws Exception {
        servidor.setIdServidor(null);
        super.salvar(servidor);
    }
    
    public void alterar(Servidor servidor) throws Exception {
        super.alterar(servidor);
    }
    
    public void excluir(Servidor servidor) throws Exception {
        super.excluir(servidor);
    }

    public List<Servidor> findAll() throws Exception {
        return super.listarPorParametrosHQL(Servidor.class, "Select s from Servidor s order by s.grupoServidor.bancoDados", 0, 0);
    }

    public boolean existeServidorMasterNo(GrupoServidor grupoServidor) throws Exception {
        return !super.listarPorParametrosHQL(Servidor.class, "Select s from Servidor s where s.grupoServidor.idGrupoServidor = :idGrupoServidor and s.tipo = 'MASTER'", 0, 0, new Parametro("idGrupoServidor", grupoServidor.getIdGrupoServidor())).isEmpty();
    }
}
