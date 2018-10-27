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
import entities.Role;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.persistence.Query;
import session.AdminManager;
import session.ConseillerManager;

/**
 *
 * @author fezai
 */
@Named(value = "adminMBean")
@RequestScoped
public class AdminMBean implements Serializable {
    
    @EJB
    private AdminManager adminmanager;
    //private List<Client> clients;

    /**
     * Creates a new instance of AdminMBean
     */
    public AdminMBean() {
    }
    
     public List<Client> Clients(){
        return adminmanager.getAllClient();
    }
     
    public List<Conseiller> Conseillers(){
        return adminmanager.getAllConseiller();
    }
    
    public List<CompteBancaire> Comptes(){
        return adminmanager.getAllComptes();
    }
  
    public String deleteClient(int id) {
        Client cl = adminmanager.getClientById(id);
        String res = "Vous avez supprimé le "+cl.getRole()+" "+ cl.getNom() + " " + cl.getPrenom();
        adminmanager.deleteClient(cl);        
        return res;
    }
    
    public String deleteConseiller(int id) {
        Personne cl = adminmanager.getConseillerById(id);
        String res = "Vous avez supprimé le "+cl.getRole()+" "+ cl.getNom() + " " + cl.getPrenom();
        adminmanager.deleteConseiller(cl);        
        return res;
    }
}
