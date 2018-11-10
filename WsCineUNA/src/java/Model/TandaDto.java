/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Util.LocalDateAdapter;
import java.time.LocalDate;
import java.time.ZoneId;
import javax.persistence.Column;
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
     private MovieDto movieId;
     private SalaDto salaId;
<<<<<<< HEAD
     private Integer horaTanda;
=======
     private Long tandaInihh;
     private Long tandaInimm;
     private Long tandaFinhh;
     private Long tandaFinmm;
>>>>>>> origin/mario/master5-6/11
    
      public TandaDto(Tanda t) {
         this.tandaId = t.getTandaId();
         this.tandaCobro = t.getTandaCobro();
<<<<<<< HEAD
         this.movieId = new MovieDto(t.getMovieId());
         //this.movieId = t.getMovieId().getMovieId();
//         this.tandaHinicio = t.getTandaHinicio().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//         this.tandaHfin = t.getTandaHfin().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
         this.salaId = new SalaDto(t.getSalaId());
//         this.horaTanda = t.getTandaHinicio().getHours();
         //this.salaId = t.getSalaId().getSalaId();
         /*try {
                this.tandaHinicio = LocalDateTime.ofInstant(t.getTandaHinicio().toInstant(), ZoneId.systemDefault());;
            } catch (Exception ex) {

            }*/
         
=======
         this.movieId = new MovieDto(t.getMovieId());       
         this.salaId = new SalaDto(t.getSalaId());
         this.tandaFinhh = t.getTandaFinhh();
         this.tandaFinmm = t.getTandaFinmm();
         this.tandaInihh = t.getTandaInihh();
         this.tandaInimm = t.getTandaInimm();
>>>>>>> origin/mario/master5-6/11
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

    public Long getTandaInihh() {
        return tandaInihh;
    }

    public void setTandaInihh(Long tandaInihh) {
        this.tandaInihh = tandaInihh;
    }
<<<<<<< HEAD
    
    public LocalDate getTandaHfin() {
        return tandaHfin;
=======

    public Long getTandaInimm() {
        return tandaInimm;
>>>>>>> origin/mario/master5-6/11
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
<<<<<<< HEAD
    */

    public Integer getHoraTanda() {
        return horaTanda;
    }

    public void setHoraTanda(Integer horaTanda) {
        this.horaTanda = horaTanda;
    }
=======

>>>>>>> origin/mario/master5-6/11
      
}
