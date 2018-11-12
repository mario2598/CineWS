/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Model.Cine;
import Model.CineDto;
import Util.CodigoRespuesta;
import Util.Respuesta;
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
public class CineService {
    private static final Logger LOG = Logger.getLogger(CineService.class.getName());
    @PersistenceContext(unitName = "WsCineUNAPU")
    private EntityManager em;
    
    /**
     * Consulta del cine, no recibe parametro porque solo existe 1 cine siempre
     * @return 
     */
    public Respuesta getCine(){
        try{
            Query qryCine = em.createNamedQuery("Cine.findAll", Cine.class);
            List<Cine> list = qryCine.getResultList();
            Cine cine = list.get(0);
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Cine", new CineDto(cine));
        } catch (NoResultException ex) {
            LOG.log(Level.SEVERE, "No existe ningún cine registrado en la base de datos.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe ningún cine registrado en la base de datos.", "getCine NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el cine.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el cine.", "getCine NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el cine.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el cine.", "getCine " + ex.getMessage());
        }
    }
    
    /**
     * Solo guarda cuando el dto trae un id que ya existe dentro de la base de datos
     * Desde el cliente no se pueden crear varios cines, solo modificar el existente
     * @param cine
     * @return 
     */
    public Respuesta guardarCine(Cine cine) {
        try {
            Cine cineAux;
            if (cine!=null && cine.getCineId()!= null && cine.getCineId() > 0) {
                cineAux = em.find(Cine.class, cine.getCineId());
                if (cineAux == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el Cine a modificar.", "guardarCine NoResultException");
                }
                cineAux = em.merge(cineAux);
                em.flush();

                return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Cine", new CineDto(cineAux));
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Se ha producido un error el guardar el cine", "guardarCine @param 'Cine' null ID");
            }
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el cine.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el cine.", "guardarCine " + ex.getMessage());
        }
    }
    
}
