/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.time.ZoneId;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
    , @NamedQuery(name = "Comprobante.findByMovieId", query = "SELECT c FROM Comprobante c  WHERE c.movieId = :movieId")
    , @NamedQuery(name = "Comprobante.findBySalaId", query = "SELECT c FROM Comprobante c  WHERE c.salaId = :salaId")
    , @NamedQuery(name = "Comprobante.findByCompCosto", query = "SELECT c FROM Comprobante c WHERE c.compCosto = :compCosto")})
public class Comprobante implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "COMP_COSTO")
    private Long compCosto;
    @Column(name = "COMP_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date compDate;

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "COMPROBANTE_ID_GENERATOR", sequenceName = "CINEUNA.COMPROBANTE_SEQ01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMPROBANTE_ID_GENERATOR")
    @Basic(optional = false)
   // @NotNull
    @Column(name = "COMP_ID")
    private Long compId;
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
    
     public Comprobante(ComprobanteDto compDto) {
        this.compId = compDto.getCompId();
        this.compCosto = compDto.getCompCosto();
     //   this.compDate = Date.from(compDto.getCompDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
        this.movieId = new Movie(compDto.getMovieId());
        this.salaId = new Sala(compDto.getSalaId());
        this.usuId = new Usuario(compDto.getUsuId());
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
    
    public Date getCompDate() {
        return compDate;
    }

    public void setCompDate(Date compDate) {
        this.compDate = compDate;
    }
    
}
