/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Hamza
 */
@Entity
@Table(name = "COMPTEBANCAIRE")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "CompteBancaire.findAll", query = "SELECT c FROM CompteBancaire c ORDER BY c.id")
        ,@NamedQuery(name = "CompteBancaire.findById", query = "SELECT c FROM CompteBancaire c WHERE c.id = :id")})

public class CompteBancaire implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private float solde;

    public CompteBancaire() {
    }

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<OperationBancaire> operations = new ArrayList();

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<Client> listeClient = new ArrayList();

    public List<Client> getListeClient() {
        return listeClient;
    }

    public void setListeClient(Client client) {
        this.listeClient.add(client);
    }

 
    public CompteBancaire(float solde) {
        this.solde = solde;
        OperationBancaire op = new OperationBancaire("Création du compte", solde);
        operations.add(op);
    }
    public void deposer(float montant) {
        solde += montant;
        OperationBancaire op = new OperationBancaire("Depot", solde);
        operations.add(op);
    }

    public float retirer(float montant) {
        if (montant < solde) {
            solde -= montant;
            OperationBancaire op = new OperationBancaire("Retrait", solde);
            operations.add(op);
            return montant;
        } else {
            return 0;
        }
    }

    public List<OperationBancaire> getOperations() {
        return this.operations;
    }

    public void setOperations(OperationBancaire op) {
        this.operations.add(op);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompteBancaire)) {
            return false;
        }
        CompteBancaire other = (CompteBancaire) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.CompteBancaire[ id=" + id + " ]";
    }

}
