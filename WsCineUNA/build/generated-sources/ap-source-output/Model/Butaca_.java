package Model;

import Model.Comprobante;
import Model.Sala;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-03T10:26:50")
@StaticMetamodel(Butaca.class)
public class Butaca_ { 

    public static volatile SingularAttribute<Butaca, String> butEstado;
    public static volatile SingularAttribute<Butaca, String> butActiva;
    public static volatile SingularAttribute<Butaca, String> butImg;
    public static volatile SingularAttribute<Butaca, Long> butFila;
    public static volatile SingularAttribute<Butaca, String> butLetra;
    public static volatile SingularAttribute<Butaca, Long> butId;
    public static volatile SingularAttribute<Butaca, Sala> salaId;
    public static volatile SingularAttribute<Butaca, Long> butColumna;
    public static volatile ListAttribute<Butaca, Comprobante> comprobanteList;

}