/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.lang.Long;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.QueryHint;
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
@Table(name = "USUARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
    , @NamedQuery(name = "Usuario.findByUsuId", query = "SELECT u FROM Usuario u WHERE u.usuId = :usuId")
    , @NamedQuery(name = "Usuario.findByUsuUser", query = "SELECT u FROM Usuario u WHERE u.usuUser = :usuUser")
    , @NamedQuery(name = "Usuario.findByUsuNombre", query = "SELECT u FROM Usuario u WHERE u.usuNombre = :usuNombre")
    , @NamedQuery(name = "Usuario.findByUsuPapellido", query = "SELECT u FROM Usuario u WHERE u.usuPapellido = :usuPapellido")
    , @NamedQuery(name = "Usuario.findByUsuSapellido", query = "SELECT u FROM Usuario u WHERE u.usuSapellido = :usuSapellido")
    , @NamedQuery(name = "Usuario.findByUsuPassword", query = "SELECT u FROM Usuario u WHERE u.usuPassword = :usuPassword")
    , @NamedQuery(name = "Usuario.findByUsuEmail", query = "SELECT u FROM Usuario u WHERE u.usuEmail = :usuEmail")
    , @NamedQuery(name = "Usuario.findByUsuIdioma", query = "SELECT u FROM Usuario u WHERE u.usuIdioma = :usuIdioma")
    , @NamedQuery(name = "Usuario.findByUsuEstado", query = "SELECT u FROM Usuario u WHERE u.usuEstado = :usuEstado")
    , @NamedQuery(name = "Usuario.findByUsuAdmin", query = "SELECT u FROM Usuario u WHERE u.usuAdmin = :usuAdmin")
    , @NamedQuery(name = "Usuario.findByUsuNewpassword", query = "SELECT u FROM Usuario u WHERE u.usuNewpassword = :usuNewpassword")
    , @NamedQuery(name = "Usuario.findByUsuNewClave", query = "SELECT u FROM Usuario u WHERE u.usuUser = :usuUser and u.usuNewpassword = :usuNewpassword")
    , @NamedQuery(name = "Usuario.findByUsuClave", query = "SELECT u FROM Usuario u WHERE u.usuUser = :usuUser and u.usuPassword = :usuPassword")
    , @NamedQuery(name = "Usuario.findByUsuCodAct", query = "SELECT u FROM Usuario u WHERE u.usuCodAct = :usuCodAct")})
      
public class Usuario implements Serializable {

   // @Size(max = 8)
    @Column(name = "USU_COD_ACT")
    private String usuCodAct;

    @Column(name = "USU_IDIOMA")
    private Long usuIdioma;
    @OneToMany(mappedBy = "usuId", fetch = FetchType.LAZY)
    private List<Review> reviewList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuId", fetch = FetchType.LAZY)
    private List<Comprobante> comprobanteList;

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
   // @NotNull
    @Column(name = "USU_ID")
    private Long usuId;
    @Basic(optional = false)
  //  @NotNull
  //  @Size(min = 1, max = 30)
    @Column(name = "USU_USER")
    private String usuUser;
    @Basic(optional = false)
   // @NotNull
  //  @Size(min = 1, max = 30)
    @Column(name = "USU_NOMBRE")
    private String usuNombre;
 //   @Size(max = 30)
    @Column(name = "USU_PAPELLIDO")
    private String usuPapellido;
 //   @Size(max = 30)
    @Column(name = "USU_SAPELLIDO")
    private String usuSapellido;
    @Basic(optional = false)
 //   @NotNull
 //   @Size(min = 1, max = 30)
    @Column(name = "USU_PASSWORD")
    private String usuPassword;
    @Basic(optional = false)
 //   @NotNull
 //   @Size(min = 1, max = 80)
    @Column(name = "USU_EMAIL")
    private String usuEmail;
    @Basic(optional = false)
//    @NotNull
 //   @Size(min = 1, max = 1)
    @Column(name = "USU_ESTADO")
    private String usuEstado;
    @Basic(optional = false)
 //   @NotNull
  //  @Size(min = 1, max = 1)
    @Column(name = "USU_ADMIN")
    private String usuAdmin;
    @Basic(optional = false)
  //  @NotNull
  //  @Size(min = 1, max = 4)
    @Column(name = "USU_NEWPASSWORD")
    private String usuNewpassword;
    @Basic(optional = false)
  //  @NotNull
  //  @Size(min = 1, max = 1)
    @Column(name = "USU_CAMBIO")
    private String usuCambio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuId", fetch = FetchType.LAZY)
    private List<Cine> cineList;

    public Usuario() {
    }

