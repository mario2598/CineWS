/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Model.MovieDto;
import Model.UsuarioDto;
import Service.MovieService;
import Util.CodigoRespuesta;
import Util.Respuesta;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.GET;
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
    
    @GET
    @Path("/movies")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUsuario() {
    

            return "hola";
        
    }
    
    @GET
    @Path("/getMovie")
    @Produces(MediaType.APPLICATION_JSON)
    public String getMovie() {
    

            return "hola";
        
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
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo las películas").build();
        }
    }
    
}
