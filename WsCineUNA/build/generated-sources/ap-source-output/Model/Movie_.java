package Model;

import Model.Comprobante;
import Model.Review;
import Model.Tanda;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-03T22:39:56")
@StaticMetamodel(Movie.class)
public class Movie_ { 

    public static volatile ListAttribute<Movie, Review> reviewList;
    public static volatile SingularAttribute<Movie, Long> movieDuracion;
    public static volatile SingularAttribute<Movie, String> movieNombre;
    public static volatile SingularAttribute<Movie, String> moviePortada;
    public static volatile SingularAttribute<Movie, String> movieUrleng;
    public static volatile SingularAttribute<Movie, Long> movieId;
    public static volatile SingularAttribute<Movie, String> movieEstado;
    public static volatile SingularAttribute<Movie, String> movieTipo;
    public static volatile SingularAttribute<Movie, String> movieResena;
    public static volatile SingularAttribute<Movie, String> movieUrlesp;
    public static volatile SingularAttribute<Movie, Long> movieIdioma;
    public static volatile SingularAttribute<Movie, Serializable> movieUrlimg;
    public static volatile ListAttribute<Movie, Tanda> tandaList;
    public static volatile SingularAttribute<Movie, Date> movieDate;
    public static volatile ListAttribute<Movie, Comprobante> comprobanteList;

}