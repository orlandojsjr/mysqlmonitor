/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mysqlmonitor.quartz;

import br.com.mysqlmonitor.dao.GrupoServidorDAO;
import br.com.mysqlmonitor.dao.JPAUtil;
import br.com.mysqlmonitor.mb.IndexMB;
import br.com.mysqlmonitor.monitor.Monitor;
import com.mysqlmonitor.entidade.GrupoServidor;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author orlando
 */
public class ScheduleJob implements Job {

    private static Boolean rodarAgente = false;
    
    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        if (rodarAgente) {
            comparar();
        }
    }

    private void comparar() {
        try {
            EntityManager em = new JPAUtil().getEntityManager();
            GrupoServidorDAO grupoServidorDAO = new GrupoServidorDAO(em);
            List<GrupoServidor> grupoServidores = grupoServidorDAO.findAll();
            for (GrupoServidor grupoServidor : grupoServidores) {
                new Monitor().excutarConferencia(grupoServidor);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(ScheduleJob.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Boolean isRodarAgente() {
        return rodarAgente;
    }
    
    public static void setRodarAgente(Boolean rodarAgente) {
        ScheduleJob.rodarAgente = rodarAgente;
    }
}
