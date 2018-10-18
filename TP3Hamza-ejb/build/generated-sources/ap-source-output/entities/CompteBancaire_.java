package entities;

import entities.Client;
import entities.OperationBancaire;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-19T01:15:39")
@StaticMetamodel(CompteBancaire.class)
public class CompteBancaire_ { 

    public static volatile ListAttribute<CompteBancaire, OperationBancaire> operations;
    public static volatile SingularAttribute<CompteBancaire, Float> solde;
    public static volatile SingularAttribute<CompteBancaire, Client> client;
    public static volatile SingularAttribute<CompteBancaire, Long> id;

}