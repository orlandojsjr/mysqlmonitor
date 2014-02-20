/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mysqlmonitor.dao;

public class Parametro {

    private String nomeParametro;
    private Object parametro;

    public Parametro() {
    }

    public Parametro(String nomeParametro, Object parametro) {
        this.nomeParametro = nomeParametro;
        this.parametro = parametro;
    }

    /**
     * @return the nomeParametro
     */
    public String getNomeParametro() {
        return nomeParametro;
    }

    /**
     * @param nomeParametro the nomeParametro to set
     */
    public void setNomeParametro(String nomeParametro) {
        this.nomeParametro = nomeParametro;
    }

    /**
     * @return the parametro
     */
    public Object getParametro() {
        return parametro;
    }

    /**
     * @param parametro the parametro to set
     */
    public void setParametro(Object parametro) {
        this.parametro = parametro;
    }
}
