package Model;

import Model.Detalle;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-23T15:57:13")
@StaticMetamodel(Movie.class)
public class Movie_ { 

    public static volatile SingularAttribute<Movie, String> movieNombre;
    public static volatile SingularAttribute<Movie, String> moviePortada;
    public static volatile ListAttribute<Movie, Detalle> detalleList;
    public static volatile SingularAttribute<Movie, Long> movieId;
    public static volatile SingularAttribute<Movie, String> movieEstado;
    public static volatile SingularAttribute<Movie, String> movieUrlyt;
    public static volatile SingularAttribute<Movie, String> movieResena;
    public static volatile SingularAttribute<Movie, Date> movieDate;

}