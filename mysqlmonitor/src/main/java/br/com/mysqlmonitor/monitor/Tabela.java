/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.mysqlmonitor.monitor;

import java.util.List;

/**
 *
 * @author orlando
 */
public class Tabela {
    private String nomeTabela;
    private List<Campo> campos;

    public Tabela(String nomeTabela) {
        this.nomeTabela = nomeTabela;
    }

    public String getNomeTabela() {
        return nomeTabela;
    }

    public void setNomeTabela(String nomeTabela) {
        this.nomeTabela = nomeTabela;
    }

    public List<Campo> getCampos() {
        return campos;
    }

    public void setCampos(List<Campo> campos) {
        this.campos = campos;
    }    

    @Override
    public String toString() {
        return "Tabela{" + "nomeTabela=" + nomeTabela + ", campos=" + campos + '}';
    }
}
