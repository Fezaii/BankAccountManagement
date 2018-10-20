/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

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
    private GestionnaireDesClients gc;
    
    public CompteBancaire creerCompte(String nom,int solde) {
        CompteBancaire compte = new CompteBancaire(nom,solde);
        persist(compte);
        return compte;
    }

    public List<CompteBancaire> getAllComptes() {
        Query query = em.createNamedQuery("CompteBancaire.findAll");
        List<CompteBancaire> liste = query.getResultList();
        return liste;
    }

    public String creerCompteTest() {
        creerCompte("Olivier",150000);
        creerCompte("Hamza",950000);
        creerCompte("Niagara",20000);
        creerCompte("Poulga",100000);
        return "liste_comptes";
    }

    public void persist(Object object) {
        em.persist(object);
    }
public CompteBancaire update(CompteBancaire compte) {
        return em.merge(compte);
    }
    
    public CompteBancaire creerCompte(String nom, float solde){
        CompteBancaire compte = new CompteBancaire(nom,solde);
        creerOperation(compte, "Création du compte", solde);
        persist(compte);       
        return compte;
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
    
    public CompteBancaire getCompteByName(String nom){
        
        Query query = em.createNamedQuery("CompteBancaire.findByName").setParameter("nom", nom);
        if(query.getResultList().size() > 0) {
            return (CompteBancaire)query.getSingleResult();
        }
        else {
            return null;
        }
    }
    
    public int getNBComptes(){
        Query query = em.createNamedQuery("CompteBancaire.nbComptes");
        
        return ((Long) query.getSingleResult()).intValue();
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

  
    public CompteBancaire deposer(CompteBancaire compte, float montant){
        compte.deposer(montant);
        creerOperation(compte, "Dépot", montant);
        em.merge(compte);
        
        return update (compte);
    }
    
    public CompteBancaire retirer(CompteBancaire compte, float montant){
        compte.retirer(montant);
        creerOperation(compte, "Retrait", montant);
        em.merge(compte);
        return update (compte);
    }
    
    
    public void operation(int op, CompteBancaire compte, float montant){
               
        if (op == 0){
            compte = this.retirer(compte, montant);
        }
        else 
            if (op == 1) {
            compte= this.deposer(compte, montant);      
            }
        
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Opération réussie !","L'opération a été effectuée");  
          
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void transfertArgent(Long idDebiteur,Long idCrediteur, float montant){
        
        CompteBancaire compteDebiteur = getCompteByID(idDebiteur);
        CompteBancaire compteCrediteur= getCompteByID(idCrediteur);
        
        compteDebiteur.retirer(montant);
        compteCrediteur.deposer(montant);
        
        creerOperation(compteDebiteur, "Virement effectué à "+compteCrediteur.getClient().getNom()+"-"+compteCrediteur.getClient().getPrenom(), montant);
        creerOperation(compteCrediteur, "Virement reçu de "+ compteDebiteur.getClient().getNom()+"-"+compteDebiteur.getClient().getPrenom(), montant);
        
        em.merge(compteDebiteur);
        em.merge(compteCrediteur);
    }
        public List<OperationBancaire> getOperations(int id) {
        CompteBancaire c = em.find(CompteBancaire.class, id);
        return c.getOperations();
    }

}
