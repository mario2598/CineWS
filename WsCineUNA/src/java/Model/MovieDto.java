/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Util.LocalDateAdapter;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
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
     private String movieUrlyt;
     @XmlJavaTypeAdapter(LocalDateAdapter.class)
     private LocalDate movieDate;
     private String movieEstado;
     private String moviePortada;
     @XmlTransient
     private List<ComprobanteDto> comprobanteList = new ArrayList<>();
     
     public MovieDto(Movie m) {
         this.movieId = m.getMovieId();
         this.movieNombre = m.getMovieNombre();
         this.movieResena = m.getMovieResena();
         this.movieUrlyt = m.getMovieUrlyt();
         this.movieDate = m.getMovieDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
         this.movieEstado = m.getMovieEstado();
         this.moviePortada = m.getMoviePortada();
    }

    public void convList(Movie m){
        for(Comprobante c : m.getComprobanteList()){ //Convertir cines a dto
             ComprobanteDto cDto = new ComprobanteDto(c);
             comprobanteList.add(cDto);
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

    public String getMovieUrlyt() {
        return movieUrlyt;
    }

    public void setMovieUrlyt(String movieUrlyt) {
        this.movieUrlyt = movieUrlyt;
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
     
     
}
