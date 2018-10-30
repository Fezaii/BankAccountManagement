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
import static java.lang.System.out;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import session.AdminManager;

/**
 *
 * @author fezai
 */
@Named(value = "connexionMBean")
//@RequestScoped
@SessionScoped
public class ConnexionMBean implements Serializable{
    private String identifiant;
    private String motdepasse;
    private FacesContext session;
    private Personne p;
    @EJB 
    private AdminManager personne;
  
        
    public Personne getLoggedUser() {
        this.session = FacesContext.getCurrentInstance();
        return (Personne)session.getExternalContext().getSessionMap().get("user");
        
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


    private boolean okform() {
        Personne pe = personne.getPersonneByIdentifiant(this.identifiant);
        
        if(pe == null) {
            System.out.println("je suis la");
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Nom non trouvé");
            FacesContext.getCurrentInstance().addMessage(null,message);
            return false;
        }
       
        return true;
    }  
    public void connect() throws IOException {
        if(this.okform() == true){
            FacesContext context = FacesContext.getCurrentInstance();
            Personne user = personne.getPersonneByIdentifiant(this.identifiant);
            if(null == user.getRole() ){
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "connexion impossible");
                FacesContext.getCurrentInstance().addMessage(null,message);
            }else switch (user.getRole()) {
                case ADMIN:
                    context.getExternalContext().getSessionMap().put("user", user);
                    context.getExternalContext().redirect("faces/AdminUI.xhtml");
                    break;
                case CONSEILER:
                    context.getExternalContext().getSessionMap().put("user", user);
                    context.getExternalContext().redirect("faces/ConseillerUI.xhtml");
                    break;
                case CLIENT:
                    context.getExternalContext().getSessionMap().put("user", user);
                    context.getExternalContext().redirect("faces/ClientUI.xhtml");
                    break;
                default:
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "connexion impossible");
                    FacesContext.getCurrentInstance().addMessage(null,message);
                    break;
            }
            System.out.println(this.getLoggedUser().getNom());
    }
        
    }
    public void disconnect() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        try {
            // Redirection vers la page de connexion
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("logout", true);
            FacesContext.getCurrentInstance().getExternalContext().redirect("faces/index.xhtml");
        } catch (IOException ex) {
        }
    }
    
}