    public Usuario(Long usuId) {
        this.usuId = usuId;
    }

    public Usuario(Long usuId, String usuUser, String usuNombre, String usuPassword, String usuEmail, Long usuIdioma, String usuEstado, String usuAdmin, String usuNewpassword, String usuCambio) {
        this.usuId = usuId;
        this.usuUser = usuUser;
        this.usuNombre = usuNombre;
        this.usuPassword = usuPassword;
        this.usuEmail = usuEmail;
        this.usuIdioma = usuIdioma;
        this.usuEstado = usuEstado;
        this.usuAdmin = usuAdmin;
        this.usuNewpassword = usuNewpassword;
        this.usuCambio = usuCambio;
    }
    
    public Usuario(UsuarioDto usu) {
        if(usu.getUsuId() != null){
          this.usuId = usu.getUsuId();
        }
        actualizarUsuario(usu);
    }
    
    public void actualizarUsuario(UsuarioDto usu){
         this.usuUser = usu.getUsuUser();
         this.usuNombre = usu.getUsuNombre();
         this.usuPapellido = usu.getUsuPapellido();
         this.usuSapellido = usu.getUsuSapellido();
         this.usuPassword = usu.getUsuPassword();
         this.usuEmail = usu.getUsuEmail();
         this.usuIdioma = usu.getUsuIdioma();
         this.usuAdmin = usu.getUsuAdmin();
         this.usuNewpassword = usu.getUsuNewpassword();
         this.usuCambio = usu.getUsuCambio();
         this.usuEstado = usu.getUsuEstado();
         this.usuCodAct = usu.getUsuCodAct();
         List<Cine> cList = new ArrayList<>();
          for(CineDto cDto : usu.getCineList()){ //Convertir cines a dto
             Cine c = new Cine();
             cList.add(c);
         }
         this.cineList = cList;
    }

    public Long getUsuId() {
        return usuId;
    }

    public void setUsuId(Long usuId) {
        this.usuId = usuId;
    }

    public String getUsuUser() {
        return usuUser;
    }

    public void setUsuUser(String usuUser) {
        this.usuUser = usuUser;
    }

    public String getUsuNombre() {
        return usuNombre;
    }

    public void setUsuNombre(String usuNombre) {
        this.usuNombre = usuNombre;
    }

    public String getUsuPapellido() {
        return usuPapellido;
    }

    public void setUsuPapellido(String usuPapellido) {
        this.usuPapellido = usuPapellido;
    }

    public String getUsuSapellido() {
        return usuSapellido;
    }

    public void setUsuSapellido(String usuSapellido) {
        this.usuSapellido = usuSapellido;
    }

    public String getUsuPassword() {
        return usuPassword;
    }

    public void setUsuPassword(String usuPassword) {
        this.usuPassword = usuPassword;
    }

    public String getUsuEmail() {
        return usuEmail;
    }

    public void setUsuEmail(String usuEmail) {
        this.usuEmail = usuEmail;
    }

    public Long getUsuIdioma() {
        return usuIdioma;
    }

    public void setUsuIdioma(Long usuIdioma) {
        this.usuIdioma = usuIdioma;
    }

    public String getUsuEstado() {
        return usuEstado;
    }

    public void setUsuEstado(String usuEstado) {
        this.usuEstado = usuEstado;
    }

    public String getUsuAdmin() {
        return usuAdmin;
    }

    public void setUsuAdmin(String usuAdmin) {
        this.usuAdmin = usuAdmin;
    }

    public String getUsuNewpassword() {
        return usuNewpassword;
    }

    public void setUsuNewpassword(String usuNewpassword) {
        this.usuNewpassword = usuNewpassword;
    }

    public String getUsuCambio() {
        return usuCambio;
    }

    public void setUsuCambio(String usuCambio) {
        this.usuCambio = usuCambio;
    }

    @XmlTransient
    public List<Cine> getCineList() {
        return cineList;
    }

    public void setCineList(List<Cine> cineList) {
        this.cineList = cineList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuId != null ? usuId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.usuId == null && other.usuId != null) || (this.usuId != null && !this.usuId.equals(other.usuId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Usuario[ usuId=" + usuId + " ]";
    }

    @XmlTransient
    public List<Comprobante> getComprobanteList() {
        return comprobanteList;
    }

    public void setComprobanteList(List<Comprobante> comprobanteList) {
        this.comprobanteList = comprobanteList;
    }

    @XmlTransient
    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    public String getUsuCodAct() {
        return usuCodAct;
    }

    public void setUsuCodAct(String usuCodAct) {
        this.usuCodAct = usuCodAct;
    }

    
}
