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
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author mario
 */
@XmlRootElement(name = "CineDto")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class CineDto { 
    
    private Long cineId;
    private String cineNombre;
    private Long cineTel;
    private String cineEmail;
    private Long cineAbre;
    private Long cineCierra;
    @XmlTransient
    private List<SalaDto> salaList = new ArrayList<>();
    @XmlTransient
    private List<Usuario> usuarioList = new ArrayList<>();;
    
    public CineDto(){
        
    }
    
     public CineDto(Cine c) {
         this.cineId = c.getCineId();
         this.cineNombre = c.getCineNombre();
         this.cineTel = c.getCineTel();
         this.cineAbre = c.getCineAbre();
         this.cineCierra = c.getCineCierra();        
    }

    public void convList(Cine c){
        for(Sala s : c.getSalaList()){ 
             SalaDto sDto = new SalaDto(s);
             salaList.add(sDto);
         }
    } 
     
    public Long getCineId() {
        return cineId;
    }

    public void setCineId(Long cineId) {
        this.cineId = cineId;
    }

    public String getCineNombre() {
        return cineNombre;
    }

    public void setCineNombre(String cineNombre) {
        this.cineNombre = cineNombre;
    }

    public Long getCineTel() {
        return cineTel;
    }

    public void setCineTel(Long cineTel) {
        this.cineTel = cineTel;
    }

    public String getCineEmail() {
        return cineEmail;
    }

    public void setCineEmail(String cineEmail) {
        this.cineEmail = cineEmail;
    }

    public Long getCineAbre() {
        return cineAbre;
    }

    public void setCineAbre(Long cineAbre) {
        this.cineAbre = cineAbre;
    }

    public Long getCineCierra() {
        return cineCierra;
    }

    public void setCineCierra(Long cineCierra) {
        this.cineCierra = cineCierra;
    }
    
    @XmlTransient
    public List<SalaDto> getSalaList() {
        return salaList;
    }

    public void setSalaList(List<SalaDto> salaList) {
        this.salaList = salaList;
    }
    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }
    
    
}
