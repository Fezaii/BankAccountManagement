/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import entities.Client;
import entities.CompteBancaire;
import entities.Conseiller;
import entities.Personne;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.Query;
import session.AdminManager;
import session.ConseillerManager;

/**
 *
 * @author fezai
 */
@Named(value = "conseillerMBean")
@SessionScoped
public class ConseillerMBean implements Serializable{

    /**
     * Creates a new instance of ConseillerMBean
     */
    @EJB
    private ConseillerManager conseillemanager;
    
    private String nom;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDateNaiss() {
        return dateNaiss;
    }

    public void setDateNaiss(Date dateNaiss) {
        this.dateNaiss = dateNaiss;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }
    private String prenom;
    private Date dateNaiss;
    private String adresse;
    private String telephone;
    private String mail;
    private String identifiant;
    private String motdepasse;
    
    public ConseillerMBean() {
    }
    public String creerConseiller()
    {
        this.conseillemanager.createConseiller(nom, prenom, dateNaiss, adresse, telephone, mail, identifiant, motdepasse);
        
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Création réussie !",  "Le client a été ajoutée.");  
          
        FacesContext.getCurrentInstance().addMessage(null, message);
        return "ListeConseillers?faces-redirect=true";
        
    } 
    public List<Client> Clients(Personne personne){
        Conseiller c = (Conseiller) personne;
        System.out.println(personne.getNom());
        System.out.println(c.getNom()+"222");
        return conseillemanager.getAllClient(c);
    }

    public String deleteClient(int id) {
        Client cl = conseillemanager.getClientById(id);
        String res = "Vous avez supprimé le "+cl.getRole()+" "+ cl.getNom() + " " + cl.getPrenom();
        conseillemanager.deleteClient(cl);        
        return res;
    }
    
}
