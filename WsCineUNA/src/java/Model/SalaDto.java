/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

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
    private String salaTipo;
    private String salaNombre;
    private Long salaFilas;
    private Long salaCol;
    private Long cineId;
    @XmlTransient
    private List<TandaDto> tandaList = new ArrayList<>();
    @XmlTransient
    private List<ButacaDto> butacaList = new ArrayList<>();
    @XmlTransient
    private List<ComprobanteDto> comprobanteList = new ArrayList<>();
    
    public SalaDto(){
        
    }
    
    public SalaDto(Sala s) {
         this.salaId = s.getSalaId();
         this.salaEstado = s.getSalaEstado();
         this.salaImgfondo = s.getSalaImgfondo();
         this.salaTipo = s.getSalaTipo();
         this.salaNombre = s.getSalaNombre();
         this.salaFilas = s.getSalaFilas();
         this.salaCol = s.getSalaCol();
         if(s.getCineId()!=null)
         this.cineId = s.getCineId().getCineId();
    }

    
    public void convListComp(Sala s){
         for(Comprobante c : s.getComprobanteList()){ 
             ComprobanteDto cDto = new ComprobanteDto(c);
             comprobanteList.add(cDto);
         }
    }
    
    public void convList(Sala s){
         for(Tanda m : s.getTandaList()){ 
             TandaDto mDto = new TandaDto(m);
             tandaList.add(mDto);
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
    public List<TandaDto> getTandaList() {
        return tandaList;
    }

    public void setTandaList(List<TandaDto> tandaList) {
        this.tandaList = tandaList;
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

    public String getSalaTipo() {
        return salaTipo;
    }

    public void setSalaTipo(String salaTipo) {
        this.salaTipo = salaTipo;
    }

    public String getSalaNombre() {
        return salaNombre;
    }

    public void setSalaNombre(String salaNombre) {
        this.salaNombre = salaNombre;
    }

    public Long getSalaFilas() {
        return salaFilas;
    }

    public void setSalaFilas(Long salaFilas) {
        this.salaFilas = salaFilas;
    }

    public Long getSalaCol() {
        return salaCol;
    }

    public void setSalaCol(Long salaCol) {
        this.salaCol = salaCol;
    }

    public Long getCineId() {
        return cineId;
    }

    public void setCineId(Long cineId) {
        this.cineId = cineId;
    }
    
    
}
