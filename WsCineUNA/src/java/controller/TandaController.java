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
 * @author robri
 */
@Path("/tandaController")
public class TandaController {
    /**
     * obtiene la lista de películas de acuerdo al estado
     * @param estado
     * @return 
     */
    
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
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo las tandas por películas(Controller)").build();
        }
    }
    
}
