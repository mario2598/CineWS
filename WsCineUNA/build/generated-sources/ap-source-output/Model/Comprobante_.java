package Model;

import Model.Butaca;
import Model.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-27T18:30:08")
@StaticMetamodel(Comprobante.class)
public class Comprobante_ { 

    public static volatile SingularAttribute<Comprobante, Long> compCosto;
    public static volatile SingularAttribute<Comprobante, Usuario> usuId;
    public static volatile SingularAttribute<Comprobante, Long> compId;
    public static volatile SingularAttribute<Comprobante, Butaca> butId;
    public static volatile SingularAttribute<Comprobante, ? extends Object> salaId;
    public static volatile SingularAttribute<Comprobante, ? extends Object> movieId;

}