/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mysqlmonitor.dao;

import com.mysqlmonitor.entidade.GrupoServidor;
import com.mysqlmonitor.entidade.Servidor;
import java.util.List;

/**
 *
 * @author orlando
 */
public class ServidorDAO extends GenericDAO {

    public void salvar(Servidor servidor) throws Exception {
        super.salvar(servidor);
    }

    public List<Servidor> findAll() throws Exception {
        return super.listarPorParametrosHQL(Servidor.class, "Select s from Servidor s order by s.grupoServidor.bancoDados", 0, 0);
    }

    public boolean existeServidorMasterNo(GrupoServidor grupoServidor) throws Exception {
        return !super.listarPorParametrosHQL(Servidor.class, "Select s from Servidor s where s.grupoServidor.idGrupoServidor = :idGrupoServidor and s.tipo = 'MASTER'", 0, 0, new Parametro("idGrupoServidor", grupoServidor.getIdGrupoServidor())).isEmpty();
    }
}
