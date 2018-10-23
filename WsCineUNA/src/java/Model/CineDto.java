/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Util.LocalDateAdapter;
import java.time.LocalDate;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author mario
 */
@XmlRootElement(name = "CineDto")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class CineDto {
    
    private Long cineId;
    private String cineNombre;
    private Long cineTel;
    private String cineEmail;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate cineAbre;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate cineCierra;
    
    
}
