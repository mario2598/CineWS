/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author mario
 */
@Path("/sala")
public class SalaController {
    
    @GET
    @Path("/salas")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUsuario() {
    

            return "hola";
        
    }
    
}
