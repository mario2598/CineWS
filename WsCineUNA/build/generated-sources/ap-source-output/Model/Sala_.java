package Model;

import Model.Butaca;
import Model.Cine;
import Model.Comprobante;
import Model.Tanda;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-03T21:34:26")
@StaticMetamodel(Sala.class)
public class Sala_ { 

    public static volatile SingularAttribute<Sala, String> salaImgfondo;
    public static volatile SingularAttribute<Sala, Cine> cineId;
    public static volatile SingularAttribute<Sala, Long> salaId;
    public static volatile SingularAttribute<Sala, String> salaEstado;
    public static volatile SingularAttribute<Sala, Long> salaFilas;
    public static volatile SingularAttribute<Sala, String> salaTipo;
    public static volatile SingularAttribute<Sala, Long> salaCol;
    public static volatile SingularAttribute<Sala, String> salaNombre;
    public static volatile ListAttribute<Sala, Tanda> tandaList;
    public static volatile ListAttribute<Sala, Comprobante> comprobanteList;
    public static volatile ListAttribute<Sala, Butaca> butacaList;

}