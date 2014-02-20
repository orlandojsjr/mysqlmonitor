/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mysqlmonitor.dao;

import com.mysqlmonitor.entidade.GrupoServidor;
import java.util.List;

/**
 *
 * @author orlando
 */
public class GrupoServidorDAO extends GenericDAO{
    
    public void salvar(GrupoServidor grupoServidor) throws Exception{
        super.salvar(grupoServidor);
    }    
    
    public GrupoServidor consultar(int id) throws Exception{
        return super.consultar(GrupoServidor.class, id);
    }
    
    public List<GrupoServidor> getListaGrupoServidor() throws Exception{
        return super.listarPorParametrosHQL(GrupoServidor.class, "Select gs from GrupoServidor gs order by gs.bancoDados", 0, 0);
    }
}
