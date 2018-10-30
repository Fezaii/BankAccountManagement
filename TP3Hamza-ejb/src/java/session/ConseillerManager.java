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
import entities.Personne;
import entities.Role;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author fezai
 */
@Stateful
@LocalBean
public class ConseillerManager {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "TP3Hamza-ejbPU")
    private EntityManager em;
    
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
    
 
    public void persist(Object object) {
        em.persist(object);
    }
    
    public List<Client> getAllClient(Conseiller conseiller){
        return conseiller.getListeClient();
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
    public Conseiller createConseiller(String nom, String prenom,Date date, String adresse, String telephone, String mail,String identifiant,String motdepasse){
        Conseiller conseiller=new Conseiller(nom, prenom,date, adresse, telephone, mail,identifiant,motdepasse);
        persist(conseiller);
        return conseiller;    
    }

    /**
     * Creer des clients test
     */
    public void creerConseillerTest() throws ParseException {  
        createConseiller("thomas", "laporte",simpleDateFormat.parse("1940/10/09"),"Nice" ,"06200200", "john@beatles.com","conseiller","conseiller2018");  
        createConseiller("boudradar", "hamza",simpleDateFormat.parse("1942/09/18"),"Paris","07800800", "paul@beatles.com","conseiller1","conseiller2019");  
    }
}
