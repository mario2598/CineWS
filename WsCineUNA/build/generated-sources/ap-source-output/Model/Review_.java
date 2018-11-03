package Model;

import Model.Movie;
import Model.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-03T10:26:50")
@StaticMetamodel(Review.class)
public class Review_ { 

    public static volatile SingularAttribute<Review, Usuario> usuId;
    public static volatile SingularAttribute<Review, Long> revId;
    public static volatile SingularAttribute<Review, String> revOpinion;
    public static volatile SingularAttribute<Review, Movie> movieId;
    public static volatile SingularAttribute<Review, Long> revCalif;

}