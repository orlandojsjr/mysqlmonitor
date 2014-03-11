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
import javax.persistence.Lob;
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
@Table(name = "comando_sql")
@NamedQueries({
    @NamedQuery(name = "ComandoSql.findAll", query = "SELECT c FROM ComandoSql c"),
    @NamedQuery(name = "ComandoSql.findByIdComandoSql", query = "SELECT c FROM ComandoSql c WHERE c.idComandoSql = :idComandoSql"),
    @NamedQuery(name = "ComandoSql.findByStatus", query = "SELECT c FROM ComandoSql c WHERE c.status = :status"),
    @NamedQuery(name = "ComandoSql.findByData", query = "SELECT c FROM ComandoSql c WHERE c.data = :data")})
public class ComandoSql implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_COMANDO_SQL")
    private Integer idComandoSql;
    @Lob
    @Size(max = 65535)
    @Column(name = "COMANDO")
    private String comando;
    @Size(max = 45)
    @Column(name = "STATUS")
    private String status;
    @Column(name = "DATA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne(optional = false)
    private Usuario usuario;
    @JoinColumn(name = "ID_GRUPO_SERVIDOR", referencedColumnName = "ID_GRUPO_SERVIDOR")
    @ManyToOne(optional = false)
    private GrupoServidor grupoServidor;

    public ComandoSql() {
    }

    public ComandoSql(String comando, String status, Usuario usuario, GrupoServidor grupoServidor) {
        this.comando = comando;
        this.status = status;        
        this.usuario = usuario;
        this.grupoServidor = grupoServidor;
        this.data = new Date();
    }

    public ComandoSql(Integer idComandoSql) {
        this.idComandoSql = idComandoSql;
    }

    public Integer getIdComandoSql() {
        return idComandoSql;
    }

    public void setIdComandoSql(Integer idComandoSql) {
        this.idComandoSql = idComandoSql;
    }

    public String getComando() {
        return comando;
    }

    public void setComando(String comando) {
        this.comando = comando;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
        hash += (idComandoSql != null ? idComandoSql.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ComandoSql)) {
            return false;
        }
        ComandoSql other = (ComandoSql) object;
        if ((this.idComandoSql == null && other.idComandoSql != null) || (this.idComandoSql != null && !this.idComandoSql.equals(other.idComandoSql))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mysqlmonitor.entidade.ComandoSql[ idComandoSql=" + idComandoSql + " ]";
    }
    
}
