/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Model.Butaca;
import Model.ButacaDto;
import Model.Reserva;
import Model.ReservaDto;
import Model.Sala;
import Model.Tanda;
import Service.MovieService;
import Service.ReservaService;
import Util.CodigoRespuesta;
import Util.Respuesta;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author mario
 */
@Path("/reservaController")
public class ReservaController {
    @EJB
    ReservaService rService;
    
     @GET
    @Path("/getReserva/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReserva(@PathParam("id") Long id){
        try{
            Respuesta resp = rService.getReserva(id);
            if(!resp.getEstado()){
                return Response.status(resp.getCodigoRespuesta().getValue()).entity(resp.getMensaje()).build();
            }
            return Response.ok((ButacaDto) resp.getResultado("Reserva")).build();
        } catch(Exception ex){
            Logger.getLogger(ButacaController.class.getName()).log(Level.SEVERE, "Error en el metodo getReserva .", ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo la reserva.").build();
        }
    }
    
    @POST
    @Path("/reserva")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response guardarReserva(ReservaDto dto){
        try{        
            Respuesta resp = rService.guardarReserva(dto);
            if(!resp.getEstado()){
                return Response.status(resp.getCodigoRespuesta().getValue()).entity(resp.getMensaje()).build();
            }
            ButacaDto retorno = new ButacaDto((Butaca) resp.getResultado("Butaca"));
            return Response.ok(retorno).build();
        } catch(Exception ex){
            Logger.getLogger(ButacaController.class.getName()).log(Level.SEVERE, "Error en el metodo guardarReserva.", ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al guardar la reserva.").build();
        }
    }
    
     @GET
    @Path("/getReservas/{tandaId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListReservas(@PathParam("tandaId") Long tandaID){
        try{
            Respuesta resp = rService.getListReserva(tandaID);
            if(!resp.getEstado()){
                return Response.status(resp.getCodigoRespuesta().getValue()).entity(resp.getMensaje()).build();
            }
            return Response.ok((ArrayList<ButacaDto>) resp.getResultado("ReservasList")).build();
        } catch(Exception ex){
            Logger.getLogger(ButacaController.class.getName()).log(Level.SEVERE, "Error en el metodo getListReservas.", ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo la lista de reservas.").build();
        }
    }
    
    @DELETE
    @Path("/butaca/{resId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response eliminarReserva(@PathParam("resId") Long id){
        try{
            Respuesta resp = rService.eliminarReserva(id);
            if(!resp.getEstado()){
                return Response.status(resp.getCodigoRespuesta().getValue()).entity(resp.getMensaje()).build();
            }
            return Response.ok().build();
        } catch(Exception ex){
            Logger.getLogger(ButacaController.class.getName()).log(Level.SEVERE, "Error en el metodo eliminarReserva de la clase ReservaController.", ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al eliminar la reserva.").build();
        }
    }
}
