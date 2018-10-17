/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import session.GestionnaireDeCompteBancaire;
import tp3.CompteBancaire;

/**
 *
 * @author fezai
 */
@Named(value = "compteBancaireMBean")
@ViewScoped
public class CompteBancaireMBean implements Serializable {
    private List<CompteBancaire> compteList;
    
    @EJB
    private GestionnaireDeCompteBancaire gestionnaireDeCompteBancaire;
  
     /* Creates a new instance of CompteBancaireMBean
     */
    public CompteBancaireMBean() {
    }
       /** 
     * Renvoie la liste des clients pour affichage dans une DataTable 
     * @return 
     */  
    public List<CompteBancaire> getAllComptes() {  
        return  gestionnaireDeCompteBancaire.getAllComptes();
    }  
  
    /** 
     * Action handler - appelé lorsque l'utilisateur sélectionne une ligne dans 
     * la DataTable pour voir les détails 
     */  
    public void create(){
        gestionnaireDeCompteBancaire.creerComptesTest();
    } 
  
}
