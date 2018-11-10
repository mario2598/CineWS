/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Util.LocalDateAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author mario
 */
@XmlRootElement(name = "MovieDto")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class MovieDto {
     private Long movieId;
     private String movieNombre;
     private String movieResena;
     private String movieUrlesp;
     private String movieUrleng;
     @XmlJavaTypeAdapter(LocalDateAdapter.class)
     private LocalDate movieDate;
     private String movieTipo;
     private String movieEstado;
     private String moviePortada;
     private Long movieDuracion;
     @XmlTransient
     private List<ComprobanteDto> comprobanteList = new ArrayList<>();
     @XmlTransient
     private List<TandaDto> tandaList = new ArrayList<>();
     @XmlTransient
     private List<ReviewDto> reviewList = new ArrayList<>();
     private byte[] movieUrlimg;
     private String movieNombreing;
     private String movieResenaing;
     
     public MovieDto(){
         
     }
     
     public MovieDto(Movie m) {
         this.movieId = m.getMovieId();
         this.movieNombre = m.getMovieNombre();
         this.movieResena = m.getMovieResena();
         this.movieUrlesp = m.getMovieUrlesp();
         this.movieUrleng = m.getMovieUrleng();
         this.movieDate = m.getMovieDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
         this.movieEstado = m.getMovieEstado();
         this.moviePortada = m.getMoviePortada();
         this.movieDuracion = m.getMovieDuracion();
         this.movieUrlimg = (byte[]) m.getMovieUrlimg();
<<<<<<< HEAD
         this.movieTipo = m.getMovieTipo();
=======
         this.movieResenaing = m.getMovieResenaing();
         this.movieNombreing = m.getMovieNombreing();
>>>>>>> origin/mario/master5-6/11
    }

    public void convList(Movie m){
        for(Comprobante c : m.getComprobanteList()){ //Convertir cines a dto
             ComprobanteDto cDto = new ComprobanteDto(c);
             comprobanteList.add(cDto);
         }
    }

    public byte[] getMovieUrlimg() {
        return movieUrlimg;
    }

    public void setMovieUrlimg(byte[] movieUrlimg) {
        this.movieUrlimg = movieUrlimg;
    }

    public String getMovieNombreing() {
        return movieNombreing;
    }

    public void setMovieNombreing(String movieNombreing) {
        this.movieNombreing = movieNombreing;
    }

    public String getMovieResenaing() {
        return movieResenaing;
    }

    public void setMovieResenaing(String movieResenaing) {
        this.movieResenaing = movieResenaing;
    }

    public Long getMovieDuracion() {
        return movieDuracion;
    }

    public void setMovieDuracion(Long movieDuracion) {
        this.movieDuracion = movieDuracion;
    }
    @XmlTransient
    public List<ComprobanteDto> getComprobanteList() {
        return comprobanteList;
    }

    public void setComprobanteList(List<ComprobanteDto> comprobanteList) {
        this.comprobanteList = comprobanteList;
    }
    @XmlTransient
    public List<TandaDto> getTandaList() {
        return tandaList;
    }

    public void setTandaList(List<TandaDto> tandaList) {
        this.tandaList = tandaList;
    }
    @XmlTransient
    public List<ReviewDto> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<ReviewDto> reviewList) {
        this.reviewList = reviewList;
    }
    
     public void convListTand(Movie m){
        for(Tanda t : m.getTandaList()){ //Convertir cines a dto
             TandaDto cDto = new TandaDto(t);
             tandaList.add(cDto);
         }
    }
     
      public void convListRev(Movie m){
        for(Review t : m.getReviewList()){ //Convertir cines a dto
            ReviewDto cDto = new ReviewDto(t);
             reviewList.add(cDto);
         }
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

    public String getMovieUrleng() {
        return movieUrleng;
    }

    public void setMovieUrleng(String movieUrleng) {
        this.movieUrleng = movieUrleng;
    }


    public LocalDate getMovieDate() {
        return movieDate;
    }

    public void setMovieDate(LocalDate movieDate) {
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

    public String getMovieTipo() {
        return movieTipo;
    }

    public void setMovieTipo(String movieTipo) {
        this.movieTipo = movieTipo;
    }
     
     
}
