/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Util.LocalDateAdapter;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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
    private Long compId;
    private String compCosto;
    private String butId;
    private String movieId;
    private String salaId;
    private String usuId;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate compDate;
    
    public ComprobanteDto(Comprobante c) {
         this.compId = c.getCompId();
         this.compCosto = c.getCompCosto().toString();
         this.butId = c.getButId().getButId().toString();
         this.movieId = c.getMovieId().getMovieId().toString();
         this.salaId = c.getSalaId().getSalaId().toString();
         this.usuId = c.getUsuId().getUsuId().toString();
         this.compDate =  c.getCompDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); 
    }

    public ComprobanteDto() {
    }

    public Long getCompId() {
        return compId;
    }

    public void setCompId(Long compId) {
        this.compId = compId;
    }

    public String getCompCosto() {
        return compCosto;
    }

    public void setCompCosto(String compCosto) {
        this.compCosto = compCosto;
    }

    public String getButId() {
        return butId;
    }

    public void setButId(String butId) {
        this.butId = butId;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getSalaId() {
        return salaId;
    }

    public void setSalaId(String salaId) {
        this.salaId = salaId;
    }

    public String getUsuId() {
        return usuId;
    }

    public void setUsuId(String usuId) {
        this.usuId = usuId;
    }

    public LocalDate getCompDate() {
        return compDate;
    }

    public void setCompDate(LocalDate compDate) {
        this.compDate = compDate;
    }


    
}
