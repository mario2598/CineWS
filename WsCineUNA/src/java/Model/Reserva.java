/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.time.ZoneId;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mario
 */
@Entity
@Table(name = "RESERVA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reserva.findAll", query = "SELECT r FROM Reserva r")
    , @NamedQuery(name = "Reserva.findByResId", query = "SELECT r FROM Reserva r WHERE r.resId = :resId")
    , @NamedQuery(name = "Reserva.findByTandaId", query = "SELECT r FROM Reserva r WHERE r.tandaId.tandaId = :tandaId")
    , @NamedQuery(name = "Reserva.findByResEstado", query = "SELECT r FROM Reserva r WHERE r.resEstado = :resEstado")
})
public class Reserva implements Serializable {
    
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "RES_ID_GENERATOR", sequenceName = "CINEUNA.RESERVA_SEQ01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RES_ID_GENERATOR")
    @Basic(optional = false)
  //  @NotNull
    @Column(name = "RES_ID")
    private Long resId;
  //  @Size(max = 1)
    @Column(name = "RES_ESTADO")
    private String resEstado;
    @Column(name = "RES_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date resDate;
    @JoinColumn(name = "TANDA_ID", referencedColumnName = "TANDA_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Tanda tandaId;
    @JoinColumn(name = "BUT_ID", referencedColumnName = "BUT_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Butaca butId;

    public Reserva() {
        
    }

     public Reserva(ReservaDto r) {
         if(r.getResId() != null){
            this.resId = r.getResId();
         }
         this.tandaId = new Tanda(r.getTandaId());
         this.butId = new Butaca(r.getButId());
         this.resEstado = r.getResEstado();        
         this.resDate = Date.from(r.getResDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public Reserva(Long resId) {
        this.resId = resId;
    }

    public Long getResId() {
        return resId;
    }

    public void setResId(Long resId) {
        this.resId = resId;
    }

    public String getResEstado() {
        return resEstado;
    }

    public void setResEstado(String resEstado) {
        this.resEstado = resEstado;
    }

    public Butaca getButId() {
        return butId;
    }

    public void setButId(Butaca butId) {
        this.butId = butId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (resId != null ? resId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reserva)) {
            return false;
        }
        Reserva other = (Reserva) object;
        if ((this.resId == null && other.resId != null) || (this.resId != null && !this.resId.equals(other.resId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Reserva[ resId=" + resId + " ]";
    }

    public Tanda getTandaId() {
        return tandaId;
    }

    public void setTandaId(Tanda tandaId) {
        this.tandaId = tandaId;
    }

    public Date getResDate() {
        return resDate;
    }

    public void setResDate(Date resDate) {
        this.resDate = resDate;
    }
}
