/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Model.Butaca;
import Model.ButacaDto;
import Model.Sala;
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
 * @author Chris
 */
@Stateless
@LocalBean
public class ButacaService {
    private static final Logger LOG = Logger.getLogger(ButacaService.class.getName());
    @PersistenceContext(unitName = "WsCineUNAPU")
    private EntityManager em;
    
    public Respuesta getButaca(Long id){
        try {
            em.flush();
            em.getEntityManagerFactory().getCache().evictAll();
            Query qryActividad = em.createNamedQuery("Butaca.findByButId", Butaca.class);
            qryActividad.setParameter("butId", id);
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Butaca", new ButacaDto((Butaca) qryActividad.getSingleResult()));
        } catch (NoResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar una butaca única.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe una butaca con determinado id.", "getButaca NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar una butaca única.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar una butaca única.", "getButaca NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar una butaca única.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar una butaca única.", "getButaca " + ex.getMessage());
        }
    }
    
    /**
     * Consulta lista de butacas de una sala en especifico
     * @param salaId id de la sala propietaria de la lista de butacas
     * @return lista de tipo butacaDto
     */
    public Respuesta getListaButacas(Long salaId){
        em.flush();
        em.getEntityManagerFactory().getCache().evictAll();
        ArrayList<Butaca> resultList;
        ArrayList<ButacaDto> dtoList;
        try{
            Query qryListButacas = em.createNamedQuery("Butaca.findBySalaId", Butaca.class);
            qryListButacas.setParameter("salID", salaId);
            resultList = new ArrayList<>(qryListButacas.getResultList());
            dtoList = new ArrayList<>();
            resultList.stream().forEach(butaca -> {
                ButacaDto newDto = new ButacaDto(butaca);
                dtoList.add(newDto);
            });
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "ButacaList", dtoList);
        } catch (NoResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar la lista de butacas realcionadas con la sala ID: "+ salaId +".", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe butacas relacionadas con la sala ID: "+ salaId +".", "getListaButacas NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar la lista de butacas realcionadas con la sala ID: "+ salaId +".", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar la lista de butacas realcionadas con la sala ID: "+ salaId +".", "getListaButacas " + ex.getMessage());
        }
    }
    
    public Respuesta guardarButaca(Butaca butaca){
        try {
            if(butaca!=null && butaca.getButId()!=null && butaca.getButId()>0){
                Butaca butacaAux = em.find(Butaca.class, butaca.getButId());
                if(butacaAux == null){
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Se intenta actualizar los datos de una butaca que no existe en la base de datos", "guardarButaca NoResultExeption");
                }
                butaca = em.merge(butaca);
            } else {
                em.persist(butaca);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Butaca", butaca);
        } catch(NoResultException ex) {
            LOG.log(Level.SEVERE, "Se intenta actualizar los datos de una butaca que no existe en la base de datos.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Ocurrio un error guardando una butaca.", "guardarButaca NoResultExeption");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar una butaca.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar una butaca.", "guardarButaca " + ex.getMessage());
        }
    }
    
    public Respuesta eliminarButaca(Long id){
        try{
            Butaca butacaAux;
            if(id!=null && id>0){
                Query qryId = em.createNamedQuery("Butaca.findByButId", Butaca.class);            
                qryId.setParameter("butId", id);   
                butacaAux = (Butaca) qryId.getSingleResult();
                if(butacaAux != null){
                    em.remove(butacaAux);
                } else {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "La butaca que se desea eliminar no existe en la base de datos", "eliminarButaca NoResultExeption");
                }
                em.flush();
                if(!getButaca(butacaAux.getButId()).getEstado()){
                    return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
                } else {
                    return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Se ha producido un error eliminando una butaca.", "eliminarButaca ");
                }
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "La butaca que se desea eliminar no contiene id", "eliminarButaca @param 'butaca' null ID");
            }
        } catch(NoResultException ex){
            LOG.log(Level.SEVERE, "La butaca que se desea eliminar no existe en la base de datos", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "La butaca que se desea eliminar no existe en la base de datos", "eliminarButaca NoResultExeption");
        } catch(Exception ex){
            LOG.log(Level.SEVERE, "Se ha producido un error eliminando una butaca.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Se ha producido un error eliminando una butaca.", "eliminarButaca " + ex.getMessage());
        }
    }
    
    /**
     * Elimina la lista de butacas de una determinada sala
     * @param salaID
     * @return 
     */
    public Respuesta eliminarListaButacas(Long salaID){
        try{
            Sala salaAux = em.find(Sala.class, salaID);
            if(salaAux != null){
                if(salaAux.getButacaList()!=null){
                    for(Butaca b : salaAux.getButacaList()){
                        eliminarButaca(b.getButId());
                    }
                }
                em.flush();
                return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "El id de la sala proporcionado no corresponde a ninguna sala.", "eliminarListaButacas NoResultExeption for Sala");
            }
        } catch(NoResultException ex){
            LOG.log(Level.SEVERE, "El id de la sala proporcionado no corresponde a ninguna sala.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "El id de la sala proporcionado no corresponde a ninguna sala.", "eliminarListaButacas NoResultExeption for Sala");
        } catch(Exception ex){
            LOG.log(Level.SEVERE, "Se ha producido un error eliminando la lista de butacas.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Se ha producido un error eliminando la lista de butacas.", ex.getMessage());
        }
    }
    
}
