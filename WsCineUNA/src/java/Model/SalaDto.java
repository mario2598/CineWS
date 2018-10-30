/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mario
 */
@XmlRootElement(name = "SalaDto")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class SalaDto {
    private Long salaId;
    private String salaEstado;
    private String salaImgfondo;
    @XmlTransient
    private List<MovieDto> movieList = new ArrayList<>();
    @XmlTransient
    private List<ButacaDto> butacaList = new ArrayList<>();
    @XmlTransient
    private List<ComprobanteDto> comprobanteList = new ArrayList<>();
    @XmlTransient
    private List<DetalleDto> detalleList = new ArrayList<>();;
    
    public SalaDto(Sala s) {
         this.salaId = s.getSalaId();
         this.salaEstado = s.getSalaEstado();
         this.salaImgfondo = s.getSalaImgfondo();
        
    }

    public void convListDet(Sala s){
         for(Detalle d : s.getDetalleList()){ 
             DetalleDto dDto = new DetalleDto(d);
             detalleList.add(dDto);
         }
    }
    
    public void convListComp(Sala s){
         for(Comprobante c : s.getComprobanteList()){ 
             ComprobanteDto cDto = new ComprobanteDto(c);
             comprobanteList.add(cDto);
         }
    }
    
    public void convList(Sala s){
         for(Movie m : s.getMovieList()){ 
             MovieDto mDto = new MovieDto(m);
             movieList.add(mDto);
         }
    }
    
    public void convListButaca(Sala s){
         for(Butaca b : s.getButacaList()){ 
             ButacaDto bDto = new ButacaDto(b);
             butacaList.add(bDto);
         }
    }
    
    public Long getSalaId() {
        return salaId;
    }

    public void setSalaId(Long salaId) {
        this.salaId = salaId;
    }

    public String getSalaEstado() {
        return salaEstado;
    }
    @XmlTransient
    public List<DetalleDto> getDetalleList() {
        return detalleList;
    }

    public void setDetalleList(List<DetalleDto> detalleList) {
        this.detalleList = detalleList;
    }

    public void setSalaEstado(String salaEstado) {
        this.salaEstado = salaEstado;
    }

    public String getSalaImgfondo() {
        return salaImgfondo;
    }

    public void setSalaImgfondo(String salaImgfondo) {
        this.salaImgfondo = salaImgfondo;
    }
    @XmlTransient
    public List<MovieDto> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<MovieDto> movieList) {
        this.movieList = movieList;
    }
    @XmlTransient
    public List<ButacaDto> getButacaList() {
        return butacaList;
    }

    public void setButacaList(List<ButacaDto> butacaList) {
        this.butacaList = butacaList;
    }
    @XmlTransient
    public List<ComprobanteDto> getComprobanteList() {
        return comprobanteList;
    }

    public void setComprobanteList(List<ComprobanteDto> comprobanteList) {
        this.comprobanteList = comprobanteList;
    }
    
    
}
