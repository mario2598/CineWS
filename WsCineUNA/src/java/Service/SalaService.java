/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Model.Sala;
import Model.SalaDto;
import Util.CodigoRespuesta;
import Util.Respuesta;
import java.util.ArrayList;
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
public class SalaService {
    private static final Logger LOG = Logger.getLogger(SalaService.class.getName());
    @PersistenceContext(unitName = "WsCineUNAPU")
    private EntityManager em;
    
    public Respuesta getSala(Long id){
        try {
            Query qryActividad = em.createNamedQuery("Sala.findBySalaId", Sala.class);
            qryActividad.setParameter("salaId", id);
            Sala sala = (Sala) qryActividad.getSingleResult();
            System.out.println("Se ha cargado la sala " + sala.getSalaNombre() 
                        + " junto con sus " + sala.getButacaList().size() + " butacas");
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Sala", new SalaDto(sala));
        } catch (NoResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar una sala única.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe una sala con determinado id.", "getSala NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar una sala única.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar una sala única.", "getSala NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar una sala única.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar una sala única.", "getSala " + ex.getMessage());
        }
    }
    
    public Respuesta getListaSalas(){
        ArrayList<Sala> resultList;
        ArrayList<SalaDto> dtoList;
        try{
            Query qryListSalas = em.createNamedQuery("Sala.findAll", Sala.class);
            resultList = new ArrayList<>(qryListSalas.getResultList());
            dtoList = new ArrayList<>();
            resultList.stream().forEach(sala -> {
                SalaDto newDto = new SalaDto(sala);
                dtoList.add(newDto);
            });
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "SalaList", dtoList);
        } catch (NoResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar la lista de salas.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Ocurrio un error al consultar la lista de salas.", "getListaSalas NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar la lista de salas.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar la lista de salas.", "getListaSalas " + ex.getMessage());
        }
    }
    
    public Respuesta guardarSala(Sala sala){
        try {
            if(sala!=null && sala.getSalaId()!=null && sala.getSalaId()>0){
                Sala salaAux = em.find(Sala.class, sala.getSalaId());
                if(salaAux == null){
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Se intenta actualizar los datos de una sala que no existe en la base de datos", "guardarSala NoResultExeption");
                }
                sala = em.merge(sala);
            } else {
                em.persist(sala);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Sala", sala);
        } catch(NoResultException ex) {
            LOG.log(Level.SEVERE, "Se intenta actualizar los datos de una sala que no existe en la base de datos.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Ocurrio un error guardando una sala.", "guardarSala NoResultExeption");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar una sala.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar una sala.", "guardarSala " + ex.getMessage());
        }
    }
    
    public Respuesta eliminarSala(Long id){
        try{
            Sala salaAux;
            if(id!=null && id>0){
                Query qryId = em.createNamedQuery("Sala.findBySalaId", Sala.class);            
                qryId.setParameter("salaId", id);   
                salaAux = (Sala) qryId.getSingleResult();
                if(salaAux != null){
                    em.remove(salaAux);
                } else {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "La sala que se desea eliminar no existe en la base de datos.", "eliminarSala NoResultExeption");
                }
                em.flush();
                return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "La sala que se desea eliminar no contiene id.", "eliminarSala @param 'Sala' null ID");
            }
        } catch(NoResultException ex){
            LOG.log(Level.SEVERE, "La sala que se desea eliminar no existe en la base de datos.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "La sala que se desea eliminar no existe en la base de datos.", "eliminarSala NoResultExeption");
        } catch(Exception ex){
            LOG.log(Level.SEVERE, "Se ha producido un error eliminando una sala.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Se ha producido un error eliminando una sala.", "eliminarSala " + ex.getMessage());
        }
    }
    
}
