/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Model.Movie;
import Model.MovieDto;
import Model.Usuario;
import Model.UsuarioDto;
import Util.CodigoRespuesta;
import Util.Respuesta;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;

/**
 *
 * @author mario
 */
@Stateless
@LocalBean
public class MovieService {
    private static final Logger LOG = Logger.getLogger(UsuarioService.class.getName());
    @PersistenceContext(unitName = "WsCineUNAPU")
     private EntityManager em;
    List<MovieDto> listDto = new ArrayList<>();
    List<Movie> list ;
    
    public Respuesta getMovie(Long id){
        try{
            Query qryActividad = em.createNamedQuery("Movie.findByMovieId", Movie.class);
            qryActividad.setParameter("movieId", id);
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Movie", new MovieDto((Movie) qryActividad.getSingleResult()));
        }
        catch(NoResultException ex){
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe una Película con las credenciales ingresadas.", "validarPelícula NoResultException");
        }
        catch(NonUniqueResultException ex){
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar la película.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar la película.", "validarUsuario NonUniqueResultException");
        }
        catch(Exception ex){
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar la película.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar la película.", "validarUsuario " + ex.getMessage());
        }
    }
    
    /**
     * obtiene una lista de películas de acuerdo al estado
     * @param estado  P = DISPONIBLE PRONTO, C = EN CARTELERA, I = INACTIVA
     * @return 
     */
    public Respuesta getListMovies(String estado){
        try {
            Query qryActividad = em.createNamedQuery("Movie.findByMovieEstado", Movie.class);
            qryActividad.setParameter("movieEstado", estado);
            list= qryActividad.getResultList();
            list.stream().map((m) -> new MovieDto(m)).forEachOrdered((mDto) -> {
                listDto.add(mDto);
            });
            
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Movie",listDto);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe Películas con ese estado.", "getListFromMovie NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar las películas con ese estado.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar las películas con ese estado.", "getListFromMovie " + ex.getMessage());
        }
    }

}
