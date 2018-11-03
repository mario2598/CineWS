package Model;

import Model.Movie;
import Model.Sala;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-03T10:26:50")
@StaticMetamodel(Tanda.class)
public class Tanda_ { 

    public static volatile SingularAttribute<Tanda, Date> tandaHinicio;
    public static volatile SingularAttribute<Tanda, Sala> salaId;
    public static volatile SingularAttribute<Tanda, Long> tandaCobro;
    public static volatile SingularAttribute<Tanda, Movie> movieId;
    public static volatile SingularAttribute<Tanda, Long> tandaId;
    public static volatile SingularAttribute<Tanda, Date> tandaHfin;

}