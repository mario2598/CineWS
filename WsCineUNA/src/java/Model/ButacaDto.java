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

@XmlRootElement(name = "ButacaDto")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class ButacaDto {
     private Long butId;
     private Long butColumna;
     private String butEstado;
     private Long butFila;
     private String butImg;
     private String butLetra;
     private Long salaId;
     private String butActiva;
     
     public ButacaDto(Butaca but) {
         this.butId = but.getButId();
         this.butColumna = but.getButColumna();
         this.butEstado = but.getButEstado();
         this.butFila = but.getButFila();
         this.butImg = but.getButImg();
         this.butLetra = but.getButLetra();
         this.salaId = but.getSalaId().getSalaId();
         this.butActiva = but.getButActiva();
    }

    public String getButActiva() {
        return butActiva;
    }

    public void setButActiva(String butActiva) {
        this.butActiva = butActiva;
    }

    public Long getButId() {
        return butId;
    }

    public void setButId(Long butId) {
        this.butId = butId;
    }

    public Long getButColumna() {
        return butColumna;
    }

    public void setButColumna(Long butColumna) {
        this.butColumna = butColumna;
    }

    public String getButEstado() {
        return butEstado;
    }

    public void setButEstado(String butEstado) {
        this.butEstado = butEstado;
    }

    public Long getButFila() {
        return butFila;
    }

    public void setButFila(Long butFila) {
        this.butFila = butFila;
    }

    public String getButImg() {
        return butImg;
    }

    public void setButImg(String butImg) {
        this.butImg = butImg;
    }

    public String getButLetra() {
        return butLetra;
    }

    public void setButLetra(String butLetra) {
        this.butLetra = butLetra;
    }

    public Long getSalaId() {
        return salaId;
    }

    public void setSalaId(Long salaId) {
        this.salaId = salaId;
    }
     
     
}
