package Model;

import Model.Movie;
import Model.Sala;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-23T15:57:13")
@StaticMetamodel(Detalle.class)
public class Detalle_ { 

    public static volatile SingularAttribute<Detalle, Long> detIdioma;
    public static volatile SingularAttribute<Detalle, Date> detFechafin;
    public static volatile SingularAttribute<Detalle, Long> detHorafin;
    public static volatile SingularAttribute<Detalle, Sala> salaId;
    public static volatile SingularAttribute<Detalle, Date> detFechaini;
    public static volatile SingularAttribute<Detalle, Movie> movieId;
    public static volatile SingularAttribute<Detalle, Long> detHoraini;
    public static volatile SingularAttribute<Detalle, Long> detId;
    public static volatile SingularAttribute<Detalle, Long> detCosto;

}