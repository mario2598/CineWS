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
import Model.Review;
import Model.ReviewDto;
import Model.Sala;
import Model.Usuario;
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
public class ReviewService {
    private static final Logger LOG = Logger.getLogger(ComprobanteService.class.getName());
    @PersistenceContext(unitName = "WsCineUNAPU")
    private EntityManager em;
        List<ReviewDto> listDto = new ArrayList<>();
    List<Review> list ;
    
    
    public Respuesta getReview(Long id){
        try {
            Query qryActividad = em.createNamedQuery("Review.findByRevId", Review.class);
            qryActividad.setParameter("revId", id);
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Review", new ReviewDto((Review) qryActividad.getSingleResult()));
        } catch (NoResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar un Review único.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un Review con determinado id.", "getReview NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar una butaca única.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar un Review único.", "getReview NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar una butaca única.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar un Review único.", "getReview " + ex.getMessage());
        }
    }
    
    public Respuesta getListFromMovie(Long id){
        try {
            listDto.clear();
            Query qryActividad = em.createNamedQuery("Review.findByMovieId", Review.class);
            qryActividad.setParameter("movieId", id);
            list = qryActividad.getResultList();
            for(Review c : list){
                ReviewDto cDto = new ReviewDto(c);
                listDto.add(cDto);
            }
            
               return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Review",listDto);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe reviw con los datos ingresados.", "getListFromMovie NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar los comprobantes.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar los review.", "getListFromMovie " + ex.getMessage());
        }
    }
    
     public Respuesta getListFromUsu(Long id){
        try {
            listDto.clear();
            Query qryActividad = em.createNamedQuery("Review.findByUsuId", Review.class);
            qryActividad.setParameter("usuId", id);
            list = qryActividad.getResultList();
            for(Review c : list){
                ReviewDto cDto = new ReviewDto(c);
                listDto.add(cDto);
            }
            
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Review",listDto);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe reviw con los datos ingresados.", "getListFromUsu NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar los comprobantes.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar los review.", "getListFromUsu " + ex.getMessage());
        }
    }
   
      public Respuesta guardarReview(ReviewDto rDto) {
        try {
            Review r;
            Usuario usu = null;
            Movie movie = null;
            try {
                usu = em.find(Usuario.class,rDto.getUsuId());               
                movie = em.find(Movie.class,rDto.getMovieId());    
            } catch (Exception e) {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Error guardando review.", "guardarReview NoResultException");
            }
            
            if (rDto.getRevId() != null && rDto.getRevId()> 0) {
                r = em.find(Review.class,rDto.getRevId());
                if (r == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el review a modificar.", "guardarReview NoResultException");
                }
                r = new Review(rDto);
                r.setMovieId(movie);
                r.setUsuId(usu);             
                r= em.merge(r);
                } else {
         
                r = new Review(rDto);
                r.setMovieId(movie);
                r.setUsuId(usu);                
                em.persist(r);  
                }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Review", new ReviewDto(r));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el review.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el review.", "guardarReview " + ex.getMessage());
        }
    }
      
    public Respuesta eliminarReview(Long id){
        try{
            Review rAux;
            if(id!=null && id>0){
                Query qryActividad = em.createNamedQuery("Review.findByRevId", Review.class);
                qryActividad.setParameter("revId", id);   
                rAux = (Review) qryActividad.getSingleResult();
                if(rAux != null){
                    em.remove(rAux);
                } else {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "El Review que se desea eliminar no existe en la base de datos", "eliminarReview NoResultExeption");
                }
                em.flush();
                if(!getReview(rAux.getRevId()).getEstado()){
                    return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
                } else {
                    return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Se ha producido un error eliminando un Review.", "eliminarReview ");
                }
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "El Review que se desea eliminar no contiene id", "eliminarReview @param 'Review' null ID");
            }
        } catch(NoResultException ex){
            LOG.log(Level.SEVERE, "El Review que se desea eliminar no existe en la base de datos", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "El Review que se desea eliminar no existe en la base de datos", "eliminarReview NoResultExeption");
        } catch(Exception ex){
            LOG.log(Level.SEVERE, "Se ha producido un error eliminando el review.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Se ha producido un error eliminando el Review.", "eliminarReview " + ex.getMessage());
        }
    }
}
