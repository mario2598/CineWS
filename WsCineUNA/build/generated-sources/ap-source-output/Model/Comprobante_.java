package Model;

import Model.Butaca;
import Model.Movie;
import Model.Sala;
import Model.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-03T21:34:26")
@StaticMetamodel(Comprobante.class)
public class Comprobante_ { 

    public static volatile SingularAttribute<Comprobante, Long> compCosto;
    public static volatile SingularAttribute<Comprobante, Usuario> usuId;
    public static volatile SingularAttribute<Comprobante, Long> compId;
    public static volatile SingularAttribute<Comprobante, Butaca> butId;
    public static volatile SingularAttribute<Comprobante, Sala> salaId;
    public static volatile SingularAttribute<Comprobante, Movie> movieId;

}