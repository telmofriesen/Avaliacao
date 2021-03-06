/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inscricao.persistence.entity;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 *
 * @author telmo
 */
@Entity
@Table(name="regiao")
@NamedQueries({
    @NamedQuery(name = "Regiao.findAll", query = "SELECT r FROM Regiao r")})
public class Regiao implements Serializable{
    private static final long serialVersionUID = 1L;
   
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigo")
    private Integer codigo;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "descricao")
    private String descricao = "";
    
    public Regiao() {
    }
    
    public Regiao(Integer codigo) {
        this.codigo = codigo;
    }
    
    public Regiao(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }
    
    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Regiao)) {
            return false;
        }
        Regiao other = (Regiao) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "inscricao.persistence.entity.Regiao[ codigo=" + codigo + " ]";
    }
}
