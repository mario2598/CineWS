/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mario
 */
@Entity
@Table(name = "BUTACA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Butaca.findAll", query = "SELECT b FROM Butaca b")
    , @NamedQuery(name = "Butaca.findByButId", query = "SELECT b FROM Butaca b WHERE b.butId = :butId")
    , @NamedQuery(name = "Butaca.findByButColumna", query = "SELECT b FROM Butaca b WHERE b.butColumna = :butColumna")
    , @NamedQuery(name = "Butaca.findByButEstado", query = "SELECT b FROM Butaca b WHERE b.butEstado = :butEstado")
    , @NamedQuery(name = "Butaca.findByButFila", query = "SELECT b FROM Butaca b WHERE b.butFila = :butFila")
    , @NamedQuery(name = "Butaca.findByButImg", query = "SELECT b FROM Butaca b WHERE b.butImg = :butImg")
    , @NamedQuery(name = "Butaca.findByButLetra", query = "SELECT b FROM Butaca b WHERE b.butLetra = :butLetra")
    , @NamedQuery(name = "Butaca.findByButActiva", query = "SELECT b FROM Butaca b WHERE b.butActiva = :butActiva")
    , @NamedQuery(name = "Butaca.findBySalaId", query = "SELECT b FROM Butaca b WHERE b.salaId.salaId = :salID")})//cambio query
public class Butaca implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "BUTACA_ID_GENERATOR", sequenceName = "CINEUNA.BUTACA_SEQ01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BUTACA_ID_GENERATOR")
    @Basic(optional = false)
//    @NotNull
    @Column(name = "BUT_ID")
    private Long butId;
    @Column(name = "BUT_COLUMNA")
    private Long butColumna;
//    @Size(max = 1)
    @Column(name = "BUT_ESTADO")
    private String butEstado;
    @Column(name = "BUT_FILA")
    private Long butFila;
//    @Size(max = 100)
    @Column(name = "BUT_IMG")
    private String butImg;
//    @Size(max = 3)
    @Column(name = "BUT_LETRA")
    private String butLetra;
//    @Size(max = 1)
    @Column(name = "BUT_ACTIVA")
    private String butActiva;
    @JoinColumn(name = "SALA_ID", referencedColumnName = "SALA_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Sala salaId;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "butId", fetch = FetchType.LAZY)
    private List<Comprobante> comprobanteList;

    public Butaca() {
        this.comprobanteList = new ArrayList();
    }
    
    public void duplicateData(ButacaDto dto){
        this.butId = dto.getButId();
        this.butColumna = dto.getButColumna();
        this.butEstado = dto.getButEstado();
        this.butFila = dto.getButFila();
        this.butImg = dto.getButImg();
        this.butLetra = dto.getButLetra();
        this.butActiva = dto.getButActiva();
    }

    public Butaca(Long butId) {
        this.butId = butId;
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

    public String getButActiva() {
        return butActiva;
    }

    public void setButActiva(String butActiva) {
        this.butActiva = butActiva;
    }

    @XmlTransient
    public List<Comprobante> getComprobanteList() {
        return comprobanteList;
    }

    public void setComprobanteList(List<Comprobante> comprobanteList) {
        this.comprobanteList = comprobanteList;
    }

    public Sala getSalaId() {
        return salaId;
    }

    public void setSalaId(Sala salaId) {
        this.salaId = salaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (butId != null ? butId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Butaca)) {
            return false;
        }
        Butaca other = (Butaca) object;
        if ((this.butId == null && other.butId != null) || (this.butId != null && !this.butId.equals(other.butId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Butaca[ butId=" + butId + " ]";
    }
    
}
