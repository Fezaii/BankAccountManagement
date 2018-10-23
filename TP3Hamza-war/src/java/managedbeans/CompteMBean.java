/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import entities.CompteBancaire;
import entities.OperationBancaire;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.persistence.EntityManager;
import org.primefaces.context.RequestContext;
import session.GestionnaireDeCompteBancaire;

/**
 *
 * @author Hamza
 */
@Named(value = "compteMBean")
@ViewScoped
public class CompteMBean implements Serializable {

    @EJB
    private GestionnaireDeCompteBancaire compteManager;
    private List<CompteBancaire> listeComptes;
    private CompteBancaire compte;

    /* Compte courant dans la session, utilisé pour afficher ses détails ou 
     * pour faire une mise à jour du compte modifié dans la base */
    private EntityManager em;
    private Long idDebiteur;

    public void setIdDebiteur(Long idDebiteur) {
        this.idDebiteur = idDebiteur;
    }

    public void setIdCrediteur(Long idCrediteur) {
        this.idCrediteur = idCrediteur;
    }
    private Long idCrediteur;

    public Long getIdDebiteur() {
        return idDebiteur;
    }

    public Long getIdCrediteur() {
        return idCrediteur;
    }
    private float montant = 0;
    private int op;

    private String nom;
    private float solde;

    public CompteBancaire getCompte() {
        return compte;
    }

    public CompteMBean() {
    }

    /**
     * Renvoie la liste des clients pour affichage dans une DataTable
     *
     * @return
     */
    public void setCompte(CompteBancaire compte) {
        this.compte = compte;
    }

    public List<CompteBancaire> getComptes() {
        listeComptes = compteManager.getAllComptes();
        return listeComptes;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public String getNom() {
        return nom;
    }

    public float getSolde() {
        return solde;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setSolde(float solde) {
        this.solde = solde;
    }

    public List<OperationBancaire> getOperations() {
        return compte.getOperations();
    }

    /**
     * Renvoie les détails du client courant (celui dans l'attribut customer de
     * cette classe), qu'on appelle une propriété (property)
     *
     * @return
     */
    public CompteBancaire getDetails() {
        return compte;
    }

    public int getOp() {
        return op;
    }

    public void setOp(int op) {
        this.op = op;
    }

    /**
     * Action handler - appelé lorsque l'utilisateur sélectionne une ligne dans
     * la DataTable pour voir les détails
     *
     * @param compte
     * @return
     */
    public String showDetails(CompteBancaire compte) {
        System.out.println("managedbeans.CompteMBean.showDetails()");
        this.compte = compte;
        return "Historique?faces-redirect=true";
    }

    public String operation() {
        System.out.println("managedbeans.CompteMBean.operation()");
        return "Operation?faces-redirect=true";
    }

    public String operationType() {
        compteManager.operation(this.op, this.compte, this.montant);

        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Opération réussie !", "L'opération a été effectuée");

        FacesContext.getCurrentInstance().addMessage(null, message);

        return "ListeComptes?faces-redirect=true";
    }

    public String ajouterMontant() {
        compteManager.deposer(compte, montant);
        return "ajouter?faces-redirect=true";
    }

    public String retirerMontant() {
        compteManager.retirer(compte, montant);
        return "ListeComptes?faces-redirect=true";
    }

    public String transfertMontant() {
        compteManager.transfertArgent(idDebiteur, idCrediteur, montant);

        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Virement réussi !", "Le viremenent a été effectué");

        FacesContext.getCurrentInstance().addMessage(null, message);
        return "ListeComptes?faces-redirect=true";
    }

    public String transfert() {
        CompteBancaire source = compteManager.getCompteByID(idDebiteur);
        CompteBancaire dest = compteManager.getCompteByID(idCrediteur);
        source.retirer(montant);
        dest.deposer(montant);
        compteManager.update(source);
        compteManager.update(dest);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Transfert effectué avec succès!");
        FacesContext.getCurrentInstance().addMessage(null, message);
        //FacesContext.getCurrentInstance().getExternalContext().getFlash().setRedirect(true);
        //FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);

        //RequestContext.getCurrentInstance().showMessageInDialog(message);
        return "ListeComptes?faces-redirect=true";

    }

    public String suppression(CompteBancaire compte) {
        System.out.println("managedbeans.CompteMBean.suppression()");
        compteManager.delete(compte);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Suppression réussie !", "La suppression a été effectuée");

        FacesContext.getCurrentInstance().addMessage(null, message);
        return "ListeComptes";
    }

    public String creerCompte() {
        compteManager.creerCompte(nom, solde);
        return "ListeComptes?faces-redirect=true";
    }

    /**
     * Action handler - met à jour la base de données en fonction du client
     * passé en paramètres
     *
     * @return
     */
    public String update() {
        System.out.println("###UPDATE###");
        compte = compteManager.update(compte);
        return "ListeComptes";
    }

    /**
     * Action handler - renvoie vers la page qui affiche la liste des clients
     *
     * @return
     */
    public String list() {
        return "ListeComptes?faces-redirect=true";
    }

    public String colorow(float solde) {
        if (solde < 0) {
            return "TableRowRed";
        } else {
            return "TableRowGray";
        }
    }

}
