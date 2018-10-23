package Model;

import Model.Cine;
import Model.Detalle;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-23T15:57:13")
@StaticMetamodel(Sala.class)
public class Sala_ { 

    public static volatile SingularAttribute<Sala, String> salaImgfondo;
    public static volatile SingularAttribute<Sala, Cine> cineId;
    public static volatile ListAttribute<Sala, Detalle> detalleList;
    public static volatile SingularAttribute<Sala, Long> salaId;
    public static volatile SingularAttribute<Sala, String> salaEstado;

}