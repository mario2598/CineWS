/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Model.Movie;
import Model.MovieDto;
import Model.reportMovie;
import Util.CodigoRespuesta;
import Util.Respuesta;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

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
     * @param id
     * @return 
     */
    
     public Respuesta reporteMovieList(Date date1, Date date2) {
        try {
            List<Movie> listMovies;
            Query qryActividad = em.createNamedQuery("Movie.ReporteList", Movie.class);
            qryActividad.setParameter("date1", date1);
            qryActividad.setParameter("date2", date2);
            listMovies= qryActividad.getResultList();
            List<reportMovie> listJasper = new ArrayList<>();
            reportMovie rp;
            for(Movie m : listMovies){
                
                rp = new reportMovie(m.getMovieNombre(), m.getMovieId());
                listJasper.add(rp);
            }
            Collections.sort(listJasper, (reportMovie p1, reportMovie p2) -> { 
                return p2.getCantidad().compareTo(p1.getCantidad());
            } 
            );
            
            String ruta = context.getRealPath("/");
            String JasperRuta = ruta+ "\\jasper\\movieReport.jrxml";
            String pdfRuta = ruta+ "\\jasper\\reporteMovies.pdf";
            String outPutFile = pdfRuta;
            
            JRBeanCollectionDataSource cobrojrb = new JRBeanCollectionDataSource(listJasper);
            JasperReport jasperReport = JasperCompileManager.compileReport(JasperRuta);
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("dataSource", cobrojrb);
            JasperPrint jasperprint = JasperFillManager.fillReport(jasperReport, parametros, new JREmptyDataSource());        
            OutputStream outputStream = new FileOutputStream(new File(outPutFile));
            
            JasperExportManager.exportReportToPdfStream(jasperprint, outputStream);
            byte[] ReportBytes = convertDocToByteArray(outPutFile);
           
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Movie",ReportBytes);
        } catch (Exception ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al generar el reporte.", "reporteMovieList " + ex.getMessage());
        }
    }
     
         public static byte[] convertDocToByteArray(String path)throws FileNotFoundException, IOException{
        File file = new File(path);
        FileInputStream fis = new FileInputStream(file);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        try {
            for (int readNum; (readNum = fis.read(buf)) != -1;) {
                bos.write(buf, 0, readNum);
            }
        } catch (IOException ex) {
        }
        byte[] bytes = bos.toByteArray();
        return bytes;
    }
     
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
            
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "AllMovieList",moviesDto);

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
