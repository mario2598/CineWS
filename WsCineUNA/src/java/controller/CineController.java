/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Model.Cine;
import Model.CineDto;
import Service.CineService;
import Util.CodigoRespuesta;
import Util.Respuesta;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author mario
 */
@Path("/CineController")
public class CineController {
    
    @EJB
    private CineService cineService;
    
    @GET
    @Path("/cines")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUsuario() {
        return "hola";
    }
    
    @GET
    @Path("/cine")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCine(){
        try{
            Respuesta resp = cineService.getCine();
            if(!resp.getEstado()){
                return Response.status(resp.getCodigoRespuesta().getValue()).entity(resp.getMensaje()).build();
            }
            return Response.ok((CineDto) resp.getResultado("Cine")).build();
        } catch(Exception ex){
            Logger.getLogger(CineController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error guardando el usuario").build();
        }
    }
    
    @POST
    @Path("/cine")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response guardarCine(CineDto cineDto) {
        try {
            Cine cine = new Cine();
            cine.dulplicateData(cineDto);
            Respuesta respuesta = cineService.guardarCine(cine);
            if (!respuesta.getEstado()) {
                return Response.status(respuesta.getCodigoRespuesta().getValue()).entity(respuesta.getMensaje()).build();
            }
            CineDto retorno = new CineDto((Cine) respuesta.getResultado("Cine"));
            return Response.ok(retorno).build();
        } catch (Exception ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error guardando el cine").build();
        }
    }
    
}
