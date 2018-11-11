package Model;

import Model.Cine;
import Model.Comprobante;
import Model.Review;
import java.io.Serializable;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-11T09:26:30")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile ListAttribute<Usuario, Review> reviewList;
    public static volatile SingularAttribute<Usuario, Long> usuIdioma;
    public static volatile SingularAttribute<Usuario, String> usuUser;
    public static volatile SingularAttribute<Usuario, String> usuSapellido;
    public static volatile SingularAttribute<Usuario, String> usuEstado;
    public static volatile SingularAttribute<Usuario, String> usuNewpassword;
    public static volatile SingularAttribute<Usuario, String> usuNombre;
    public static volatile SingularAttribute<Usuario, Cine> cineId;
    public static volatile SingularAttribute<Usuario, Long> usuId;
    public static volatile SingularAttribute<Usuario, String> usuCambio;
    public static volatile SingularAttribute<Usuario, String> usuCodAct;
    public static volatile SingularAttribute<Usuario, String> usuAdmin;
    public static volatile SingularAttribute<Usuario, String> usuEmail;
    public static volatile SingularAttribute<Usuario, String> usuPassword;
    public static volatile SingularAttribute<Usuario, Serializable> usuImg;
    public static volatile SingularAttribute<Usuario, String> usuPapellido;
    public static volatile ListAttribute<Usuario, Comprobante> comprobanteList;

}