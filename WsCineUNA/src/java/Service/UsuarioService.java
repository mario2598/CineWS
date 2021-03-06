/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Model.Usuario;
import Model.UsuarioDto;
import Util.CodigoRespuesta;
import Util.Respuesta;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author mario
 */
@Stateless
@LocalBean
public class UsuarioService {
    private static final Logger LOG = Logger.getLogger(UsuarioService.class.getName());
    @PersistenceContext(unitName = "WsCineUNAPU")
     private EntityManager em;
    
    
     public Respuesta getUsuarioUsu(String user){
        try {
            Query qryActividad = em.createNamedQuery("Usuario.findByUsuUser", Usuario.class);
            qryActividad.setParameter("usuUser", user);
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Usuario", new UsuarioDto((Usuario) qryActividad.getSingleResult()));

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un usuario con las credenciales ingresadas.", "validarUsuario NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el usuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el usuario.", "validarUsuario NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el usuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el usuario.", "validarUsuario " + ex.getMessage());
        }
    }
    
    public Respuesta validarUsuario(String usuario, String clave) {
        try {
            Integer i = 0;
            Query qryActividad1 = em.createNamedQuery("Usuario.findByUsuNewClave", Usuario.class);
            qryActividad1.setParameter("usuUser", usuario);
            qryActividad1.setParameter("usuNewpassword", clave);
            
            Query qryActividad = em.createNamedQuery("Usuario.findByUsuClave", Usuario.class);
            qryActividad.setParameter("usuUser", usuario);
            qryActividad.setParameter("usuPassword", clave);
            try {
                new UsuarioDto((Usuario) qryActividad.getSingleResult());
            } catch (NoResultException ex) {
                i = 1;
            }
            if(i == 1){
                return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Usuario", new UsuarioDto((Usuario) qryActividad1.getSingleResult()));
            }
            else{
               return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Usuario", new UsuarioDto((Usuario) qryActividad.getSingleResult())); 
            }
            
        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un usuario con las credenciales ingresadas.", "validarUsuario NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el usuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el usuario.", "validarUsuario NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el usuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el usuario.", "validarUsuario " + ex.getMessage());
        }
    }
    
    public Respuesta guardarUsuario(UsuarioDto usuDto) {
        try {
            Usuario usu;
            if (usuDto.getUsuId() != null && usuDto.getUsuId() > 0) {
                usu = em.find(Usuario.class,usuDto.getUsuId());
                if (usu == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el Usuario a modificar.", "guardarUsuario NoResultException");
                }
                usu.actualizarUsuario(usuDto);
                usu = em.merge(usu);
            } else {
                usu = new Usuario(usuDto);
                em.persist(usu);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Usuario", new UsuarioDto(usu));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el usuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el usuario.", "guardarUsuario " + ex.getMessage());
        }
    }
    
     public String activarUsuario(String user) {
             Usuario usu;
             Query qryUsu = em.createNamedQuery("Usuario.findByUsuUser",Usuario.class);            
             qryUsu.setParameter("usuUser",user);             
             usu = (Usuario) qryUsu.getSingleResult();
             usu.setUsuEstado("A");
             usu = em.merge(usu);
             em.flush();
             return usu.getUsuNombre();
    }
}
