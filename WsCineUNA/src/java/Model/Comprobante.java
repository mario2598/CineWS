/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
   // @NotNull
    @Column(name = "COMP_COSTO")
    private Long compCosto;
    @JoinColumn(name = "BUT_ID", referencedColumnName = "BUT_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Butaca butId;
    @JoinColumn(name = "MOVIE_ID", referencedColumnName = "MOVIE_ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Movie movieId;
    @JoinColumn(name = "SALA_ID", referencedColumnName = "SALA_ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Sala salaId;
    @JoinColumn(name = "USU_ID", referencedColumnName = "USU_ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario usuId;

    public Comprobante() {
    }

    public Comprobante(Long compId) {
        this.compId = compId;
    }

    public Comprobante(Long compId, Long compCosto) {
        this.compId = compId;
        this.compCosto = compCosto;
    }

    public Long getCompId() {
        return compId;
    }

    public void setCompId(Long compId) {
        this.compId = compId;
    }

    public Long getCompCosto() {
        return compCosto;
    }

    public void setCompCosto(Long compCosto) {
        this.compCosto = compCosto;
    }

    public Butaca getButId() {
        return butId;
    }

    public void setButId(Butaca butId) {
        this.butId = butId;
    }

    public Movie getMovieId() {
        return movieId;
    }

    public void setMovieId(Movie movieId) {
        this.movieId = movieId;
    }

    public Sala getSalaId() {
        return salaId;
    }

    public void setSalaId(Sala salaId) {
        this.salaId = salaId;
    }

    public Usuario getUsuId() {
        return usuId;
    }

    public void setUsuId(Usuario usuId) {
        this.usuId = usuId;
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
