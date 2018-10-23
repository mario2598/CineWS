/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mario
 */
@Entity
@Table(name = "COMPROBANTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comprobante.findAll", query = "SELECT c FROM Comprobante c")
    , @NamedQuery(name = "Comprobante.findByCompId", query = "SELECT c FROM Comprobante c WHERE c.compId = :compId")
    , @NamedQuery(name = "Comprobante.findByMovieId", query = "SELECT c FROM Comprobante c WHERE c.movieId = :movieId")
    , @NamedQuery(name = "Comprobante.findBySalaId", query = "SELECT c FROM Comprobante c WHERE c.salaId = :salaId")
    , @NamedQuery(name = "Comprobante.findByUsuId", query = "SELECT c FROM Comprobante c WHERE c.usuId = :usuId")
    , @NamedQuery(name = "Comprobante.findByCompCosto", query = "SELECT c FROM Comprobante c WHERE c.compCosto = :compCosto")})
public class Comprobante implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
   // @NotNull
    @Column(name = "COMP_ID")
    private Long compId;
    @Basic(optional = false)
  //  @NotNull
    @Column(name = "MOVIE_ID")
    private Long movieId;
    @Basic(optional = false)
  //  @NotNull
    @Column(name = "SALA_ID")
    private Long salaId;
    @Basic(optional = false)
  //  @NotNull
    @Column(name = "USU_ID")
    private Long usuId;
    @Basic(optional = false)
   // @NotNull
    @Column(name = "COMP_COSTO")
    private Long compCosto;

    public Comprobante() {
    }

    public Comprobante(Long compId) {
        this.compId = compId;
    }

    public Comprobante(Long compId, Long movieId, Long salaId, Long usuId, Long compCosto) {
        this.compId = compId;
        this.movieId = movieId;
        this.salaId = salaId;
        this.usuId = usuId;
        this.compCosto = compCosto;
    }

    public Long getCompId() {
        return compId;
    }

    public void setCompId(Long compId) {
        this.compId = compId;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Long getSalaId() {
        return salaId;
    }

    public void setSalaId(Long salaId) {
        this.salaId = salaId;
    }

    public Long getUsuId() {
        return usuId;
    }

    public void setUsuId(Long usuId) {
        this.usuId = usuId;
    }

    public Long getCompCosto() {
        return compCosto;
    }

    public void setCompCosto(Long compCosto) {
        this.compCosto = compCosto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (compId != null ? compId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comprobante)) {
            return false;
        }
        Comprobante other = (Comprobante) object;
        if ((this.compId == null && other.compId != null) || (this.compId != null && !this.compId.equals(other.compId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Comprobante[ compId=" + compId + " ]";
    }
    
}
