/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Model.Butaca;
import Model.Comprobante;
import Model.ComprobanteDto;
import Model.Movie;
import Model.Sala;
import Model.Tanda;
import Model.Usuario;
import Model.UsuarioDto;
import Util.CodigoRespuesta;
import Util.Respuesta;
import java.util.ArrayList;
import java.util.List;
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
public class ComprobanteService {
    private static final Logger LOG = Logger.getLogger(ComprobanteService.class.getName());
    @PersistenceContext(unitName = "WsCineUNAPU")
     private EntityManager em;
    List<ComprobanteDto> listDto = new ArrayList<>();
    List<Comprobante> list ;
    
    
    public Respuesta getComp(Long id){
        try {
            Query qryActividad = em.createNamedQuery("Comprobante.findByCompId", Comprobante.class);
            qryActividad.setParameter("compId", id);
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Comprobante", new ComprobanteDto((Comprobante) qryActividad.getSingleResult()));
        } catch (NoResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar un Comprobante único.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un Comprobante con determinado id.", "getComp NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar una butaca única.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar un Comprobante único.", "getComp NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar una butaca única.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar un Comprobante único.", "getComp " + ex.getMessage());
        }
    }
    
    public Respuesta getListFromMovie(Long id){
        try {
            listDto.clear();
            Query qryActividad = em.createNamedQuery("Comprobante.findByMovieId", Comprobante.class);
            qryActividad.setParameter("movieId", id);
            list = qryActividad.getResultList();
            for(Comprobante c : list){
                ComprobanteDto cDto = new ComprobanteDto(c);
                listDto.add(cDto);
            }
            
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Comprobante",listDto);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe comprobantes con los datos ingresados.", "getListFromMovie NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar los comprobantes.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar los comprobantes.", "getListFromMovie " + ex.getMessage());
        }
    }
    
    public Respuesta getListFromSala(Long id){
        try {
            listDto.clear();
            Query qryActividad = em.createNamedQuery("Comprobante.findBySalaId", Comprobante.class);
            qryActividad.setParameter("salaId", id);
            list = qryActividad.getResultList();
            for(Comprobante c : list){
                ComprobanteDto cDto = new ComprobanteDto(c);
                listDto.add(cDto);
            }
            
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Comprobante",listDto);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe comprobantes con los datos ingresados.", "getListFromMovie NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar los comprobantes.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar los comprobantes.", "getListFromMovie " + ex.getMessage());
        }
    }
    
    
     public Respuesta guardarComp(ComprobanteDto compDto) {
        try {
            Comprobante comp;
            Usuario usu = null;
            Sala sala = null;
            Movie movie = null;
            Butaca but = null;
            try {
                usu = em.find(Usuario.class,compDto.getUsuId());
                sala = em.find(Sala.class,compDto.getSalaId());
                movie = em.find(Movie.class,compDto.getMovieId());
                but = em.find(Butaca.class,compDto.getButId());      
            } catch (Exception e) {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Error guardando comprobante.", "guardarComp NoResultException");
            }
            
            if (compDto.getCompId() != null && compDto.getCompId() > 0) {
                comp = em.find(Comprobante.class,compDto.getCompId());
                if (comp == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el Comprobante a modificar.", "guardarComp NoResultException");
                }
                comp = new Comprobante(compDto);
                comp.setButId(but);
                comp.setMovieId(movie);
                comp.setSalaId(sala);
                comp.setUsuId(usu);              
                comp= em.merge(comp);
                } else {
         
                comp = new Comprobante(compDto);
                comp.setButId(but);
                comp.setMovieId(movie);
                comp.setSalaId(sala);
                comp.setUsuId(usu);   
                em.persist(comp);  
                }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Comprobante", new ComprobanteDto(comp));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el comprobante.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el comprobante.", "guardarComp " + ex.getMessage());
        }
    }
     
     public Respuesta eliminarComp(Long id){
        try{
            Comprobante compAux;
            if(id!=null && id>0){
                Query qryId = em.createNamedQuery("Comprobante.findByCompId", Comprobante.class);            
                qryId.setParameter("compId", id);   
                compAux = (Comprobante) qryId.getSingleResult();
                if(compAux != null){
                    em.remove(compAux);
                } else {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "El comprobante que se desea eliminar no existe en la base de datos", "eliminarTanda NoResultExeption");
                }
                em.flush();
                if(!getComp(compAux.getCompId()).getEstado()){
                    return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
                } else {
                    return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Se ha producido un error eliminando un comprobante.", "eliminarComp ");
                }
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "El comprobante que se desea eliminar no contiene id", "eliminarComp @param 'tanda' null ID");
            }
        } catch(NoResultException ex){
            LOG.log(Level.SEVERE, "El comprobante que se desea eliminar no existe en la base de datos", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "El comprobante que se desea eliminar no existe en la base de datos", "eliminarComp NoResultExeption");
        } catch(Exception ex){
            LOG.log(Level.SEVERE, "Se ha producido un error eliminando e comprobante.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Se ha producido un error eliminando el comprobante.", "eliminarTanda " + ex.getMessage());
        }
    }
}
