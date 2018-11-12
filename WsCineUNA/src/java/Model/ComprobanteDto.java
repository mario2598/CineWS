/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Util.LocalDateAdapter;
import java.time.LocalDate;
import java.time.ZoneId;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author mario
 */

@XmlRootElement(name = "ComprobanteDto")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class ComprobanteDto {
    
    private static final long serialVersionUID = 1L;
  
    private Long compId;
    private Long compCosto;
    private Long butId;
    private Long movieId;
    private Long salaId;
    private Long usuId;
   // @XmlJavaTypeAdapter(LocalDateAdapter.class)
   // private LocalDate compDate;
    
    public ComprobanteDto(Comprobante c) {
         this.compId = c.getCompId();
         this.compCosto = c.getCompCosto();
         this.butId = c.getButId().getButId();
         this.movieId = c.getMovieId().getMovieId();
         this.salaId = c.getSalaId().getSalaId();
         this.usuId = c.getUsuId().getUsuId();
       //  this.compDate =  c.getCompDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); 
    }

    public ComprobanteDto() {
    }
    
    public Long getCompId() {
        return compId;
    }

    public void setCompId(Long compId) {
        this.compId = compId;
    }

    public Long getCompCosto() {
        return compCosto;
    }

    public void setCompCosto(Long compCosto) {
        this.compCosto = compCosto;
    }

    public Long getButId() {
        return butId;
    }

    public void setButId(Long butId) {
        this.butId = butId;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Long getSalaId() {
        return salaId;
    }

    public void setSalaId(Long salaId) {
        this.salaId = salaId;
    }

    public Long getUsuId() {
        return usuId;
    }

    public void setUsuId(Long usuId) {
        this.usuId = usuId;
    }
   /* @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate getCompDate() {
        return compDate;
    }
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public void setCompDate(LocalDate compDate) {
        this.compDate = compDate;
    } */  
}
