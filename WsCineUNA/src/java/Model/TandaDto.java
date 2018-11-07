/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Util.LocalDateAdapter;
import Util.LocalDateTimeAdapter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import javafx.beans.property.ObjectProperty;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author mario
 */
@XmlRootElement(name = "TandaDto")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class TandaDto {
     private Long tandaId;
     private Long tandaCobro;
     @XmlJavaTypeAdapter(LocalDateAdapter.class)
     private LocalDate tandaHinicio;
     //@XmlJavaTypeAdapter(LocalDateAdapter.class)
     //private LocalDate tandaHfin;
     private MovieDto movieId;
     private SalaDto salaId;
    
      public TandaDto(Tanda t) {
         this.tandaId = t.getTandaId();
         this.tandaCobro = t.getTandaCobro();
         this.movieId = new MovieDto(t.getMovieId());
         //this.movieId = t.getMovieId().getMovieId();
         //this.tandaHinicio = t.getTandaHinicio().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
         //this.tandaHfin = t.getTandaHfin().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
         this.salaId = new SalaDto(t.getSalaId());
         //this.salaId = t.getSalaId().getSalaId();
         /*try {
                this.tandaHinicio = LocalDateTime.ofInstant(t.getTandaHinicio().toInstant(), ZoneId.systemDefault());;
            } catch (Exception ex) {

            }*/
         
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

    public LocalDate getTandaHinicio() {
        return tandaHinicio;
    }

    public void setTandaHinicio(LocalDate tandaHinicio) {
        this.tandaHinicio = tandaHinicio;
    }
/*
    public LocalDateTime getTandaHfin() {
        return tandaHfin;
    }

    public void setTandaHfin(LocalDateTime tandaHfin) {
        this.tandaHfin = tandaHfin;
    }
*/
    public MovieDto getMovieId() {
        return movieId;
    }
    
    public void setMovieId(MovieDto movieId) {
        this.movieId = movieId;
    }

    public SalaDto getSalaId() {
        return salaId;
    }

    public void setSalaId(SalaDto salaId) {
        this.salaId = salaId;
    }
    
    /*
    public Long getMovieId() {
        return movieId;
    }
    
    public void setMovieId(MovieDto movieId) {
        this.movieId = movieId.getMovieId();
    }

    public Long getSalaId() {
        return salaId;
    }

    public void setSalaId(SalaDto salaId) {
        this.salaId = salaId.getSalaId();
    }
    */
      
}
