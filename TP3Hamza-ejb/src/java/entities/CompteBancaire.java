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
@NamedQuery(name = "CompteBancaire.findAll", query = "SELECT c FROM CompteBancaire c ORDER BY c.id"),
@NamedQuery(name = "CompteBancaire.findById", query = "SELECT c FROM CompteBancaire c WHERE c.id = :id"),
@NamedQuery(name = "CompteBancaire.nbComptes", query = "SELECT COUNT(c) FROM CompteBancaire c")})

public class CompteBancaire implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private float solde;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<OperationBancaire> operations = new ArrayList();

    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private Client client;

    public CompteBancaire() {
        client = new Client();

    }

    public CompteBancaire(float solde) {
        this.solde = solde;
    }

    public void deposer(int montant) {
        solde += montant;
    }

    public int retirer(int montant) {
        if (montant < solde) {
            solde -= montant;
            return montant;
        } else {
            return 0;
        }
    }

    public CompteBancaire(Client client, float solde) {
        this.client = client;
        this.solde = solde;
    }

    public List<OperationBancaire> getOperations() {
        return operations;
    }

    public void setOperations(List<OperationBancaire> operations) {
        this.operations = operations;
    }

    public void addOperationBancaire(OperationBancaire op) {
        operations.add(op);
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public float depot(float montant) {
        if (montant < 0) {
            montant = 0 - montant;
        }

        this.solde += montant;
        return solde;
    }

    public float retrait(float montant) {
        if (montant < 0) {
            montant = 0 - montant;
        }

        solde -= montant;
        return montant;
    }

    public float consultation() {
        return solde;
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
