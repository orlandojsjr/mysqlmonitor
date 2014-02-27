/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mysqlmonitor.dao;

import br.com.mysqlmonitor.Interceptor.TransacaoJPA;
import com.mysqlmonitor.entidade.GrupoServidor;
import java.util.List;

/**
 *
 * @author orlando
 */
@TransacaoJPA
public class GrupoServidorDAO extends GenericDAO{
    
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
}
