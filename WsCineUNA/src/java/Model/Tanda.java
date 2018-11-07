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
@Table(name = "TANDA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tanda.findAll", query = "SELECT t FROM Tanda t")
    , @NamedQuery(name = "Tanda.findByTandaId", query = "SELECT t FROM Tanda t WHERE t.tandaId = :tandaId")
    , @NamedQuery(name = "Tanda.findByTandaCobro", query = "SELECT t FROM Tanda t WHERE t.tandaCobro = :tandaCobro")
    , @NamedQuery(name = "Tanda.findBySalaId", query = "SELECT t FROM Tanda t WHERE t.salaId = :salaId")
    , @NamedQuery(name = "Tanda.findByMovieId", query = "SELECT t FROM Tanda t WHERE t.movieId.movieId = :movieId")    
    , @NamedQuery(name = "Tanda.findByTandaHinicio", query = "SELECT t FROM Tanda t WHERE t.tandaHinicio = :tandaHinicio")
    , @NamedQuery(name = "Tanda.findByTandaHfin", query = "SELECT t FROM Tanda t WHERE t.tandaHfin = :tandaHfin")})
public class Tanda implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    //@NotNull
    @Column(name = "TANDA_ID")
    private Long tandaId;
    @Column(name = "TANDA_COBRO")
    private Long tandaCobro;
    @Column(name = "TANDA_HINICIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tandaHinicio;
    @Column(name = "TANDA_HFIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tandaHfin;
    @JoinColumn(name = "MOVIE_ID", referencedColumnName = "MOVIE_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movieId;
    @JoinColumn(name = "SALA_ID", referencedColumnName = "SALA_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Sala salaId;

    public Tanda() {
        this.movieId=new Movie();
        this.salaId = new Sala();
    }

    public Tanda(TandaDto tDto){
        if(tDto.getMovieId() != null){
          this.tandaId = tDto.getTandaId();
        }
        actualizarTanda(tDto);
    }
    
    public void actualizarTanda(TandaDto tDto){
        this.tandaCobro = tDto.getTandaCobro();
        //this.tandaHfin = Date.from(tDto.getTandaHfin().atZone(ZoneId.systemDefault()).toInstant()); //atStartOfDay(ZoneId.systemDefault()).toInstant());
        //this.tandaHinicio = Date.from(tDto.getTandaHinicio().atZone(ZoneId.systemDefault()).toInstant());
        this.movieId = new Movie(tDto.getMovieId());
        this.salaId = new Sala(tDto.getTandaId());
        //this.
    }
    
    public Tanda(Long tandaId) {
        this.tandaId = tandaId;
    }

    public Long getTandaId() {
        return tandaId;
    }

    public void setTandaId(Long tandaId) {
        this.tandaId = tandaId;
    }

    public Long getTandaCobro() {
        return tandaCobro;
    }

    public void setTandaCobro(Long tandaCobro) {
        this.tandaCobro = tandaCobro;
    }

    public Date getTandaHinicio() {
        return tandaHinicio;
    }

    public void setTandaHinicio(Date tandaHinicio) {
        this.tandaHinicio = tandaHinicio;
    }

    public Date getTandaHfin() {
        return tandaHfin;
    }

    public void setTandaHfin(Date tandaHfin) {
        this.tandaHfin = tandaHfin;
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
        hash += (tandaId != null ? tandaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tanda)) {
            return false;
        }
        Tanda other = (Tanda) object;
        if ((this.tandaId == null && other.tandaId != null) || (this.tandaId != null && !this.tandaId.equals(other.tandaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Tanda[ tandaId=" + tandaId + " ]";
    }
    
}
