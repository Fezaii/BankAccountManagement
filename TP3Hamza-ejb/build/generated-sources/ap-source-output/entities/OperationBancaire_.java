package entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-30T03:41:33")
@StaticMetamodel(OperationBancaire.class)
public class OperationBancaire_ { 

    public static volatile SingularAttribute<OperationBancaire, String> description;
    public static volatile SingularAttribute<OperationBancaire, Float> montant;
    public static volatile SingularAttribute<OperationBancaire, Long> id;
    public static volatile SingularAttribute<OperationBancaire, Date> dateOperation;

}