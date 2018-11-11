/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Model.Movie;
import Model.MovieDto;
import Util.CodigoRespuesta;
import Util.Respuesta;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.persistence.Query;
import javax.servlet.ServletContext;

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
     @Inject
     ServletContext context;
    
    /**
     * obtiene película a partir de un id
     * @param date1
     * @param date2
     * @param id
     * @return 
     */
    
     public Movie reporteMovid(Long id) {
        try {
            Movie m;
            Query qryActividad = em.createNamedQuery("Movie.findByMovieId", Movie.class);
            qryActividad.setParameter("movieId", id);
            try {
             m = (Movie) qryActividad.getSingleResult();
            } catch (NoResultException ex) {
               m = null;
            }
            return  m;
         } catch (Exception ex) {
            return null;
        }
    }
     
    public Respuesta getMovie(Long id){
        try{
            Query qryActividad = em.createNamedQuery("Movie.findByMovieId", Movie.class);
            qryActividad.setParameter("resId", id);
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
     * busca todas las películas
     * @return 
     */
    public Respuesta getMovies(){
        try {
            Query qryActividad = em.createNamedQuery("Movie.findAll", Movie.class);
            List<Movie> movies = qryActividad.getResultList();
            List<MovieDto> moviesDto = new ArrayList<>();
            for (Movie m : movies) {
                moviesDto.add(new MovieDto(m));
            }
            
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Movie",moviesDto);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existen películas.", "getListFromMovie NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrió un error al consultar todas las películas.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar todas las películas con ese estado.", "getListFromMovie " + ex.getMessage());
        }
    }
    
    /**
     * obtiene una lista de películas de acuerdo al estado
     * @param estado  P = DISPONIBLE PRONTO, C = EN CARTELERA, I = INACTIVA
     * @return 
     */
    public Respuesta getListMovies(String estado){
        try {
            //limpiarListas();
            Query qryActividad = em.createNamedQuery("Movie.findByMovieEstado", Movie.class);
            qryActividad.setParameter("movieEstado", estado);
            List<Movie> movies = qryActividad.getResultList();
            List<MovieDto> moviesDto = new ArrayList<>();
            for (Movie m : movies) {
                moviesDto.add(new MovieDto(m));
            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "MovieList",moviesDto);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe Películas con ese estado.", "getListFromMovie NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar las películas con ese estado.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar las películas con ese estado.", "getListFromMovie " + ex.getMessage());
        }
    }

    /**
     * guardar Movie a partir de un dto
     */
    public Respuesta guardarMovie(MovieDto movDto) {
        try {
            Movie movie;
            if (movDto.getMovieId()!= null && movDto.getMovieId()> 0) {//si tra id
                movie = em.find(Movie.class,movDto.getMovieId());//busca la película con ese id para actualizar
                if (movie == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró la película a modificar.", "guardarUsuario NoResultException");
                }
                movie.actualizarMovie(movDto);//actualiza la movie si ya existía
                movie = em.merge(movie);//actualiza
            } else {
                System.out.println("Intenta persistir la pelicula: " + movDto.getMovieNombre());
                movie = new Movie(movDto);//crea una nueva a partir del Dto
                em.persist(movie);//periste
            }
            em.flush();//refresca
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Movie", new MovieDto(movie));    
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar la película.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el empleado.", "guardarEmpleado " + ex.getMessage());
        }
    }
    
    /**
     * elimina una película a partir de un id
     * @param id
     * @return 
     */
    public Respuesta eliminarMovie(Long id) {
        try {
            Movie empleado; 
            if (id != null && id > 0) {
                empleado = em.find(Movie.class, id);
                if (empleado == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró la película a eliminar.", "eliminarPelícula NoResultException");
                }
                em.remove(empleado);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar la pelicula a eliminar.", "eliminarPelícula NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");//no se ocupa obtener la película de la respuesta
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "No se puede eliminar la pelicula porque tiene relaciones con otros registros.", "eliminarPelícula " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el empleado.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar la película.", "eliminarPelícula " + ex.getMessage());
        }
    }
    
    
}
