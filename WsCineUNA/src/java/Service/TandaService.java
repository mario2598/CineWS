/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Model.Butaca;
import Model.ButacaDto;
import Model.Cine;
import Model.CineDto;
import Model.Tanda;
import Model.TandaDto;
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
public class TandaService {
    
    private static final Logger LOG = Logger.getLogger(CineService.class.getName());
    @PersistenceContext(unitName = "WsCineUNAPU")
    private EntityManager em;
    
    public Respuesta getTanda(Long id){
        try {
            Query qryId = em.createNamedQuery("Tanda.findByTandaId", Tanda.class);            
            qryId.setParameter("tandaId", id);   
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Tanda", new TandaDto((Tanda) qryId.getSingleResult()));
        } catch (NoResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar una tanda única.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe una tanda con determinado id.", "getTanda NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar una butaca única.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar una tanda única.", "getTanda NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar una butaca única.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar una tanda única.", "getTanda " + ex.getMessage());
        }
    }
    
    public Respuesta getListTandaS(Long salaId){
        ArrayList<Tanda> resultList;
        ArrayList<TandaDto> dtoList;
        try{
            Query qryListTandas = em.createNamedQuery("Tanda.findBySalaId", Tanda.class);
            qryListTandas.setParameter("salaId", salaId);
            resultList = new ArrayList<>(qryListTandas.getResultList());
            dtoList = new ArrayList<>();
            resultList.stream().forEach(tanda -> {
                TandaDto newDto = new TandaDto(tanda);
                dtoList.add(newDto);
            });
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "TandaListS", dtoList);
        } catch (NoResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar la lista de tandas realcionadas con la sala ID: "+ salaId +".", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe butacas relacionadas con la sala ID: "+ salaId +".", "getListaButacas NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar la lista de butacas realcionadas con la sala ID: "+ salaId +".", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar la lista de butacas realcionadas con la sala ID: "+ salaId +".", "getListaButacas " + ex.getMessage());
        }
    }
    
    public Respuesta getListTandaM(Long movieId){
        ArrayList<Tanda> resultList;
        ArrayList<TandaDto> dtoList;
        try{
            Query qryListButacas = em.createNamedQuery("Tanda.findByMovieId", Tanda.class);
            qryListButacas.setParameter("movieId", movieId);
            resultList = new ArrayList<>(qryListButacas.getResultList());
            dtoList = new ArrayList<>();
            /*resultList.stream().forEach(tanda -> {
                TandaDto newDto = new TandaDto(tanda);
                dtoList.add(newDto);
            });*/
            for(Tanda t: resultList){
                System.out.println("agregando tanda");
                dtoList.add(new TandaDto(t));
            }
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "TandaListM", dtoList);
        } catch (NoResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar la lista de tandas realcionadas con la sala ID: "+ movieId +".", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe tnadas relacionadas con la movie ID: "+ movieId +".", "getListaButacas NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar la lista de tandas tandas con la movie ID: "+ movieId +".", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar la lista de tnadas realcionadas con la movie ID: "+ movieId +".", "getListaButacas " + ex.getMessage());
        }
    }
    
    public Respuesta guardarTanda(Tanda tanda) {
        try {
            Tanda tandaAux;
            if (tanda!=null && tanda.getTandaId()!= null && tanda.getTandaId() > 0) {
                tandaAux = em.find(Tanda.class, tanda.getTandaId());
                if (tandaAux == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró la tanda a modificar.", "guardarTanda NoResultException");
                }
                tandaAux = em.merge(tandaAux);
                return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Tanda", new TandaDto(tandaAux));
            } else {
                 em.persist(tanda);
            }
             em.flush();
             return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Tanda", new TandaDto(tanda));             
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar la tanda.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar la tanda.", "guardarTanda " + ex.getMessage());
        }
    }
        
    public Respuesta eliminarTanda(Long id){
        try{
            Tanda tandaAux;
            if(id!=null && id>0){
                Query qryId = em.createNamedQuery("Tanda.findByTandaId", Tanda.class);            
                qryId.setParameter("tandaId", id);   
                tandaAux = (Tanda) qryId.getSingleResult();
                if(tandaAux != null){
                    em.remove(tandaAux);
                } else {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "La Tanda que se desea eliminar no existe en la base de datos", "eliminarTanda NoResultExeption");
                }
                em.flush();
                if(!getTanda(tandaAux.getTandaId()).getEstado()){
                    return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
                } else {
                    return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Se ha producido un error eliminando una tanda.", "eliminarTanda ");
                }
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "La Tanda que se desea eliminar no contiene id", "eliminarTanda @param 'tanda' null ID");
            }
        } catch(NoResultException ex){
            LOG.log(Level.SEVERE, "La tanda que se desea eliminar no existe en la base de datos", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "La tanda que se desea eliminar no existe en la base de datos", "eliminarTanda 11111111111111111NoResultExeption");
        } catch(Exception ex){
            LOG.log(Level.SEVERE, "Se ha producido un error eliminando una tanda.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Se ha producido un error eliminando una tanda.", "eliminarTanda " + ex.getMessage());
        }
    }
    
    /**
     * guardar Tanda a partir de un dto
     */
    public Respuesta guardarTanda(TandaDto tDto) {
        try {
            Tanda tanda;
            if (tDto.getTandaId()!= null && tDto.getTandaId()> 0) {//si tra id
                tanda = em.find(Tanda.class,tDto.getTandaId());//busca la película con ese id para actualizar
                if (tanda == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró la tanda a modificar.", "guardarTanda NoResultException");
                }
                tanda.actualizarTanda(tDto);//actualiza la tanda si ya existía
                tanda = em.merge(tanda);//actualiza
            } else {
                tanda = new Tanda(tDto);//crea una nueva a partir del Dto
                em.persist(tanda);//periste
            }
            em.flush();//refresca
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Tanda", new TandaDto(tanda));    
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar la tanda.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar la tanda.", "guardarTanda " + ex.getMessage());
        }
    }
}
