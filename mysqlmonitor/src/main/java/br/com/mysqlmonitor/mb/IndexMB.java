/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mysqlmonitor.mb;

import br.com.mysqlmonitor.interceptador.Login;
import br.com.mysqlmonitor.quartz.ScheduleJob;
import com.mysqlmonitor.entidade.Usuario;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

/**
 *
 * @author orlando
 */
@Model
@Login
public class IndexMB {

    @Inject
    private Usuario usuario;   

    public Usuario getUsuario() {
        return usuario;
    }

    public void iniciarAgenteVerificadorCatalogos() {        
        ScheduleJob.setRodarAgente(Boolean.TRUE);
        System.out.println("Iniciando agente...");
    }

    public void pararAgenteVerificadorCatalogos() {        
        ScheduleJob.setRodarAgente(Boolean.FALSE);
        System.out.println("Parando agente...");
    }
    
    public Boolean liberarBotao(){
        return ScheduleJob.isRodarAgente();
    }
}
