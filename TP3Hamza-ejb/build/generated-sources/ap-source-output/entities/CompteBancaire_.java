package entities;

import entities.Client;
import entities.OperationBancaire;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-30T03:41:33")
@StaticMetamodel(CompteBancaire.class)
public class CompteBancaire_ { 

    public static volatile ListAttribute<CompteBancaire, OperationBancaire> operations;
    public static volatile SingularAttribute<CompteBancaire, Float> solde;
    public static volatile SingularAttribute<CompteBancaire, Long> id;
    public static volatile ListAttribute<CompteBancaire, Client> listeClient;

}