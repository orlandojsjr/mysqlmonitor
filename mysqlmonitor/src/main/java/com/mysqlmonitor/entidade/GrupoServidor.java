/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mysqlmonitor.entidade;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author orlando
 */
@Entity
@Table(name = "grupo_servidor")
@NamedQueries({
    @NamedQuery(name = "GrupoServidor.findAll", query = "SELECT g FROM GrupoServidor g"),
    @NamedQuery(name = "GrupoServidor.findByIdGrupoServidor", query = "SELECT g FROM GrupoServidor g WHERE g.idGrupoServidor = :idGrupoServidor"),
    @NamedQuery(name = "GrupoServidor.findByNome", query = "SELECT g FROM GrupoServidor g WHERE g.nome = :nome"),
    @NamedQuery(name = "GrupoServidor.findByBancoDados", query = "SELECT g FROM GrupoServidor g WHERE g.bancoDados = :bancoDados"),
    @NamedQuery(name = "GrupoServidor.findByDataCadastro", query = "SELECT g FROM GrupoServidor g WHERE g.dataCadastro = :dataCadastro")})
public class GrupoServidor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_GRUPO_SERVIDOR")
    private Integer idGrupoServidor;
    @Size(max = 45)
    @Column(name = "NOME")
    private String nome;
    @Size(max = 45)
    @Column(name = "BANCO_DADOS")
    private String bancoDados;
    @Column(name = "DATA_CADASTRO")
    @Temporal(TemporalType.DATE)
    private Date dataCadastro;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "grupoServidor")
    private List<Servidor> servidorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "grupoServidor")
    private List<ComandoSql> comandoSqlList;

    public GrupoServidor() {
        dataCadastro = new Date();
    }

    public GrupoServidor(Integer idGrupoServidor) {
        this.idGrupoServidor = idGrupoServidor;
    }

    public Integer getIdGrupoServidor() {
        return idGrupoServidor;
    }

    public void setIdGrupoServidor(Integer idGrupoServidor) {
        this.idGrupoServidor = idGrupoServidor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getBancoDados() {
        return bancoDados;
    }

    public void setBancoDados(String bancoDados) {
        this.bancoDados = bancoDados;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public List<Servidor> getServidorList() {
        return servidorList;
    }

    public void setServidorList(List<Servidor> servidorList) {
        this.servidorList = servidorList;
    }

    public List<ComandoSql> getComandoSqlList() {
        return comandoSqlList;
    }

    public void setComandoSqlList(List<ComandoSql> comandoSqlList) {
        this.comandoSqlList = comandoSqlList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGrupoServidor != null ? idGrupoServidor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GrupoServidor)) {
            return false;
        }
        GrupoServidor other = (GrupoServidor) object;
        if ((this.idGrupoServidor == null && other.idGrupoServidor != null) || (this.idGrupoServidor != null && !this.idGrupoServidor.equals(other.idGrupoServidor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GrupoServidor{" + "idGrupoServidor=" + idGrupoServidor + '}';
    }   
}
