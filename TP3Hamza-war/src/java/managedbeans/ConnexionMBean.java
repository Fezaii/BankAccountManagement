/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import entities.Personne;
import entities.Role;
import java.io.IOException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import session.AdminManager;

/**
 *
 * @author fezai
 */
@Named(value = "connexionMBean")
@RequestScoped
public class ConnexionMBean implements Serializable{
    private String identifiant;
    private String motdepasse;
    @EJB 
    private AdminManager personne;
    /**
     * Creates a new instance of ConnexionMBean
     */
    public ConnexionMBean() {
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


    private boolean ok() {
        
        if(this.identifiant.isEmpty() || this.motdepasse.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Champs vide!!! "));
            return false;
        }
        Personne pe = personne.getPersonneByIdentifiant(this.identifiant);
        if(pe == null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Nom non trouvé");
            FacesContext.getCurrentInstance().addMessage(null,message);
            return false;
        }
       
        return true;
    }  
    public void connect() throws IOException {
        if(ok()){
            FacesContext context = FacesContext.getCurrentInstance();
            Personne user = personne.getPersonneByIdentifiant(this.identifiant);
            if((user.getRole() == Role.ADMIN) ){
            context.getExternalContext().getSessionMap().put("user", user);
            context.getExternalContext().redirect("faces/AdminUI.xhtml");
            }else if((user.getRole() == Role.CONSEILER)){
            context.getExternalContext().getSessionMap().put("user", user);
            context.getExternalContext().redirect("faces/ConseillerUI.xhtml");
            }else if((user.getRole() == Role.CLIENT) ){
            context.getExternalContext().getSessionMap().put("user", user);
            context.getExternalContext().redirect("faces/ClientUI.xhtml");
            }else{
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "connexion impossible");
            FacesContext.getCurrentInstance().addMessage(null,message);
        }
    }
    }
    public void disconnect() throws IOException {
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().redirect("faces/index.xhtml");
    }
    
}
