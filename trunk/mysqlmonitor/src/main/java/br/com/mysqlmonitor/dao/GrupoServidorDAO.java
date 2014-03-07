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
import javax.persistence.EntityManager;

/**
 *
 * @author orlando
 */
@TransacaoJPA
public class GrupoServidorDAO extends GenericDAO{

    public GrupoServidorDAO() {
    }

    public GrupoServidorDAO(EntityManager em) {
        super(em);
    }
    
    public void salvar(GrupoServidor grupoServidor) throws Exception{
        grupoServidor.setIdGrupoServidor(null);
        super.salvar(grupoServidor);
    }  
    
    public void alterar(GrupoServidor grupoServidor) throws Exception{
        super.alterar(grupoServidor);
    } 
    
    public GrupoServidor consultar(int id) throws Exception{
        return super.consultar(GrupoServidor.class, id);
    }
    
    public List<GrupoServidor> findAll() throws Exception{
        return super.listarPorParametrosHQL(GrupoServidor.class, "Select gs from GrupoServidor gs order by gs.bancoDados", 0, 0);
    }
    
    public Servidor findMaster(GrupoServidor grupoServidor) throws Exception{
        List<Servidor> list = super.listarPorParametrosHQL(Servidor.class, "Select s from Servidor s Where s.grupoServidor.idGrupoServidor = :id and s.tipo = 'MASTER'", 0, 0, new Parametro("id", grupoServidor.getIdGrupoServidor()));
        if(!list.isEmpty()){
            return list.get(0);
        }
        return null;
    }
    
    public List<Servidor> findSlave(GrupoServidor grupoServidor) throws Exception{
        return super.listarPorParametrosHQL(Servidor.class, "Select s from Servidor s Where s.grupoServidor.idGrupoServidor = :id and s.tipo = 'SLAVE'", 0, 0, new Parametro("id", grupoServidor.getIdGrupoServidor()));
    }
}
