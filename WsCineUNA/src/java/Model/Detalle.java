/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mario
 */
@Entity
@Table(name = "DETALLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detalle.findAll", query = "SELECT d FROM Detalle d")
    , @NamedQuery(name = "Detalle.findByDetId", query = "SELECT d FROM Detalle d WHERE d.detId = :detId")
    , @NamedQuery(name = "Detalle.findByDetIdioma", query = "SELECT d FROM Detalle d WHERE d.detIdioma = :detIdioma")
    , @NamedQuery(name = "Detalle.findByDetCosto", query = "SELECT d FROM Detalle d WHERE d.detCosto = :detCosto")
    , @NamedQuery(name = "Detalle.findByDetHoraini", query = "SELECT d FROM Detalle d WHERE d.detHoraini = :detHoraini")
    , @NamedQuery(name = "Detalle.findByDetHorafin", query = "SELECT d FROM Detalle d WHERE d.detHorafin = :detHorafin")
    , @NamedQuery(name = "Detalle.findByDetFechaini", query = "SELECT d FROM Detalle d WHERE d.detFechaini = :detFechaini")
    , @NamedQuery(name = "Detalle.findByDetFechafin", query = "SELECT d FROM Detalle d WHERE d.detFechafin = :detFechafin")})
public class Detalle implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "DET_IDIOMA")
    private Long detIdioma;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DET_COSTO")
    private Long detCosto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DET_HORAINI")
    private Long detHoraini;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DET_HORAFIN")
    private Long detHorafin;

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
   // @NotNull
    @Column(name = "DET_ID")
    private Long detId;
    @Basic(optional = false)
 //   @NotNull
    @Column(name = "DET_FECHAINI")
    @Temporal(TemporalType.TIMESTAMP)
    private Date detFechaini;
    @Basic(optional = false)
 //   @NotNull
    @Column(name = "DET_FECHAFIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date detFechafin;
    @JoinColumn(name = "MOVIE_ID", referencedColumnName = "MOVIE_ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Movie movieId;
    @JoinColumn(name = "SALA_ID", referencedColumnName = "SALA_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Sala salaId;

    public Detalle() {
    }

    public Detalle(Long detId) {
        this.detId = detId;
    }

    public Detalle(Long detId, Long detIdioma, Long detCosto, Long detHoraini, Long detHorafin, Date detFechaini, Date detFechafin) {
        this.detId = detId;
        this.detIdioma = detIdioma;
        this.detCosto = detCosto;
        this.detHoraini = detHoraini;
        this.detHorafin = detHorafin;
        this.detFechaini = detFechaini;
        this.detFechafin = detFechafin;
    }

    public Long getDetId() {
        return detId;
    }

    public void setDetId(Long detId) {
        this.detId = detId;
    }

    public Long getDetIdioma() {
        return detIdioma;
    }

    public void setDetIdioma(Long detIdioma) {
        this.detIdioma = detIdioma;
    }

    public Long getDetCosto() {
        return detCosto;
    }

    public void setDetCosto(Long detCosto) {
        this.detCosto = detCosto;
    }

    public Long getDetHoraini() {
        return detHoraini;
    }

    public void setDetHoraini(Long detHoraini) {
        this.detHoraini = detHoraini;
    }

    public Long getDetHorafin() {
        return detHorafin;
    }

    public void setDetHorafin(Long detHorafin) {
        this.detHorafin = detHorafin;
    }

    public Date getDetFechaini() {
        return detFechaini;
    }

    public void setDetFechaini(Date detFechaini) {
        this.detFechaini = detFechaini;
    }

    public Date getDetFechafin() {
        return detFechafin;
    }

    public void setDetFechafin(Date detFechafin) {
        this.detFechafin = detFechafin;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detId != null ? detId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detalle)) {
            return false;
        }
        Detalle other = (Detalle) object;
        if ((this.detId == null && other.detId != null) || (this.detId != null && !this.detId.equals(other.detId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Detalle[ detId=" + detId + " ]";
    }

}
