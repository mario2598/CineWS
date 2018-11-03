/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Model.Cine;
import Model.Sala;
import Model.SalaDto;
import Service.SalaService;
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
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author mario
 */
@Path("/SalaController")
public class SalaController {
    
    @EJB
    private final SalaService salaService = new SalaService();
    
    @GET
    @Path("/salasTest")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUsuario() {
        return "hola";
    }
    
    @GET
    @Path("/sala/{salaID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSala(@PathParam("salaID") Long id){
        try{
            Respuesta resp = salaService.getSala(id);
            if(!resp.getEstado()){
                return Response.status(resp.getCodigoRespuesta().getValue()).entity(resp.getMensaje()).build();
            }
            return Response.ok((SalaDto) resp.getResultado("Sala")).build();
        } catch(Exception ex){
            Logger.getLogger(SalaController.class.getName()).log(Level.SEVERE, "Error en el metodo getSala de la clase SalaController.", ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error guardando la sala.").build();
        }
    }
    
    @GET
    @Path("/salas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListaSalas(){
        try{
            Respuesta resp = salaService.getListaSalas();
            if(!resp.getEstado()){
                return Response.status(resp.getCodigoRespuesta().getValue()).entity(resp.getMensaje()).build();
            }
            return Response.ok(new GenericEntity<ArrayList<SalaDto>>((ArrayList<SalaDto>)resp.getResultado("SalaList")){}).build();
        } catch(Exception ex){
            Logger.getLogger(SalaController.class.getName()).log(Level.SEVERE, "Error en el metodo getListaSalas de la clase SalaController", ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo la lista de salas.").build();
        }
    }
    
    @POST
    @Path("/sala")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response guardarSala(SalaDto dto){
        try{
            Sala sala = new Sala();
            sala.duplicateData(dto);
            Cine cine = new Cine();
            cine.setCineId(dto.getCineId());
            sala.setCineId(cine);
            Respuesta resp = salaService.guardarSala(sala);
            if(!resp.getEstado()){
                return Response.status(resp.getCodigoRespuesta().getValue()).entity(resp.getMensaje()).build();
            }
            SalaDto retorno = new SalaDto((Sala) resp.getResultado("Sala"));
            return Response.ok(retorno).build();
        } catch(Exception ex){
            Logger.getLogger(SalaController.class.getName()).log(Level.SEVERE, "Error en el metodo guardarSala de la clase SalaController", ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al guardar la sala.").build();
        }
    }
    
    @DELETE
    @Path("/sala/{salaID}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response eliminarSala(@PathParam("salaID") Long id){
        try{
            Respuesta resp = salaService.eliminarSala(id);
            if(!resp.getEstado()){
                return Response.status(resp.getCodigoRespuesta().getValue()).entity(resp.getMensaje()).build();
            }
            return Response.ok("Correcto").build();
        } catch(Exception ex){
            Logger.getLogger(SalaController.class.getName()).log(Level.SEVERE, "Error en el metodo eliminarSala de la clase SalaController", ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al eliminar la sala.").build();
        }
    }
    
}
