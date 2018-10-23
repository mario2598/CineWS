/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mario
 */
@XmlRootElement(name = "UsuarioDto")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class UsuarioDto {
    //Atributos
    private Long usuId; 
    private String usuUser;
    private String usuNombre;
    private String usuPapellido;
    private String usuSapellido;
    private String usuPassword; 
    private String usuEmail;
    private Long usuIdioma;
    private String usuEstado;
    private String usuAdmin; 
    private String usuNewpassword; 
    private String usuCambio;
    private List<CineDto> cineList;
    
    //Constructores

    public UsuarioDto() {
    }
     public UsuarioDto(Usuario usu) {
         this.usuId = usu.getUsuId();
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
       /*  for(Cine c : usu.getCineList()){ //Convertir cines a dto
             CineDto cDto = new CineDto();
             cineList.add(cDto);
         }*/
    }
    
     //Metodos

    public Long getUsuId() {
        if(usuId == null){
            return null;
        }
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

    public List<CineDto> getCineList() {
        return cineList;
    }

    public void setCineList(List<CineDto> cineList) {
        this.cineList = cineList;
    }
     
     

    
    
    
    
}
