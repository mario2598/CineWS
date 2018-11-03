/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Model.Butaca;
import Model.ButacaDto;
import Model.Sala;
import Service.ButacaService;
import Util.CodigoRespuesta;
import Util.Respuesta;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Chris
 */
@Path("/ButacaController")
public class ButacaController {
    
    @EJB
    private final ButacaService butacaService = new ButacaService();
    
    @GET
    @Path("/butaca/{butacaID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getButaca(@PathParam("butacaID") Long butacaID){
        try{
            Respuesta resp = butacaService.getButaca(butacaID);
            if(!resp.getEstado()){
                return Response.status(resp.getCodigoRespuesta().getValue()).entity(resp.getMensaje()).build();
            }
            return Response.ok((ButacaDto) resp.getResultado("Butaca")).build();
        } catch(Exception ex){
            Logger.getLogger(ButacaController.class.getName()).log(Level.SEVERE, "Error en el metodo getButaca de la clase ButacaController.", ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error guardando la butaca.").build();
        }
    }
    
    @GET
    @Path("/butacas/{salaID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListaButacas(@PathParam("salaID") Long salaID){
        try{
            Respuesta resp = butacaService.getListaButacas(salaID);
            if(!resp.getEstado()){
                return Response.status(resp.getCodigoRespuesta().getValue()).entity(resp.getMensaje()).build();
            }
            return Response.ok((ArrayList<ButacaDto>) resp.getResultado("ButacaList")).build();
        } catch(Exception ex){
            Logger.getLogger(ButacaController.class.getName()).log(Level.SEVERE, "Error en el metodo getListaButacas de la clase ButacaController.", ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo la lista de butacas.").build();
        }
    }
    
    @POST
    @Path("/butaca")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response guardarButaca(ButacaDto dto){
        try{
            Butaca butaca = new Butaca();
            butaca.duplicateData(dto);
            Sala sala = new Sala();
            sala.setSalaId(dto.getSalaId());
            butaca.setSalaId(sala);
            Respuesta resp = butacaService.guardarButaca(butaca);
            if(!resp.getEstado()){
                return Response.status(resp.getCodigoRespuesta().getValue()).entity(resp.getMensaje()).build();
            }
            ButacaDto retorno = new ButacaDto((Butaca) resp.getResultado("Butaca"));
            return Response.ok(retorno).build();
        } catch(Exception ex){
            Logger.getLogger(ButacaController.class.getName()).log(Level.SEVERE, "Error en el metodo guardarButaca de la clase ButacaController.", ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al guardar la butaca.").build();
        }
    }
    
    @DELETE
    @Path("/butaca/{butacaID}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response eliminarButaca(@PathParam("butacaID") Long id){
        try{
            Respuesta resp = butacaService.eliminarButaca(id);
            if(!resp.getEstado()){
                return Response.status(resp.getCodigoRespuesta().getValue()).entity(resp.getMensaje()).build();
            }
            return Response.ok().build();
        } catch(Exception ex){
            Logger.getLogger(ButacaController.class.getName()).log(Level.SEVERE, "Error en el metodo eliminarButaca de la clase ButacaController.", ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al eliminar la butaca.").build();
        }
    }
    
    @DELETE
    @Path("/butacas/{salaID}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response eliminarListaButaca(@PathParam("salaID") Long salaID){
        try{
            Respuesta resp = butacaService.eliminarListaButacas(salaID);
            if(!resp.getEstado()){
                return Response.status(resp.getCodigoRespuesta().getValue()).entity(resp.getMensaje()).build();
            }
            return Response.ok().build();
        } catch(Exception ex){
            Logger.getLogger(ButacaController.class.getName()).log(Level.SEVERE, "Error en el metodo eliminarListaButaca de la clase ButacaController.", ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al eliminar lista de butacas.").build();
        }
    }
    
}
