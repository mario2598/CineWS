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
@XmlRootElement(name = "ReservaDto")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class ReservaDto {
    private String resEstado;
    private TandaDto tandaId;
    private Long resId;
    private ButacaDto butId;

    public ReservaDto() {
    }
    
    public ReservaDto(Reserva r) {
        this.butId = new ButacaDto(r.getButId());
        this.resEstado = r.getResEstado();
        this.tandaId = new TandaDto(r.getTandaId());
        this.resId = r.getResId();
    }
    
    public String getResEstado() {
        return resEstado;
    }

    public void setResEstado(String resEstado) {
        this.resEstado = resEstado;
    }

    public TandaDto getTandaId() {
        return tandaId;
    }

    public void setTandaId(TandaDto tandaId) {
        this.tandaId = tandaId;
    }

    public Long getResId() {
        return resId;
    }

    public void setResId(Long resId) {
        this.resId = resId;
    }

    public ButacaDto getButId() {
        return butId;
    }

    public void setButId(ButacaDto butId) {
        this.butId = butId;
    }
    
    
    
}
