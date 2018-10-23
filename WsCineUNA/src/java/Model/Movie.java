/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "MOVIE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Movie.findAll", query = "SELECT m FROM Movie m")
    , @NamedQuery(name = "Movie.findByMovieId", query = "SELECT m FROM Movie m WHERE m.movieId = :movieId")
    , @NamedQuery(name = "Movie.findByMovieNombre", query = "SELECT m FROM Movie m WHERE m.movieNombre = :movieNombre")
    , @NamedQuery(name = "Movie.findByMovieResena", query = "SELECT m FROM Movie m WHERE m.movieResena = :movieResena")
    , @NamedQuery(name = "Movie.findByMovieUrlyt", query = "SELECT m FROM Movie m WHERE m.movieUrlyt = :movieUrlyt")
    , @NamedQuery(name = "Movie.findByMovieDate", query = "SELECT m FROM Movie m WHERE m.movieDate = :movieDate")
    , @NamedQuery(name = "Movie.findByMovieEstado", query = "SELECT m FROM Movie m WHERE m.movieEstado = :movieEstado")
    , @NamedQuery(name = "Movie.findByMoviePortada", query = "SELECT m FROM Movie m WHERE m.moviePortada = :moviePortada")})
public class Movie implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
  //  @NotNull
    @Column(name = "MOVIE_ID")
    private Long movieId;
    @Basic(optional = false)
  //  @NotNull
  //  @Size(min = 1, max = 50)
    @Column(name = "MOVIE_NOMBRE")
    private String movieNombre;
    @Basic(optional = false)
  //  @NotNull
  //  @Size(min = 1, max = 500)
    @Column(name = "MOVIE_RESENA")
    private String movieResena;
  //  @Size(max = 20)
    @Column(name = "MOVIE_URLYT")
    private String movieUrlyt;
    @Basic(optional = false)
  //  @NotNull
    @Column(name = "MOVIE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date movieDate;
    @Basic(optional = false)
 //   @NotNull
 //   @Size(min = 1, max = 1)
    @Column(name = "MOVIE_ESTADO")
    private String movieEstado;
    @Basic(optional = false)
 //   @NotNull
 //   @Size(min = 1, max = 100)
    @Column(name = "MOVIE_PORTADA")
    private String moviePortada;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movieId", fetch = FetchType.LAZY)
    private List<Detalle> detalleList;

    public Movie() {
    }

    public Movie(Long movieId) {
        this.movieId = movieId;
    }

    public Movie(Long movieId, String movieNombre, String movieResena, Date movieDate, String movieEstado, String moviePortada) {
        this.movieId = movieId;
        this.movieNombre = movieNombre;
        this.movieResena = movieResena;
        this.movieDate = movieDate;
        this.movieEstado = movieEstado;
        this.moviePortada = moviePortada;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getMovieNombre() {
        return movieNombre;
    }

    public void setMovieNombre(String movieNombre) {
        this.movieNombre = movieNombre;
    }

    public String getMovieResena() {
        return movieResena;
    }

    public void setMovieResena(String movieResena) {
        this.movieResena = movieResena;
    }

    public String getMovieUrlyt() {
        return movieUrlyt;
    }

    public void setMovieUrlyt(String movieUrlyt) {
        this.movieUrlyt = movieUrlyt;
    }

    public Date getMovieDate() {
        return movieDate;
    }

    public void setMovieDate(Date movieDate) {
        this.movieDate = movieDate;
    }

    public String getMovieEstado() {
        return movieEstado;
    }

    public void setMovieEstado(String movieEstado) {
        this.movieEstado = movieEstado;
    }

    public String getMoviePortada() {
        return moviePortada;
    }

    public void setMoviePortada(String moviePortada) {
        this.moviePortada = moviePortada;
    }

    @XmlTransient
    public List<Detalle> getDetalleList() {
        return detalleList;
    }

    public void setDetalleList(List<Detalle> detalleList) {
        this.detalleList = detalleList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (movieId != null ? movieId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Movie)) {
            return false;
        }
        Movie other = (Movie) object;
        if ((this.movieId == null && other.movieId != null) || (this.movieId != null && !this.movieId.equals(other.movieId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Movie[ movieId=" + movieId + " ]";
    }
    
}
