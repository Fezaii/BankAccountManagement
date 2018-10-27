/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Admin;
import entities.Client;
import entities.CompteBancaire;
import entities.Conseiller;
import entities.OperationBancaire;
import entities.Personne;
import entities.Role;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author fezai
 */
@Stateless
@LocalBean
public class AdminManager {

    @PersistenceContext(unitName = "TP3Hamza-ejbPU")
    private EntityManager em;
    
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
    
 
    public void persist(Object object) {
        em.persist(object);
    }

    public List<Client> getAllClient(){
        Query query = em.createNamedQuery("Personne.findByRole").setParameter("role",Role.CLIENT);
        return query.getResultList();
    }
    public List<Conseiller> getAllConseiller(){
        Query query = em.createNamedQuery("Personne.findByRole").setParameter("role",Role.CONSEILER);
        return query.getResultList();
    }
    public List<CompteBancaire> getAllComptes(){
        Query query = em.createNamedQuery("CompteBancaire.findAll");
        return query.getResultList();
    }
    public Admin createAdmin(String nom, String prenom,Date date, String adresse, String telephone, String mail,String identifiant,String motdepasse){
        Admin admin=new Admin(nom, prenom,date, adresse, telephone, mail,identifiant,motdepasse);
        persist(admin);
        return admin;    
    }
    public void deleteClient(Client client) {
        Query query = em.createNamedQuery("Personne.deleteById",Personne.class);
        query.setParameter("id",client.getId());
        query.executeUpdate();    
    }
    public Client getClientById(int id){
        Query query = em.createNamedQuery("Personne.findById").setParameter("id",id);
        return (Client)query.getSingleResult();    
    }
    
    public void deleteConseiller(Personne conseiller) {
        
    if (!em.contains(conseiller)) {
        conseiller = em.merge(conseiller);
    }
    em.remove(conseiller);
        
        /*Query query = em.createNamedQuery("Personne.deleteById",Personne.class);
        query.setParameter("id",conseiller.getId());
        query.executeUpdate();*/    
    }
    public Personne getConseillerById(long id){
        Query query = em.createNamedQuery("Personne.findById").setParameter("id",id);
        return (Personne)query.getSingleResult();    
    }
    
    public Personne getPersonneByIdentifiant(String identifiant){
        Query query = em.createNamedQuery("Personne.findByidentifiant").setParameter("identifiant",identifiant);
        return (Personne)query.getSingleResult();    
    }
    /*public void addClient (Client client){
        em.persist(client);
    }
        public Personne getConseillerById(long id){
        Query query = em.createNamedQuery("Personne.findById").setParameter("id",id);
        return (Personne)query.getSingleResult();    
    }
    
    public Client findClientByID(Long id){
        Query query = em.createQuery("SELECT c FROM Client c WHERE c.id =: id");
        query.setParameter("id", id);
        return (Client) query.getSingleResult();
    }
    
    public Client findClientByName(String nom){
        Query query= em.createQuery("SELECT c FROM Client c WHERE c.nom =:nom");
        query.setParameter("nom", nom);
         try {
            return (Client) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (NonUniqueResultException en) {
            List<Client> clients = (List<Client>) query.getResultList();

            return clients.get(0);
        }
    }
    
    /**
     * Creer des clients test
     */
    public void creerAdminTest() throws ParseException {  
        createAdmin("fezai", "ahmed",simpleDateFormat.parse("1940/10/09"),"Nice" ,"06200200", "john@beatles.com","admin","admin2018");  
    }
    
}
