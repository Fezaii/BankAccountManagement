/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp3;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author fezai
 */
@Entity
@NamedQueries({
@NamedQuery(name ="CompteBancaire.findAll", query ="SELECT c FROM CompteBancaire c")})
public class CompteBancaire implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nom;
    
    private int solde;

    public CompteBancaire() {
    }
    
    public CompteBancaire(String nom, int solde) {
        
        this.nom = nom;  
        this.solde = solde;  
    } 
    /**
     * Get the value of Nom
     *
     * @return the value of Nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Set the value of Nom
     *
     * @param nom new value of Nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    

    /**
     * Get the value of Solde
     *
     * @return the value of Solde
     */
    public long getSolde() {
        return solde;
    }

    /**
     * Set the value of Solde
     *
     * @param solde new value of Solde
     */
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
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "tp3.CompteBanquaire[ id=" + id + " ]";
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
}
