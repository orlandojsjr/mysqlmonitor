/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mysqlmonitor.monitor;

import br.com.mysqlmonitor.dao.ConexaoJDBC;
import br.com.mysqlmonitor.dao.GrupoServidorDAO;
import br.com.mysqlmonitor.dao.JPAUtil;
import br.com.mysqlmonitor.dao.LogAgenteDAO;
import com.mysqlmonitor.entidade.GrupoServidor;
import com.mysqlmonitor.entidade.LogAgente;
import com.mysqlmonitor.entidade.Servidor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

/**
 *
 * @author orlando
 */
public class Monitor {

    private GrupoServidorDAO grupoServidorDAO;
    private LogAgenteDAO logAgenteDAO;
    private ConexaoJDBC conexaoJDBC = new ConexaoJDBC();

    {
        EntityManager em = new JPAUtil().getEntityManager();
        grupoServidorDAO = new GrupoServidorDAO(em);
        logAgenteDAO = new LogAgenteDAO(em);
        System.out.println("aqui");
    }

    public void excutarConferencia(GrupoServidor grupoServidor) {
        try {
            System.out.println(grupoServidor);
            Servidor servidorMaster = grupoServidorDAO.findMaster(grupoServidor);
            List<Servidor> servidoresSlaves = grupoServidorDAO.findSlave(grupoServidor);
            List<Tabela> tabelasServidorMaster = carregarTabelas(servidorMaster);
            
            for (Servidor servidorSlave : servidoresSlaves) {
                
                List<Tabela> tabelasServidorSlave = carregarTabelas(servidorSlave);
                comparar(tabelasServidorMaster, tabelasServidorSlave);
            }
        } catch (Exception ex) {
            Logger.getLogger(Monitor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private List<Tabela> carregarTabelas(Servidor servidor) {        
        List<Tabela> listaTabela = new ArrayList<Tabela>();
        try {
            StringBuilder query = new StringBuilder("SHOW TABLES");
            Connection con = conexaoJDBC.iniciarConexao(servidor);
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(query.toString());
            while (rs.next()) {

                Tabela tabela = new Tabela(rs.getString("Tables_in_" + servidor.getGrupoServidor().getBancoDados()));
                tabela.setCampos(carregarCamposTabela(servidor, new Tabela(rs.getString("Tables_in_" + servidor.getGrupoServidor().getBancoDados()))));
                System.out.println(tabela);
                listaTabela.add(tabela);
            }
            stm.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Monitor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaTabela;
    }

    private List<Campo> carregarCamposTabela(Servidor servidor, Tabela tabela) {
        List<Campo> listaCampo = new ArrayList<Campo>();
        try {
            StringBuilder query = new StringBuilder("DESC ").append(tabela.getNomeTabela());
            Connection con = conexaoJDBC.iniciarConexao(servidor);
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(query.toString());
            while (rs.next()) {
                Campo campo = new Campo();
                campo.setNome(rs.getString("Field"));
                campo.setTipo(rs.getString("Type"));
                campo.setPermiteNull(rs.getString("Null"));
                campo.setKey(rs.getString("Key"));
                campo.setValorDefault(rs.getString("Default"));
                campo.setExtra(rs.getString("Extra"));
                listaCampo.add(campo);
            }
            stm.close();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listaCampo;
    }

    private void comparar(List<Tabela> tabelasServidorMaster, List<Tabela> tabelasServidorSlave) {
        Tabela master;
        Tabela slave;
        Campo campoMaster;
        Campo campoSlave;

        for (int i = 0; i < tabelasServidorMaster.size(); i++) {
            master = tabelasServidorMaster.get(i);
            slave = tabelasServidorSlave.get(i);

            if (!master.getNomeTabela().equals(slave.getNomeTabela())) {
                adicionarLog("Tabela com NOME diferente: Master(" + master.getNomeTabela() + "), Slave(" + slave.getNomeTabela() + ")");
            }

            for (int j = 0; j < master.getCampos().size(); j++) {
                campoMaster = master.getCampos().get(j);
                campoSlave = slave.getCampos().get(j);

                if (!campoMaster.getNome().equals(campoSlave.getNome())) {
                    adicionarLog("Tabela (" + master.getNomeTabela() + " com CAMPO diferente: Master(" + campoMaster.getNome() + "), Slave(" + campoSlave.getNome() + ")");
                }
                if (!campoMaster.getTipo().equals(campoSlave.getTipo())) {
                    adicionarLog("Tabela (" + master.getNomeTabela() + " com TIPO diferente: Master(" + campoMaster.getTipo() + "), Slave(" + campoSlave.getTipo() + ")");
                }
                if (!campoMaster.getPermiteNull().equals(campoSlave.getPermiteNull())) {
                    adicionarLog("Tabela (" + master.getNomeTabela() + " com PERMITENULL diferente: Master(" + campoMaster.getPermiteNull() + "), Slave(" + campoSlave.getPermiteNull() + ")");
                }
                if (!campoMaster.getKey().equals(campoSlave.getKey())) {
                    adicionarLog("Tabela (" + master.getNomeTabela() + " com KEY diferente: Master(" + campoMaster.getKey() + "), Slave(" + campoSlave.getKey() + ")");
                }
                if (!campoMaster.getValorDefault().equals(campoSlave.getValorDefault())) {
                    adicionarLog("Tabela (" + master.getNomeTabela() + " com VALOR DEFAULT diferente: Master(" + campoMaster.getValorDefault() + "), Slave(" + campoSlave.getValorDefault() + ")");
                }
                if (!campoMaster.getExtra().equals(campoSlave.getExtra())) {
                    adicionarLog("Tabela (" + master.getNomeTabela() + " com EXTRA diferente: Master(" + campoMaster.getExtra() + "), Slave(" + campoSlave.getExtra() + ")");
                }
            }
        }
    }

    private void adicionarLog(String descricao) {
        try {
            logAgenteDAO.salvar(new LogAgente(descricao));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}