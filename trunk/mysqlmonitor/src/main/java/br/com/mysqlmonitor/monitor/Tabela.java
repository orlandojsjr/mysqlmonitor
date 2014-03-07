/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.mysqlmonitor.monitor;

/**
 *
 * @author orlando
 */
public class Tabela {
    private String nomeTabela;
    private String campo;
    private String tipo;
    private String permiteNull;
    private String key;
    private String valorDefault;
    private String extra;

    public Tabela(String nomeTabela) {
        this.nomeTabela = nomeTabela;
    }

    public String getNomeTabela() {
        return nomeTabela;
    }

    public void setNomeTabela(String nomeTabela) {
        this.nomeTabela = nomeTabela;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPermiteNull() {
        return permiteNull;
    }

    public void setPermiteNull(String permiteNull) {
        this.permiteNull = permiteNull;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValorDefault() {
        return valorDefault;
    }

    public void setValorDefault(String valorDefault) {
        this.valorDefault = valorDefault;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }    
}
