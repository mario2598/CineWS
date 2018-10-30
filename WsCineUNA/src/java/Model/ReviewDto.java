/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.time.ZoneId;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mario
 */
@XmlRootElement(name = "ReviewDto")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class ReviewDto {
      private Long revId;
      private Long revCalif;
      private String revOpinion;
      private MovieDto movieId;
      private UsuarioDto usuId;
      
       public ReviewDto(Review r) {
         this.revId = r.getRevId();
         this.revCalif = r.getRevCalif();
         this.revOpinion = r.getRevOpinion();
         this.movieId = new MovieDto(r.getMovieId());
         this.usuId = new UsuarioDto(r.getUsuId());      
    }

    public Long getRevId() {
        return revId;
    }

    public void setRevId(Long revId) {
        this.revId = revId;
    }

    public Long getRevCalif() {
        return revCalif;
    }

    public void setRevCalif(Long revCalif) {
        this.revCalif = revCalif;
    }

    public String getRevOpinion() {
        return revOpinion;
    }

    public void setRevOpinion(String revOpinion) {
        this.revOpinion = revOpinion;
    }

    public MovieDto getMovieId() {
        return movieId;
    }

    public void setMovieId(MovieDto movieId) {
        this.movieId = movieId;
    }

    public UsuarioDto getUsuId() {
        return usuId;
    }

    public void setUsuId(UsuarioDto usuId) {
        this.usuId = usuId;
    }
       
    
}
