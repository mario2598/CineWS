package Model;

import Model.Butaca;
import Model.Sala;
import Model.Tanda;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-10T15:23:34")
@StaticMetamodel(Reserva.class)
public class Reserva_ { 

    public static volatile SingularAttribute<Reserva, String> resEstado;
    public static volatile SingularAttribute<Reserva, Butaca> butId;
    public static volatile SingularAttribute<Reserva, Sala> salaId;
    public static volatile SingularAttribute<Reserva, Tanda> tandaId;
    public static volatile SingularAttribute<Reserva, Long> resId;
    public static volatile ListAttribute<Reserva, Butaca> butacaList;

}