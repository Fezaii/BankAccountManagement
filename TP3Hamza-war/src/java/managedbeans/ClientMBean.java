/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import session.GestionnaireDeCompteBancaire;
import session.GestionnaireDesClients;

/**
 *
 * @author Hamza
 */
@Named(value = "clientMBean")
@ViewScoped
public class ClientMBean implements Serializable {

    @EJB
    private GestionnaireDesClients managerClient;

    
    private String nom;
    private String prenom;
    private Date dateNaiss;
    private String adresse;
    private String telephone;
    private String mail;
    private float solde;
    /**
     * Creates a new instance of ClientMBean
     */
    public ClientMBean() {
    }

    public String creerClient()
    {
        managerClient.createClient(nom, prenom,dateNaiss, adresse, telephone, mail, solde);
        
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Création réussie !",  "Le client a été ajoutée.");  
          
        FacesContext.getCurrentInstance().addMessage(null, message);
        return "listeComptes";
        
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
