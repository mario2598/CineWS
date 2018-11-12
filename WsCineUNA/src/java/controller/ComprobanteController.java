/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Model.Butaca;
import Model.ButacaDto;
import Model.Comprobante;
import Model.ComprobanteDto;
import Model.Sala;
import Model.reporteController;
import Service.ButacaService;
import Service.ComprobanteService;
import Util.CodigoRespuesta;
import Util.Respuesta;
import static com.sun.faces.facelets.util.Path.context;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
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
@Path("/comprobante")
public class ComprobanteController {
    @Inject
    ServletContext context;
    @EJB
    private final ComprobanteService cService = new ComprobanteService();
    
    @GET
    @Path("/comprobantes")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUsuario() {
    

            return "hola";
        
    }
    @GET
    @Path("/comprobanteReport/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMovieReport(@PathParam("id") Long id) {
    try {   
            reporteController rc = new reporteController(context);
            Respuesta res = rc.ganerateJasperComp(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
         
            return Response.ok(res.getResultado("Reporte")).build();
           
       } catch (Exception ex) {
            Logger.getLogger(MovieController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error creando Comprobante").build();
        }      
    }
    
    @POST
    @Path("/guardarComprobante")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response guardarComprobante(ComprobanteDto dto){
        try{
           
            Respuesta resp = cService.guardarComp(dto);
            if(!resp.getEstado()){
                return Response.status(resp.getCodigoRespuesta().getValue()).entity(resp.getMensaje()).build();
            }
            ComprobanteDto retorno = new ComprobanteDto((Comprobante) resp.getResultado("Comprobante"));
            return Response.ok(retorno).build();
        } catch(Exception ex){
            Logger.getLogger(ButacaController.class.getName()).log(Level.SEVERE, "Error en el metodo guardarComprobante de la clase ComprobanteController.", ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al guardar el comprobante.").build();
        }
    }
    
}
