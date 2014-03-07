/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mysqlmonitor.monitor;

import br.com.mysqlmonitor.dao.ConexaoJDBC;
import br.com.mysqlmonitor.dao.GrupoServidorDAO;
import br.com.mysqlmonitor.dao.LogAgenteDAO;
import com.mysqlmonitor.entidade.GrupoServidor;
import com.mysqlmonitor.entidade.LogAgente;
import com.mysqlmonitor.entidade.Servidor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author orlando
 */
public class Monitor {

    private GrupoServidorDAO grupoServidorDAO = new GrupoServidorDAO();
    private LogAgenteDAO logAgenteDAO = new LogAgenteDAO();
    private ConexaoJDBC conexaoJDBC = new ConexaoJDBC();

    public void excutarConferencia(GrupoServidor grupoServidor) {
        try {
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
            ResultSet rs = con.createStatement().executeQuery(query.toString());
            while (rs.next()) {
                Tabela tabela = carregarCamposTabela(servidor, new Tabela(rs.getString("Tables_in_" + servidor.getGrupoServidor().getBancoDados())));
                listaTabela.add(tabela);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Monitor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaTabela;
    }

    private Tabela carregarCamposTabela(Servidor servidor, Tabela tabela) {
        try {
            StringBuilder query = new StringBuilder("DESC ").append(tabela.getNomeTabela());
            Connection con = conexaoJDBC.iniciarConexao(servidor);
            ResultSet rs = con.createStatement().executeQuery(query.toString());
            while (rs.next()) {
                tabela.setCampo(rs.getString("Field"));
                tabela.setTipo(rs.getString("Type"));
                tabela.setPermiteNull(rs.getString("Null"));
                tabela.setKey(rs.getString("Key"));
                tabela.setValorDefault(rs.getString(rs.getString("Default")));
                tabela.setExtra(rs.getString("Extra"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return tabela;
    }

    private void comparar(List<Tabela> tabelasServidorMaster, List<Tabela> tabelasServidorSlave) {
        Tabela master;
        Tabela slave;        
        
        for (int i = 0; i < tabelasServidorMaster.size(); i++) {
            master = tabelasServidorMaster.get(i);
            slave = tabelasServidorSlave.get(i);
            
            if(!master.getNomeTabela().equals(slave.getNomeTabela())){
                adicionarLog("Tabela com NOME diferente: Master("+master.getNomeTabela()+"), Slave("+slave.getNomeTabela()+")");
            }
            if(!master.getCampo().equals(slave.getCampo())){
                adicionarLog("Tabela ("+master.getNomeTabela()+" com CAMPO diferente: Master("+master.getCampo()+"), Slave("+slave.getCampo()+")");
            }
            if(!master.getTipo().equals(slave.getTipo())){
                adicionarLog("Tabela ("+master.getNomeTabela()+" com TIPO diferente: Master("+master.getTipo()+"), Slave("+slave.getTipo()+")");
            }
            if(!master.getPermiteNull().equals(slave.getPermiteNull())){
                adicionarLog("Tabela ("+master.getNomeTabela()+" com PERMITENULL diferente: Master("+master.getPermiteNull()+"), Slave("+slave.getPermiteNull()+")");
            }
            if(!master.getKey().equals(slave.getKey())){
                adicionarLog("Tabela ("+master.getNomeTabela()+" com KEY diferente: Master("+master.getKey()+"), Slave("+slave.getKey()+")");
            }
            if(!master.getValorDefault().equals(slave.getValorDefault())){
                adicionarLog("Tabela ("+master.getNomeTabela()+" com VALOR DEFAULT diferente: Master("+master.getValorDefault()+"), Slave("+slave.getValorDefault()+")");
            }
            if(!master.getExtra().equals(slave.getExtra())){
                adicionarLog("Tabela ("+master.getNomeTabela()+" com EXTRA diferente: Master("+master.getExtra()+"), Slave("+slave.getExtra()+")");
            }
        }        
    }
    
    private void adicionarLog(String descricao){
        try {
            logAgenteDAO.salvar(new LogAgente(descricao));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
