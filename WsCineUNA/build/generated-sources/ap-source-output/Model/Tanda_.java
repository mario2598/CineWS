package Model;

import Model.Movie;
import Model.Reserva;
import Model.Sala;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-10T15:23:34")
@StaticMetamodel(Tanda.class)
public class Tanda_ { 

    public static volatile SingularAttribute<Tanda, Long> tandaInihh;
    public static volatile SingularAttribute<Tanda, Long> tandaInimm;
    public static volatile ListAttribute<Tanda, Reserva> reservaList;
    public static volatile SingularAttribute<Tanda, Sala> salaId;
    public static volatile SingularAttribute<Tanda, Long> tandaCobro;
    public static volatile SingularAttribute<Tanda, Long> tandaFinhh;
    public static volatile SingularAttribute<Tanda, Long> tandaFinmm;
    public static volatile SingularAttribute<Tanda, Movie> movieId;
    public static volatile SingularAttribute<Tanda, Long> tandaId;

}