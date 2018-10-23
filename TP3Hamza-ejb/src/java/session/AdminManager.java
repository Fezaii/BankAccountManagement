/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Admin;
import entities.Client;
import entities.CompteBancaire;
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
    
    public Admin createAdmin(String nom, String prenom,Date date, String adresse, String telephone, String mail){
        Admin admin=new Admin(nom, prenom,date, adresse, telephone, mail);
        persist(admin);
        return admin;    
    }
    
    /*public void addClient (Client client){
        em.persist(client);
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
        createAdmin("fezai", "Ahmed",simpleDateFormat.parse("1940/10/09"),"Nice" ,"06200200", "john@beatles.com");  
        createAdmin("boudardar", "hamza",simpleDateFormat.parse("1942/09/18"),"Paris","07800800", "paul@beatles.com");  
    }
    
}
