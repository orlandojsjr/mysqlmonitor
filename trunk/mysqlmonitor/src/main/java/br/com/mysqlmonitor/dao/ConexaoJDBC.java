/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mysqlmonitor.dao;

import com.mysqlmonitor.entidade.Servidor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author orlando
 */
public class ConexaoJDBC {

    private Connection connection;
    private String url;

    public Connection getConnection() {
        return connection;
    }
        
    public Connection iniciarConexao(Servidor servidor) {
        try {
            Class.forName("com.mysql.jsdb.Driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }        
        try {
            connection = DriverManager.getConnection(url, servidor.getUsuario(), servidor.getSenha());
            url = "jdbc:mysql://" + servidor.getIp() + ":" + servidor.getPorta() + "/" + servidor.getGrupoServidor().getBancoDados() + "";
        } catch (SQLException ex) {
            ex.printStackTrace();            
        }     
        return connection;
    }

    public void fecharConexao(){
        try {
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
