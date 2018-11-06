/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Service.ComprobanteService;
import Service.MovieService;
import Util.Respuesta;
import javax.ejb.EJB;

/**
 *
 * @author mario
 */
public class reportMovie {
    private String Nombre;
    private Integer cantidad;
    @EJB
    ComprobanteService cService;

    public reportMovie(String Nombre,Long id) {
        this.Nombre = Nombre;
        Respuesta res = cService.countFromMovie(id);
        try {
           cantidad = (Integer) res.getResultado("Comprobante"); 
        } catch (Exception e) {
            cantidad = 0;
        }
        
    }
    
    

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    
    
    
}
