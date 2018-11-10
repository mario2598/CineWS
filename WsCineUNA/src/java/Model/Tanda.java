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
    , @NamedQuery(name = "Tanda.findBySalaId", query = "SELECT t FROM Tanda t WHERE t.salaId.salaId = :salaId")
    , @NamedQuery(name = "Tanda.findByMovieId", query = "SELECT t FROM Tanda t WHERE t.movieId.movieId = :movieId")})
public class Tanda implements Serializable {

    @Column(name = "TANDA_COBRO")
    private Long tandaCobro;
    @Column(name = "TANDA_INIHH")
    private Long tandaInihh;
    @Column(name = "TANDA_INIMM")
    private Long tandaInimm;
    @Column(name = "TANDA_FINHH")
    private Long tandaFinhh;
    @Column(name = "TANDA_FINMM")
    private Long tandaFinmm;

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    //@NotNull
    @Column(name = "TANDA_ID")
    private Long tandaId;


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

    public Long getTandaInihh() {
        return tandaInihh;
    }

    public void setTandaInihh(Long tandaInihh) {
        this.tandaInihh = tandaInihh;
    }

    public Long getTandaInimm() {
        return tandaInimm;
    }

    public void setTandaInimm(Long tandaInimm) {
        this.tandaInimm = tandaInimm;
    }

    public Long getTandaFinhh() {
        return tandaFinhh;
    }

    public void setTandaFinhh(Long tandaFinhh) {
        this.tandaFinhh = tandaFinhh;
    }

    public Long getTandaFinmm() {
        return tandaFinmm;
    }

    public void setTandaFinmm(Long tandaFinmm) {
        this.tandaFinmm = tandaFinmm;
    }
    
}
