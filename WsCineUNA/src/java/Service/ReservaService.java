/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Model.Butaca;
import Model.Movie;
import Model.MovieDto;
import Model.Reserva;
import Model.ReservaDto;
import Model.Sala;
import Model.Tanda;
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
public class ReservaService {
    private static final Logger LOG = Logger.getLogger(UsuarioService.class.getName());
    @PersistenceContext(unitName = "WsCineUNAPU")
     private EntityManager em;
    
    
     public Respuesta getReserva(Long id){
        try{
            Query qryActividad = em.createNamedQuery("Reserva.findByResId",Reserva.class);
            qryActividad.setParameter("resId", id);
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Reserva", new ReservaDto((Reserva) qryActividad.getSingleResult()));
        }
        catch(NoResultException ex){
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe una reserva con las credenciales ingresadas.", "getReserva NoResultException");
        }
        catch(NonUniqueResultException ex){
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar la reserva.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar la reserva.", "getReserva NonUniqueResultException");
        }
        catch(Exception ex){
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar la reserva.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar la reserva.", "getReserva " + ex.getMessage());
        }
    }
     
     public Respuesta guardarReserva(ReservaDto rDto) {
        try {
            Reserva r;
            if (rDto.getResId()!= null && rDto.getResId() > 0) {//si tra id
                r = em.find(Reserva.class,rDto.getResId());//busca la película con ese id para actualizar
                if (r == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró la reserva a modificar.", "guardarReserva NoResultException");
                }
                r = new Reserva(rDto);//actualiza la movie si ya existía
                r = em.merge(r);//actualiza
            } else {
               
                r = new Reserva(rDto);
                em.persist(r);//periste
            }
            em.flush();//refresca
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Reserva", new ReservaDto(r));    
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar la Reserva.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar la reserva.", "guardarReserva " + ex.getMessage());
        }
    }
        public Respuesta getListReserva(Long id){
        try {
            //limpiarListas();
            Query qryActividad = em.createNamedQuery("Reserva.findByTandaId", Reserva.class);
            qryActividad.setParameter("tandaId", id);
            List<Reserva> reservas = qryActividad.getResultList();
            List<ReservaDto> reservasDto = new ArrayList<>();
            for (Reserva r : reservas) {
                reservasDto.add(new ReservaDto(r));
            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "ReservaList",reservasDto);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe reservas cen esa tanda.", "getListReserva NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar las reservas con esa tanda.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar las reservas con esa tanda.", "getListReserva " + ex.getMessage());
        }
    }
        
    public Respuesta eliminarListaReservas(Long tandaID){
        try{
            Tanda tAux = em.find(Tanda.class, tandaID);
            if(tAux != null){
                if(tAux.getReservaList()!=null){
                    for(Reserva r : tAux.getReservaList()){
                       eliminarReserva(r.getResId());
                    }
                }
                em.flush();
                return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "El id de la tanda proporcionado no corresponde a ninguna tanda.", "eliminarListaReservas NoResultExeption for tanda");
            }
        } catch(NoResultException ex){
            LOG.log(Level.SEVERE, "El id de la sala proporcionado no corresponde a ninguna sala.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "El id de la tanda proporcionado no corresponde a ninguna tanda.", "eliminarListaReservas NoResultExeption for tanda");
        } catch(Exception ex){
            LOG.log(Level.SEVERE, "Se ha producido un error eliminando la lista de reservas.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Se ha producido un error eliminando la lista de reservas.", ex.getMessage());
        }
    }
    
    
    public Respuesta eliminarReserva(Long id){
        try{
            Reserva rAux;
            if(id!=null && id>0){
                Query qryId = em.createNamedQuery("Reserva.findByResId", Reserva.class);            
                qryId.setParameter("resId", id);   
                rAux = (Reserva) qryId.getSingleResult();
                if(rAux != null){
                    em.remove(rAux);
                } else {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "La reserva que se desea eliminar no existe en la base de datos", "eliminarReserva NoResultExeption");
                }
            }
            em.flush(); 
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch(NoResultException ex){
            LOG.log(Level.SEVERE, "La reserva que se desea eliminar no existe en la base de datos", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "La reserva que se desea eliminar no existe en la base de datos", "eliminarReserva NoResultExeption");
        } catch(Exception ex){
            LOG.log(Level.SEVERE, "Se ha producido un error eliminando una butaca.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Se ha producido un error eliminando una reserva.", "eliminarReserva " + ex.getMessage());
        }
    }
}
