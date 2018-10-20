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
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author Hamza
 */
@Entity
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private String prenom;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateNaissance;
    private String adresse;
    private String telephone;
    private String mail;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<CompteBancaire> listeComptes = new ArrayList();

    public Client() {
    }

    public Client(String nom, String prenom, List<CompteBancaire> listeComptes) {
        this.nom = nom;
        this.prenom = prenom;
        this.listeComptes = listeComptes;
    }

    public Client(String nom, String prenom, Date date, String adresse, String telephone, String mail) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = date;
        this.adresse = adresse;
        this.telephone = telephone;
        this.mail = mail;
    }

    public Client(String nom, String prenom, Date date, String adresse, String telephone, String mail, List<CompteBancaire> listeComptes) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = date;
        this.adresse = adresse;
        this.telephone = telephone;
        this.mail = mail;
        this.listeComptes = listeComptes;
    }


    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getMail() {
        return mail;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public List<CompteBancaire> getListeComptes() {
        return listeComptes;
    }

    public void setListeComptes(List<CompteBancaire> listeComptes) {
        this.listeComptes = listeComptes;
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
        if (!(object instanceof Client)) {
            return false;
        }
        Client other = (Client) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Client[ id=" + id + " ]";
    }

}
