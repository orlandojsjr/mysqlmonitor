/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mysqlmonitor.dao;

import com.mysqlmonitor.entidade.LogAgente;
import java.util.List;

/**
 *
 * @author orlando
 */
public class LogAgenteDAO extends GenericDAO {

    public void salvar(LogAgente logAgente) throws Exception {        
        super.salvar(logAgente);
    }
    
    public List<LogAgente> findAll() throws Exception {        
        return super.listarPorParametrosHQL(LogAgente.class, "Select l from LogAgente l order by l.idLogAgente desc",0,0);
    }
}
