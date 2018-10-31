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

@XmlRootElement(name = "ComprobanteDto")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class ComprobanteDto {
    private Long compId;
    private Long compCosto;
    private ButacaDto butId;
    private MovieDto movieId;
    private SalaDto salaId;
    private UsuarioDto usuId;
    
    public ComprobanteDto(Comprobante c) {
         this.compId = c.getCompId();
         this.compCosto = c.getCompCosto();
         this.butId = new ButacaDto(c.getButId());
         this.movieId = new MovieDto(c.getMovieId());
         this.salaId = new SalaDto(c.getSalaId());
         this.usuId = new UsuarioDto(c.getUsuId());
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

    public ButacaDto getButId() {
        return butId;
    }

    public void setButId(ButacaDto butId) {
        this.butId = butId;
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

    public UsuarioDto getUsuId() {
        return usuId;
    }

    public void setUsuId(UsuarioDto usuId) {
        this.usuId = usuId;
    }
    
    
    
}
