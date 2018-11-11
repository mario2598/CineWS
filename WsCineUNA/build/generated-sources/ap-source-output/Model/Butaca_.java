package Model;

import Model.Comprobante;
import Model.Reserva;
import Model.Sala;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-11T09:26:30")
@StaticMetamodel(Butaca.class)
public class Butaca_ { 

    public static volatile SingularAttribute<Butaca, String> butActiva;
    public static volatile SingularAttribute<Butaca, String> butImg;
    public static volatile SingularAttribute<Butaca, Long> butFila;
    public static volatile SingularAttribute<Butaca, String> butLetra;
    public static volatile ListAttribute<Butaca, Reserva> reservaList;
    public static volatile SingularAttribute<Butaca, Long> butId;
    public static volatile SingularAttribute<Butaca, Sala> salaId;
    public static volatile SingularAttribute<Butaca, Long> butColumna;
    public static volatile SingularAttribute<Butaca, Reserva> resId;
    public static volatile ListAttribute<Butaca, Comprobante> comprobanteList;

}