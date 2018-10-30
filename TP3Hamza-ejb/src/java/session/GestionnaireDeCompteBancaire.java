/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Client;
import entities.CompteBancaire;
import entities.OperationBancaire;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Hamza
 */
@Stateless
@LocalBean
public class GestionnaireDeCompteBancaire {

    @PersistenceContext(unitName = "TP3Hamza-ejbPU")
    private EntityManager em;
   
    
    public CompteBancaire creerCompte(int solde) {
        CompteBancaire compte = new CompteBancaire(solde);
        persist(compte);
        return compte;
    }

    public List<Client> getListeClient(long id) {
        return getCompteByID(id).getListeClient();
    }


    public void persist(Object object) {
        em.persist(object);
    }
    
    public CompteBancaire update(CompteBancaire compte) {
        return em.merge(compte);
    }
    
   
    public void transferer(CompteBancaire source, CompteBancaire destination, float montant) {
    float val = source.retirer(montant);
    if (val == 0) {

      return;
    }
    destination.deposer(montant);
    update(source);
    update(destination);
  }
  
    public CompteBancaire getCompteByID(Long id){
        
        Query query = em.createNamedQuery("CompteBancaire.findById").setParameter("id", id);
       
        return (CompteBancaire) query.getSingleResult();
    }
    
   
    /**
     * Supprimer un compte bancaire
     * @param compte 
     */
    public void delete(CompteBancaire compte) {
        em.remove(em.merge(compte));
    }
    
    public void creerOperation(CompteBancaire compte, String descrption, float montant){
        OperationBancaire op = new OperationBancaire(descrption, montant);
        compte.getOperations().add(op);
    }
    
    public CompteBancaire consulter(long id) {   
        return em.find(CompteBancaire.class, id);
    }

  
    public CompteBancaire deposer(Long compte, float montant){
        CompteBancaire cpt = getCompteByID(compte);
        cpt.deposer(montant);
        //creerOperation(compte, "Dépot", montant);
        em.merge(cpt);
        
        return update (cpt);
    }
    
    public CompteBancaire retirer(Long compte, float montant){
        CompteBancaire cpt = getCompteByID(compte);
        cpt.retirer(montant);
        //creerOperation(compte, "Retrait", montant);
        em.merge(cpt);
        return update (cpt);
    }
    
    
    public void operation(int op, CompteBancaire compte, float montant){
               
        if (op == 0){
            compte = this.retirer(compte.getId(), montant);
        }
        else 
            if (op == 1) {
            compte= this.deposer(compte.getId(), montant);      
            }
        
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Opération réussie !","L'opération a été effectuée");  
          
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void transfertArgent(Long idDebiteur,Long idCrediteur, float montant){
        
        CompteBancaire compteDebiteur = getCompteByID(idDebiteur);
        CompteBancaire compteCrediteur= getCompteByID(idCrediteur);
        
        compteDebiteur.retirer(montant);
        compteCrediteur.deposer(montant);
        
        creerOperation(compteDebiteur, "Virement effectué à "+compteCrediteur.getListeClient().get(0).getNom()+"-"+compteCrediteur.getListeClient().get(0).getPrenom(), montant);
        creerOperation(compteCrediteur, "Virement reçu de "+ compteDebiteur.getListeClient().get(0).getNom()+"-"+compteDebiteur.getListeClient().get(0).getPrenom(), montant);
        
        em.merge(compteDebiteur);
        em.merge(compteCrediteur);
    }
        public List<OperationBancaire> getOperations(int id) {
        CompteBancaire c = em.find(CompteBancaire.class, id);
        return c.getOperations();
    }

}
