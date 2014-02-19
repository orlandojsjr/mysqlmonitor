/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mysqlmonitor.mb;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author orlando
 */
public class Face {
    public void addMensagem(String mensagem, FacesMessage.Severity tipo) {        
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(tipo, mensagem, null));        
    }
}

