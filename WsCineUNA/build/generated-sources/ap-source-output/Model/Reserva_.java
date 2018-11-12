package Model;

import Model.Butaca;
import Model.Tanda;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-12T16:54:27")
@StaticMetamodel(Reserva.class)
public class Reserva_ { 

    public static volatile SingularAttribute<Reserva, String> resEstado;
    public static volatile SingularAttribute<Reserva, Butaca> butId;
    public static volatile SingularAttribute<Reserva, Date> resDate;
    public static volatile SingularAttribute<Reserva, Tanda> tandaId;
    public static volatile SingularAttribute<Reserva, Long> resId;

}