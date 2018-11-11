package Model;

import Model.Sala;
import Model.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-10T15:23:34")
@StaticMetamodel(Cine.class)
public class Cine_ { 

    public static volatile SingularAttribute<Cine, Long> cineId;
    public static volatile ListAttribute<Cine, Usuario> usuarioList;
    public static volatile SingularAttribute<Cine, Long> cineCierra;
    public static volatile SingularAttribute<Cine, Long> cineAbre;
    public static volatile SingularAttribute<Cine, String> cineEmail;
    public static volatile ListAttribute<Cine, Sala> salaList;
    public static volatile SingularAttribute<Cine, Long> cineTel;
    public static volatile SingularAttribute<Cine, String> cineNombre;

}