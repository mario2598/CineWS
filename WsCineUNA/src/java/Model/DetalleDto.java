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
@XmlRootElement(name = "DetalleDto")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class DetalleDto {
    private Long detId;
    private Long detIdioma;
    private Long detCosto;
    private Long detHoraini;
    private Long detHorafin;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate detFechaini;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate detFechafin;
    private MovieDto movieId;
    private SalaDto salaId;
    
    public DetalleDto(Detalle d) {
         this.detId = d.getDetId();
         this.detIdioma = d.getDetIdioma();
         this.detCosto = d.getDetCosto();
         this.detHoraini = d.getDetHoraini();
         this.detHorafin = d.getDetHorafin();
         this.detFechaini = d.getDetFechaini().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
         this.detFechafin = d.getDetFechafin().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
         this.salaId = new SalaDto(d.getSalaId());
         this.movieId = new MovieDto(d.getMovieId());
    }

    public Long getDetId() {
        return detId;
    }

    public void setDetId(Long detId) {
        this.detId = detId;
    }

    public Long getDetIdioma() {
        return detIdioma;
    }

    public void setDetIdioma(Long detIdioma) {
        this.detIdioma = detIdioma;
    }

    public Long getDetCosto() {
        return detCosto;
    }

    public void setDetCosto(Long detCosto) {
        this.detCosto = detCosto;
    }

    public Long getDetHoraini() {
        return detHoraini;
    }

    public void setDetHoraini(Long detHoraini) {
        this.detHoraini = detHoraini;
    }

    public Long getDetHorafin() {
        return detHorafin;
    }

    public void setDetHorafin(Long detHorafin) {
        this.detHorafin = detHorafin;
    }

    public LocalDate getDetFechaini() {
        return detFechaini;
    }

    public void setDetFechaini(LocalDate detFechaini) {
        this.detFechaini = detFechaini;
    }

    public LocalDate getDetFechafin() {
        return detFechafin;
    }

    public void setDetFechafin(LocalDate detFechafin) {
        this.detFechafin = detFechafin;
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
    
    
    
}
