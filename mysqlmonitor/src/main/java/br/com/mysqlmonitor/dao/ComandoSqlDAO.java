/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.mysqlmonitor.dao;

import br.com.mysqlmonitor.interceptador.TransacaoJPA;
import com.mysqlmonitor.entidade.ComandoSql;
import java.util.List;

/**
 *
 * @author orlando
 */
@TransacaoJPA
public class ComandoSqlDAO extends GenericDAO{
    
    public void salvar(ComandoSql comandoSql) throws Exception{
        super.salvar(comandoSql);
    }
    
    public List<ComandoSql> findAll() throws Exception{
        return super.listarPorParametrosHQL(ComandoSql.class, "Select c from ComandoSql c order by c.idComandoSql desc", 0,0);
    }
}
