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
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import session.GestionnaireDeCompteBancaire;
import session.GestionnaireDesClients;

/**
 *
 * @author Fezai
 */
@Named(value = "clientMBean")
@SessionScoped
public class ClientMBean implements Serializable {

    @EJB
    private GestionnaireDesClients managerClient;

    
    private String nom;
    private String prenom;
    private Date dateNaiss;
    private String adresse;
    private String telephone;
    private String mail;
    private String identifiant;
    private String motdepasse;
    private float solde;
    private int consid;
    
    public List<CompteBancaire> Comptes(Personne personne){
        Client c = (Client) personne;
        return managerClient.getAllCompte(c);
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


    public int getConsid() {
        return consid;
    }

    public void setConsid(int consid) {
        this.consid = consid;
    }
    
    /**
     * Creates a new instance of ClientMBean
     */
    public ClientMBean() {
    }

    public String creerClient()
    {
        this.managerClient.createClient(nom, prenom,dateNaiss, adresse, telephone, mail, solde,consid,identifiant,motdepasse);
        
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Création réussie !",  "Le client a été ajoutée.");  
          
        FacesContext.getCurrentInstance().addMessage(null, message);
        return "ListeClients?faces-redirect=true";
        
    }
    
    public void clientTest() throws ParseException{
        managerClient.creerClientTest();
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
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

    public float getSolde() {
        return solde;
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

    public void setSolde(float solde) {
        this.solde = solde;
    }

    public Date getDateNaiss() {
        return dateNaiss;
    }

    public void setDateNaiss(Date dateNaiss) {
        this.dateNaiss = dateNaiss;
    }
    
}
