/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
    , @NamedQuery(name = "Movie.findByMovieUrlesp", query = "SELECT m FROM Movie m WHERE m.movieUrlesp = :movieUrlesp")
    , @NamedQuery(name = "Movie.findByMovieDate", query = "SELECT m FROM Movie m WHERE m.movieDate = :movieDate")
    , @NamedQuery(name = "Movie.findByMovieEstado", query = "SELECT m FROM Movie m WHERE UPPER(m.movieEstado) = :movieEstado")//revisar ese upper() like
    , @NamedQuery(name = "Movie.findByMoviePortada", query = "SELECT m FROM Movie m WHERE m.moviePortada = :moviePortada")
    , @NamedQuery(name = "Movie.findByMovieDuracion", query = "SELECT m FROM Movie m WHERE m.movieDuracion = :movieDuracion")
    , @NamedQuery(name = "Movie.findByMovieTipo", query = "SELECT m FROM Movie m WHERE m.movieTipo = :movieTipo")
    , @NamedQuery(name = "Movie.findByMovieUrleng", query = "SELECT m FROM Movie m WHERE m.movieUrleng = :movieUrleng")
    , @NamedQuery(name = "Movie.findByMovieIdioma", query = "SELECT m FROM Movie m WHERE m.movieIdioma = :movieIdioma")
    /*, @NamedQuery(name = "Movie.findAvailable", query = "SELECT m from MOVIE m WHERE m.movieEstado='C'")
    , @NamedQuery(name = "Movie.findUnavailable", query = "SELECT m from MOVIE m WHERE m.movieEstado='P'")*/  
})
public class Movie implements Serializable {

    @Column(name = "MOVIE_DURACION")
    private Long movieDuracion;
    @Column(name = "MOVIE_IDIOMA")
    private Long movieIdioma;
    @Lob
    @Column(name = "MOVIE_URLIMG")
    private Serializable movieUrlimg;

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    //@NotNull
    @Column(name = "MOVIE_ID")
    private Long movieId;
    @Basic(optional = false)
    //@NotNull
    //@Size(min = 1, max = 50)
    @Column(name = "MOVIE_NOMBRE")
    private String movieNombre;
    @Basic(optional = false)
    //@NotNull
    //@Size(min = 1, max = 500)
    @Column(name = "MOVIE_RESENA")
    private String movieResena;
    //@Size(max = 100)
    @Column(name = "MOVIE_URLESP")
    private String movieUrlesp;
    @Basic(optional = false)
    //@NotNull
    @Column(name = "MOVIE_DATE")
    @Temporal(TemporalType.TIMESTAMP)//todo preguntar
    private Date movieDate;
    @Basic(optional = false)
    //@NotNull
    //@Size(min = 1, max = 1)
    @Column(name = "MOVIE_ESTADO")
    private String movieEstado;
    @Basic(optional = false)
    //@NotNull
    //@Size(min = 1, max = 100)
    @Column(name = "MOVIE_PORTADA")
    private String moviePortada;
    //@Size(max = 3)
    @Column(name = "MOVIE_TIPO")
    private String movieTipo;
    //@Size(max = 100)
    @Column(name = "MOVIE_URLENG")
    private String movieUrleng;
    @OneToMany(mappedBy = "movieId", fetch = FetchType.LAZY)
    private List<Tanda> tandaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movieId", fetch = FetchType.LAZY)
    private List<Comprobante> comprobanteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movieId", fetch = FetchType.LAZY)
    private List<Review> reviewList;

    public Movie() {
    }

    public Movie(MovieDto movie) {
        if(movie.getMovieId() != null){
          this.movieId = movie.getMovieId();
        }
        actualizarMovie(movie);
    }
    
    public void actualizarMovie(MovieDto movie){
        this.movieNombre = movie.getMovieNombre();
        this.movieResena = movie.getMovieResena();
        this.movieDate = Date.from(movie.getMovieDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
        this.movieEstado = movie.getMovieEstado();
        this.moviePortada = movie.getMoviePortada();
        this.movieUrlimg = movie.getMovieUrlimg();
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

    public String getMovieUrlesp() {
        return movieUrlesp;
    }

    public void setMovieUrlesp(String movieUrlesp) {
        this.movieUrlesp = movieUrlesp;
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

    public Long getMovieDuracion() {
        return movieDuracion;
    }

    public void setMovieDuracion(Long movieDuracion) {
        this.movieDuracion = movieDuracion;
    }

    public String getMovieTipo() {
        return movieTipo;
    }

    public void setMovieTipo(String movieTipo) {
        this.movieTipo = movieTipo;
    }

    public String getMovieUrleng() {
        return movieUrleng;
    }

    public void setMovieUrleng(String movieUrleng) {
        this.movieUrleng = movieUrleng;
    }

    public Long getMovieIdioma() {
        return movieIdioma;
    }

    public void setMovieIdioma(Long movieIdioma) {
        this.movieIdioma = movieIdioma;
    }

    @XmlTransient
    public List<Tanda> getTandaList() {
        return tandaList;
    }

    public void setTandaList(List<Tanda> tandaList) {
        this.tandaList = tandaList;
    }

    @XmlTransient
    public List<Comprobante> getComprobanteList() {
        return comprobanteList;
    }

    public void setComprobanteList(List<Comprobante> comprobanteList) {
        this.comprobanteList = comprobanteList;
    }

    @XmlTransient
    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
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

    public Serializable getMovieUrlimg() {
        return movieUrlimg;
    }

    public void setMovieUrlimg(Serializable movieUrlimg) {
        this.movieUrlimg = movieUrlimg;
    }
    
}
