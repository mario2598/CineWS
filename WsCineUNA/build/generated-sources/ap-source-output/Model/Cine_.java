package Model;

import Model.Sala;
import Model.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-23T15:57:13")
@StaticMetamodel(Cine.class)
public class Cine_ { 

    public static volatile SingularAttribute<Cine, Long> cineId;
    public static volatile SingularAttribute<Cine, Usuario> usuId;
    public static volatile SingularAttribute<Cine, Date> cineCierra;
    public static volatile SingularAttribute<Cine, String> cineEmail;
    public static volatile SingularAttribute<Cine, Date> cineAbre;
    public static volatile ListAttribute<Cine, Sala> salaList;
    public static volatile SingularAttribute<Cine, Long> cineTel;
    public static volatile SingularAttribute<Cine, String> cineNombre;

}