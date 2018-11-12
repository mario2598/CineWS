/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Model.UsuarioDto;
import Service.UsuarioService;
import Util.CodigoRespuesta;
import Util.Respuesta;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
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
@Path("/UsuarioController")
public class UsuarioController {
    
    @EJB
    UsuarioService usuarioService;
    
    @GET
    @Path("/usuariosPrueba")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUsuario() {
<<<<<<< HEAD
    

        return "hola";
        
=======
        return "hola";
>>>>>>> master9_f
    }
    
    @POST
    @Path("/usuario")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response guardarEmpleado(UsuarioDto usuarioDto) {
        try {
            Respuesta respuesta = usuarioService.guardarUsuario(usuarioDto);
            if (!respuesta.getEstado()) {
                return Response.status(respuesta.getCodigoRespuesta().getValue()).entity(respuesta.getMensaje()).build();
            }
            return Response.ok((UsuarioDto) respuesta.getResultado("Usuario")).build();
        } catch (Exception ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error guardando el usuario").build();
        }
    }

    
    @GET
    @Path("/activar/{user}")
    @Produces(MediaType.APPLICATION_JSON)
    public String activarUsuario(@PathParam("user") String user) { 
          String nombre =  usuarioService.activarUsuario(user);
          return "Bienvenido "+nombre+", su cuenta ha sido activada exitosamente";
    }
    
    @GET
    @Path("/usuario/{usuario}/{clave}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsuario(@PathParam("usuario") String usuario, @PathParam("clave") String clave) {
        try {
             Respuesta res = usuarioService.validarUsuario(usuario, clave);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok((UsuarioDto) res.getResultado("Usuario")).build();
        } catch (Exception ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo el usuario").build();
        }
    }
    
    @GET
    @Path("/usuario/{user}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response newPass(@PathParam("user") String user) {   
           try {
             Respuesta res =  usuarioService.getUsuarioUsu(user);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok((UsuarioDto) res.getResultado("Usuario")).build();
        } catch (Exception ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo el usuario").build();
        }
    }
    
    @GET
    @Path("/usuarios")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListaUsuarios(){
        try{
            Respuesta resp = usuarioService.getUsuarioList();
            if(!resp.getEstado()){
                return Response.status(resp.getCodigoRespuesta().getValue()).entity(resp.getMensaje()).build();
            }
            return Response.ok(new GenericEntity<ArrayList<UsuarioDto>>((ArrayList<UsuarioDto>)resp.getResultado("UsuList")){}).build();
        } catch(Exception ex){
            Logger.getLogger(SalaController.class.getName()).log(Level.SEVERE, "Error en el metodo getListaUsuarios de la clase UsuarioController", ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo la lista de usuarios.\nError: " + ex).build();
        }
    }
}
