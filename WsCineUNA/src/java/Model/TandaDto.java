/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

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
     private Integer horaTanda;
     private Long tandaInihh;
     private Long tandaInimm;
     private Long tandaFinhh;
     private Long tandaFinmm;
    
      public TandaDto(Tanda t) {
         this.tandaId = t.getTandaId();
         this.tandaCobro = t.getTandaCobro();
         this.movieId = new MovieDto(t.getMovieId());       
         this.salaId = new SalaDto(t.getSalaId());
         this.tandaFinhh = t.getTandaFinhh();
         this.tandaFinmm = t.getTandaFinmm();
         this.tandaInihh = t.getTandaInihh();
         this.tandaInimm = t.getTandaInimm();
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

    public Integer getHoraTanda() {
        return horaTanda;
    }

    public void setHoraTanda(Integer horaTanda) {
        this.horaTanda = horaTanda;
    }

      
}
