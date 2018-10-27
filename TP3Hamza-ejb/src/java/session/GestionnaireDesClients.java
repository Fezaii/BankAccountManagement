/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Client;
import entities.CompteBancaire;
import entities.Conseiller;
import entities.OperationBancaire;
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
 * @author Hamza
 */
@Stateless
@LocalBean
public class GestionnaireDesClients {
    
    @PersistenceContext(unitName = "TP3Hamza-ejbPU")
    private EntityManager em;
    
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
    
    public void persist(Object object) {
        em.persist(object);
    }
    
    public Conseiller getPersonneById(int id){
        
        Query query = em.createNamedQuery("Personne.findById").setParameter("id",id);
        return (Conseiller)query.getSingleResult();    
    }
    /*public List<Client> getAllClient(){
        Query query = em.createQuery("SELECT c FROM Client c");
        return query.getResultList();
    }*/
    
    public Client createClient(String nom, String prenom,Date date, String adresse, String telephone, String mail, float solde,int consid,String identifiant,String motdepasse){
        Client client=new Client(nom, prenom,date, adresse, telephone, mail,identifiant,motdepasse);
        
        Conseiller cons = getPersonneById(consid);
        
        CompteBancaire compte= new CompteBancaire(solde);
    
        
        OperationBancaire operation = new OperationBancaire("Création du compte", solde);
        compte.setOperations(operation);
        cons.setListeClient(client);
        compte.setListeClient(client);
        client.setListeComptes(compte);

        persist(client);
        persist(compte);
        persist(cons);
        return client;
        
    }
    
    /*public void addClient (Client client){
        em.persist(client);
    }*
    
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
    }*/
    
    /**
     * Creer des clients test
     */
    public void creerClientTest() throws ParseException {  
        createClient("John", "Lennon",simpleDateFormat.parse("1940/10/09"),"Nice" ,"06200200", "john@beatles.com", 150000,3,"client","client2018");  
        createClient("Paul", "Mac Cartney",simpleDateFormat.parse("1942/09/18"),"Paris","07800800", "paul@beatles.com", 950000,3,"client1","client2019");  
        createClient("Ringo", "Starr",simpleDateFormat.parse("1940/07/07"),"Londre","06333980", "ringo@beatles.com", 20000,3,"client2","blbla");  
        createClient("Georges", "Harrisson",simpleDateFormat.parse("1943/02/25"),"Paris","07966098", "georges@beatles.com", 100000,3,"client3","blba");
        createClient("Hala","Ghoualmi",simpleDateFormat.parse("1988/01/13"),"Nice","07218218","hala@beatles.ca",123000,3,"client4","blabla");
 
    }
    

}
