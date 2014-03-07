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
import javax.persistence.Lob;
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
@Table(name = "log_agente")
@NamedQueries({
    @NamedQuery(name = "LogAgente.findAll", query = "SELECT l FROM LogAgente l"),
    @NamedQuery(name = "LogAgente.findByIdLogAgente", query = "SELECT l FROM LogAgente l WHERE l.idLogAgente = :idLogAgente"),
    @NamedQuery(name = "LogAgente.findByData", query = "SELECT l FROM LogAgente l WHERE l.data = :data")})
public class LogAgente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_LOG_AGENTE")
    private Integer idLogAgente;
    @Lob
    @Size(max = 65535)
    @Column(name = "DESCRICAO")
    private String descricao;
    @Column(name = "DATA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;

    public LogAgente() {
    }

    public LogAgente(String descricao) {
        this.descricao = descricao;
        this.data = new Date();
    }
    
    public LogAgente(Integer idLogAgente) {
        this.idLogAgente = idLogAgente;
    }

    public Integer getIdLogAgente() {
        return idLogAgente;
    }

    public void setIdLogAgente(Integer idLogAgente) {
        this.idLogAgente = idLogAgente;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLogAgente != null ? idLogAgente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LogAgente)) {
            return false;
        }
        LogAgente other = (LogAgente) object;
        if ((this.idLogAgente == null && other.idLogAgente != null) || (this.idLogAgente != null && !this.idLogAgente.equals(other.idLogAgente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mysqlmonitor.entidade.LogAgente[ idLogAgente=" + idLogAgente + " ]";
    }
    
}
