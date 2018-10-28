package Model;

import Model.Comprobante;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-27T18:30:08")
@StaticMetamodel(Butaca.class)
public class Butaca_ { 

    public static volatile SingularAttribute<Butaca, String> butEstado;
    public static volatile SingularAttribute<Butaca, String> butImg;
    public static volatile SingularAttribute<Butaca, Long> butFila;
    public static volatile SingularAttribute<Butaca, String> butLetra;
    public static volatile SingularAttribute<Butaca, Long> butId;
    public static volatile SingularAttribute<Butaca, String> butPantalla;
    public static volatile SingularAttribute<Butaca, ? extends Object> salaId;
    public static volatile CollectionAttribute<Butaca, Comprobante> comprobanteCollection;
    public static volatile SingularAttribute<Butaca, Long> butColumna;

}