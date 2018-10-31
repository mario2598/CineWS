/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mario
 */
@Entity
@Table(name = "CINE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cine.findAll", query = "SELECT c FROM Cine c")
    , @NamedQuery(name = "Cine.findByCineId", query = "SELECT c FROM Cine c WHERE c.cineId = :cineId")
    , @NamedQuery(name = "Cine.findByCineNombre", query = "SELECT c FROM Cine c WHERE c.cineNombre = :cineNombre")
    , @NamedQuery(name = "Cine.findByCineTel", query = "SELECT c FROM Cine c WHERE c.cineTel = :cineTel")
    , @NamedQuery(name = "Cine.findByCineEmail", query = "SELECT c FROM Cine c WHERE c.cineEmail = :cineEmail")
    , @NamedQuery(name = "Cine.findByCineAbre", query = "SELECT c FROM Cine c WHERE c.cineAbre = :cineAbre")
    , @NamedQuery(name = "Cine.findByCineCierra", query = "SELECT c FROM Cine c WHERE c.cineCierra = :cineCierra")})
public class Cine implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
   // @NotNull
    @Column(name = "CINE_ID")
    private Long cineId;
    @Basic(optional = false)
    //@NotNull
    @Size(min = 1, max = 30)
    @Column(name = "CINE_NOMBRE")
    private String cineNombre;
    @Basic(optional = false)
    //@NotNull
    @Column(name = "CINE_TEL")
    private Long cineTel;
    @Basic(optional = false)
    // @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "CINE_EMAIL")
    private String cineEmail;
    @Basic(optional = false)
    //@NotNull
    @Column(name = "CINE_ABRE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cineAbre;
    @Basic(optional = false)
    //@NotNull
    @Column(name = "CINE_CIERRA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cineCierra;
    @OneToMany(mappedBy = "cineId", fetch = FetchType.LAZY)
    private List<Sala> salaList;
    @OneToMany(mappedBy = "cineId", fetch = FetchType.LAZY)
    private List<Usuario> usuarioList;

    public Cine() {
    }

    public Cine(Long cineId) {
        this.cineId = cineId;
    }

    public Cine(Long cineId, String cineNombre, Long cineTel, String cineEmail, Date cineAbre, Date cineCierra) {
        this.cineId = cineId;
        this.cineNombre = cineNombre;
        this.cineTel = cineTel;
        this.cineEmail = cineEmail;
        this.cineAbre = cineAbre;
        this.cineCierra = cineCierra;
    }

    public Long getCineId() {
        return cineId;
    }

    public void setCineId(Long cineId) {
        this.cineId = cineId;
    }

    public String getCineNombre() {
        return cineNombre;
    }

    public void setCineNombre(String cineNombre) {
        this.cineNombre = cineNombre;
    }

    public Long getCineTel() {
        return cineTel;
    }

    public void setCineTel(Long cineTel) {
        this.cineTel = cineTel;
    }

    public String getCineEmail() {
        return cineEmail;
    }

    public void setCineEmail(String cineEmail) {
        this.cineEmail = cineEmail;
    }

    public Date getCineAbre() {
        return cineAbre;
    }

    public void setCineAbre(Date cineAbre) {
        this.cineAbre = cineAbre;
    }

    public Date getCineCierra() {
        return cineCierra;
    }

    public void setCineCierra(Date cineCierra) {
        this.cineCierra = cineCierra;
    }

    @XmlTransient
    public List<Sala> getSalaList() {
        return salaList;
    }

    public void setSalaList(List<Sala> salaList) {
        this.salaList = salaList;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cineId != null ? cineId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cine)) {
            return false;
        }
        Cine other = (Cine) object;
        if ((this.cineId == null && other.cineId != null) || (this.cineId != null && !this.cineId.equals(other.cineId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Cine[ cineId=" + cineId + " ]";
    }
    
}
