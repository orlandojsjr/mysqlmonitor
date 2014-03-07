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
public class Campo {

    private Tabela tabela;
    private String nome;
    private String tipo;
    private String permiteNull;
    private String key;
    private String valorDefault;
    private String extra;

    public Tabela getTabela() {
        return tabela;
    }

    public void setTabela(Tabela tabela) {
        this.tabela = tabela;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    @Override
    public String toString() {
        return "Campo{" + "tabela=" + tabela + ", nome=" + nome + ", tipo=" + tipo + ", permiteNull=" + permiteNull + ", key=" + key + ", valorDefault=" + valorDefault + ", extra=" + extra + '}';
    }
}
