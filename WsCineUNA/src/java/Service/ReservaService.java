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
            Reserva reserva;
            if (rDto.getResId()!= null && rDto.getResId() > 0) {//si tra id
                reserva = em.find(Reserva.class,rDto.getResId());//busca la película con ese id para actualizar
                if (reserva == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró la reserva a modificar.", "guardarReserva NoResultException");
                }
                reserva = new Reserva(rDto);
                Butaca b = new Butaca(rDto.getButId());
                reserva.setButId(b);
                Tanda t = new Tanda(rDto.getTandaId());
                reserva.setTandaId(t);
                reserva = em.merge(reserva);//actualiza
            } else {
               
                reserva= new Reserva(rDto);
                Butaca b = new Butaca(rDto.getButId());
                reserva.setButId(b);
                Tanda t = new Tanda(rDto.getTandaId());
                reserva.setTandaId(t);
                em.persist(reserva);//periste
            }
            em.flush();//refresca
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Reserva", new ReservaDto(reserva));    
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
}
