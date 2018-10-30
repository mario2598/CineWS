/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mario
 */
@Entity
@Table(name = "SALA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sala.findAll", query = "SELECT s FROM Sala s")
    , @NamedQuery(name = "Sala.findBySalaId", query = "SELECT s FROM Sala s WHERE s.salaId = :salaId")
    , @NamedQuery(name = "Sala.findBySalaEstado", query = "SELECT s FROM Sala s WHERE s.salaEstado = :salaEstado")
    , @NamedQuery(name = "Sala.findBySalaImgfondo", query = "SELECT s FROM Sala s WHERE s.salaImgfondo = :salaImgfondo")})
public class Sala implements Serializable {

    @Column(name = "SALA_COL")
    private Long salaCol;
    @Column(name = "SALA_FILAS")
    private Long salaFilas;
    @Column(name = "SALA_PANT")
    private Long salaPant;
    @OneToMany(mappedBy = "salaId", fetch = FetchType.LAZY)
    private List<Tanda> tandaList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "salaId", fetch = FetchType.LAZY)
    private List<Comprobante> comprobanteList;
    @OneToMany(mappedBy = "salaId", fetch = FetchType.LAZY)
    private List<Butaca> butacaList;

    @ManyToMany(mappedBy = "salaList", fetch = FetchType.LAZY)
    private List<Movie> movieList;

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
 //   @NotNull
    @Column(name = "SALA_ID")
    private Long salaId;
    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 1)
    @Column(name = "SALA_ESTADO")
    private String salaEstado;
//    @Size(max = 100)
    @Column(name = "SALA_IMGFONDO")
    private String salaImgfondo;
    @OneToMany(mappedBy = "salaId", fetch = FetchType.LAZY)
    private List<Detalle> detalleList;
    @JoinColumn(name = "CINE_ID", referencedColumnName = "CINE_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Cine cineId;

    public Sala() {
    }

    public Sala(Long salaId) {
        this.salaId = salaId;
    }

    public Sala(Long salaId, String salaEstado) {
        this.salaId = salaId;
        this.salaEstado = salaEstado;
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
    public List<Detalle> getDetalleList() {
        return detalleList;
    }

    public void setDetalleList(List<Detalle> detalleList) {
        this.detalleList = detalleList;
    }

    public Cine getCineId() {
        return cineId;
    }

    public void setCineId(Cine cineId) {
        this.cineId = cineId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (salaId != null ? salaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sala)) {
            return false;
        }
        Sala other = (Sala) object;
        if ((this.salaId == null && other.salaId != null) || (this.salaId != null && !this.salaId.equals(other.salaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Sala[ salaId=" + salaId + " ]";
    }

    @XmlTransient
    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }

    @XmlTransient
    public List<Comprobante> getComprobanteList() {
        return comprobanteList;
    }

    public void setComprobanteList(List<Comprobante> comprobanteList) {
        this.comprobanteList = comprobanteList;
    }

    @XmlTransient
    public List<Butaca> getButacaList() {
        return butacaList;
    }

    public void setButacaList(List<Butaca> butacaList) {
        this.butacaList = butacaList;
    }

    public Long getSalaCol() {
        return salaCol;
    }

    public void setSalaCol(Long salaCol) {
        this.salaCol = salaCol;
    }

    public Long getSalaFilas() {
        return salaFilas;
    }

    public void setSalaFilas(Long salaFilas) {
        this.salaFilas = salaFilas;
    }

    public Long getSalaPant() {
        return salaPant;
    }

    public void setSalaPant(Long salaPant) {
        this.salaPant = salaPant;
    }

    @XmlTransient
    public List<Tanda> getTandaList() {
        return tandaList;
    }

    public void setTandaList(List<Tanda> tandaList) {
        this.tandaList = tandaList;
    }
    
}
