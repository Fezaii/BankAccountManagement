/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Admin;
import entities.Client;
import entities.Conseiller;
import entities.Role;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author fezai
 */
@Stateless
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

 
    public Conseiller createConseiller(String nom, String prenom,Date date, String adresse, String telephone, String mail){
        Conseiller conseiller=new Conseiller(nom, prenom,date, adresse, telephone, mail);
        persist(conseiller);
        return conseiller;    
    }
    
    /**
     * Creer des clients test
     */
    public void creerConseillerTest() throws ParseException {  
        createConseiller("ahmed", "Ahmed",simpleDateFormat.parse("1940/10/09"),"Nice" ,"06200200", "john@beatles.com");  
        createConseiller("hamza", "hamza",simpleDateFormat.parse("1942/09/18"),"Paris","07800800", "paul@beatles.com");  
    }
}
