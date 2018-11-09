/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Model.MovieDto;
import Model.TandaDto;
import Service.MovieService;
import Service.TandaService;
import Util.CodigoRespuesta;
import Util.Respuesta;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author robri
 */
@Path("/tandaController")
public class TandaController {
    
    @EJB
    TandaService tandaService;
    
    @GET
    @Path("/getTandaListM/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTandaListM(@PathParam("id") Long id) {
        try {
             Respuesta res = tandaService.getListTandaM(id);
            if (!res.getEstado()) {
                System.out.println("El error");
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(new GenericEntity<List<TandaDto>>((List<TandaDto>) res.getResultado("TandaListM")) {}).build();
            //return Response.ok((UsuarioDto) res.getResultado("Usuario")).build();
        } catch (Exception ex) {
            Logger.getLogger(TandaController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo las tandas por películas(Controller)").build();
        }
    }
    
    @GET
    @Path("/getTandaListS/{salaID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTandaListS(@PathParam("salaID") Long salaID){
        try {
            Respuesta res = tandaService.getListTandaS(salaID);
            if (!res.getEstado()) {
                System.out.println("El error");
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(new GenericEntity<ArrayList<TandaDto>>((ArrayList<TandaDto>) res.getResultado("TandaListS")) {}).build();
            //return Response.ok((UsuarioDto) res.getResultado("Usuario")).build();
        } catch (Exception ex) {
            Logger.getLogger(TandaController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo las tandas por películas(Controller)").build();
        }
    }
    
    @DELETE
    @Path("/eliminarTanda/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response eliminarTanda(@PathParam("id") Long id) {
        try {
            Respuesta respuesta = tandaService.eliminarTanda(id);
            if (!respuesta.getEstado()) {
                return Response.status(respuesta.getCodigoRespuesta().getValue()).entity(respuesta.getMensaje()).build();
            }
            return Response.ok().build();
        } catch (Exception ex) {
            Logger.getLogger(TandaController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error eliminando la tanda(Controller)").build();
        }
    };
    
}
