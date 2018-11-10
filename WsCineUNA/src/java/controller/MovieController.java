/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Model.MovieDto;
import Model.reporteController;
import Service.MovieService;
import Util.CodigoRespuesta;
import Util.Respuesta;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 *
 * @author mario
 */
@Path("/movieController")
public class MovieController {
   
    @EJB
    MovieService movieService;
    @Inject
     ServletContext context;
    
    /**
     * obtiene todas las películas
     * @return 
     */
    @GET
    @Path("/movies")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMovies() {
    try {
             Respuesta res = movieService.getMovies();
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(new GenericEntity<List<MovieDto>>((List<MovieDto>) res.getResultado("AllMovieList")) {}).build();
            //return Response.ok((UsuarioDto) res.getResultado("Usuario")).build();
        } catch (Exception ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo todas las películas").build();
        }      
    }
    
    /**
     * obtiene una película a partir de un id
     * @param id
     * @return 
     */
    @GET
    @Path("/getMovie/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMovie(@PathParam("id") Long id) {
        try {
            Respuesta res = movieService.getMovie(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok((MovieDto) res.getResultado("Movie")).build();
        } catch (Exception ex) {
            Logger.getLogger(MovieController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo la películas(Controller)").build();
        }
    }
    
    /**
     * obtiene la lista de películas de acuerdo al estado
     * @param f1
     * @param f2
     * @return 
     */
    @GET
    @Path("/moviesReport/{date1}/{date2}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMovieList(@PathParam("date1") String f1,@PathParam("date2") String f2) {
       try {   
            reporteController rc = new reporteController(context);
            Respuesta res = rc.ganerateJasperMovieList(f1, f2);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
         
            return Response.ok(res.getResultado("Reporte")).build();
           
       } catch (Exception ex) {
            Logger.getLogger(MovieController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error creando Reporte").build();
        }      
    
    }
    
    @GET
    @Path("/movieReport/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMovieReport(@PathParam("id") Long id) {
    try {   
            reporteController rc = new reporteController(context);
            Respuesta res = rc.ganerateJasperReport(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
         
            return Response.ok(res.getResultado("Reporte")).build();
           
       } catch (Exception ex) {
            Logger.getLogger(MovieController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error creando Reporte").build();
        }      
    }
    
    @GET
    @Path("/getMovieList/{estado}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMovieList(@PathParam("estado") String estado) {
        try {
             Respuesta res = movieService.getListMovies(estado);
            if (!res.getEstado()) {
                
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(new GenericEntity<List<MovieDto>>((List<MovieDto>) res.getResultado("MovieList")) {}).build();
            //return Response.ok((UsuarioDto) res.getResultado("Usuario")).build();
        } catch (Exception ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo las películas(Controller)").build();
        }
    }
    
    /**
     * guarda una película a partir de un Dto
     * @param movieDto
     * @return 
     */
    @POST
    @Path("/guardarMovie")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response guardarMovie(MovieDto movieDto) {
        try {
            Respuesta respuesta = movieService.guardarMovie(movieDto);
            if (!respuesta.getEstado()) {
                return Response.status(respuesta.getCodigoRespuesta().getValue()).entity(respuesta.getMensaje()).build();
            }
            return Response.ok((MovieDto) respuesta.getResultado("Movie")).build();
        } catch (Exception ex) {
            Logger.getLogger(MovieController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error guardando la película(controller)").build();
        }
    };
    
    /**
     * elimina una película a partir de un id
     * @param id
     * @return 
     */
    @DELETE
    @Path("/eliminarMovie/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response eliminarMovie(@PathParam("id") Long id) {
        try {
            Respuesta respuesta = movieService.eliminarMovie(id);
            if (!respuesta.getEstado()) {
                return Response.status(respuesta.getCodigoRespuesta().getValue()).entity(respuesta.getMensaje()).build();
            }
            return Response.ok().build();
        } catch (Exception ex) {
            Logger.getLogger(MovieController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error eliminando la película(Controller)").build();
        }
    };
    
}
