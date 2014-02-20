/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mysqlmonitor.entidade;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author orlando
 */
@Entity
@Table(name = "servidor")
@NamedQueries({
    @NamedQuery(name = "Servidor.findAll", query = "SELECT s FROM Servidor s"),
    @NamedQuery(name = "Servidor.findByIdServidor", query = "SELECT s FROM Servidor s WHERE s.idServidor = :idServidor"),
    @NamedQuery(name = "Servidor.findByIp", query = "SELECT s FROM Servidor s WHERE s.ip = :ip"),
    @NamedQuery(name = "Servidor.findByPorta", query = "SELECT s FROM Servidor s WHERE s.porta = :porta"),
    @NamedQuery(name = "Servidor.findByUsuario", query = "SELECT s FROM Servidor s WHERE s.usuario = :usuario"),
    @NamedQuery(name = "Servidor.findBySenha", query = "SELECT s FROM Servidor s WHERE s.senha = :senha"),
    @NamedQuery(name = "Servidor.findByTipo", query = "SELECT s FROM Servidor s WHERE s.tipo = :tipo"),
    @NamedQuery(name = "Servidor.findByDataCadastro", query = "SELECT s FROM Servidor s WHERE s.dataCadastro = :dataCadastro")})
public class Servidor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_SERVIDOR")
    private Integer idServidor;
    @Size(max = 45)
    @Column(name = "IP")
    private String ip;
    @Size(max = 45)
    @Column(name = "PORTA")
    private String porta;
    @Size(max = 45)
    @Column(name = "USUARIO")
    private String usuario;
    @Size(max = 45)
    @Column(name = "SENHA")
    private String senha;
    @Size(max = 45)
    @Column(name = "TIPO")
    private String tipo;
    @Column(name = "DATA_CADASTRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro;
    @JoinColumn(name = "ID_GRUPO_SERVIDOR", referencedColumnName = "ID_GRUPO_SERVIDOR")
    @ManyToOne(optional = false)
    private GrupoServidor grupoServidor;

    public Servidor() {
        dataCadastro = new Date();
    }

    public Servidor(Integer idServidor) {
        this.idServidor = idServidor;
    }

    public Integer getIdServidor() {
        return idServidor;
    }

    public void setIdServidor(Integer idServidor) {
        this.idServidor = idServidor;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPorta() {
        return porta;
    }

    public void setPorta(String porta) {
        this.porta = porta;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public GrupoServidor getGrupoServidor() {
        return grupoServidor;
    }

    public void setGrupoServidor(GrupoServidor grupoServidor) {
        this.grupoServidor = grupoServidor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idServidor != null ? idServidor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Servidor)) {
            return false;
        }
        Servidor other = (Servidor) object;
        if ((this.idServidor == null && other.idServidor != null) || (this.idServidor != null && !this.idServidor.equals(other.idServidor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mysqlmonitor.entidade.Servidor[ idServidor=" + idServidor + " ]";
    }
    
}
