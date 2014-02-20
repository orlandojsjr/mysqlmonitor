/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.mysqlmonitor.mb;

import com.mysqlmonitor.entidade.Usuario;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

/**
 *
 * @author orlando
 */
@Model
public class IndexMB {
    
    @Inject private Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }  
    
    
}
