/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Client;
import entities.CompteBancaire;
import entities.OperationBancaire;
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
    
    @EJB
    private GestionnaireDesClients managerClient;
    

    public void persist(Object object) {
        em.persist(object);
    }

    public List<Client> getAllClient(){
        Query query = em.createQuery("SELECT c FROM Client c");
        return query.getResultList();
    }
    
    public Client createClient(String nom, String prenom,Date date, String adresse, String telephone, String mail, float solde){
        Client client=new Client(nom, prenom,date, adresse, telephone, mail);
        
        CompteBancaire compte= new CompteBancaire(nom,solde);
        
        OperationBancaire operation = new OperationBancaire("Création du compte", solde);
        compte.getOperations().add(operation);
        
        compte.setClient(client);
        client.getListeComptes().add(compte);

        persist(client);
        persist(compte);
        return client;
        
    }
    
    public void addClient (Client client){
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
    public void creerClientTest() throws ParseException {  
        createClient("John", "Lennon",simpleDateFormat.parse("1940/10/09"),"Nice" ,"06200200", "john@beatles.com", 150000);  
        createClient("Paul", "Mac Cartney",simpleDateFormat.parse("1942/09/18"),"Paris","07800800", "paul@beatles.com", 950000);  
        createClient("Ringo", "Starr",simpleDateFormat.parse("1940/07/07"),"Londre","06333980", "ringo@beatles.com", 20000);  
        createClient("Georges", "Harrisson",simpleDateFormat.parse("1943/02/25"),"Paris","07966098", "georges@beatles.com", 100000);
        createClient("Hala","Ghoualmi",simpleDateFormat.parse("1988/01/13"),"Nice","07218218","hala@beatles.ca",123000);
         
         createClient("Morrison","Katelyn",simpleDateFormat.parse("1980/05/31"),"1895 Et, Avenue","1 83 864 7472-7625","nunc.In@convalliserat.org",399254);
         createClient("Shelton","Ferdinand",simpleDateFormat.parse("1972/08/15"),"6491 Cras Rd.","1 24 513 7075-6121","molestie.arcu.Sed@lobortistellusjusto.ca",394519);
         createClient("Whitaker","Lydia",simpleDateFormat.parse("1999/04/15"),"497-4250 Scelerisque Rd.","1 70 957 2023-3941","cursus.Integer@eumetus.edu",281554);
         createClient("Gregory","Arden",simpleDateFormat.parse("1981/02/23"),"863-7785 Lectus St.","1 54 298 8084-7049","Pellentesque.ut@mieleifendegestas.ca",509602);
         createClient("Tillman","Gregory",simpleDateFormat.parse("1976/07/13"),"Ap #735-1617 Dui, St.","1 58 636 1870-6505","a.mi.fringilla@vehiculaaliquet.ca",959450);
         createClient("Deleon","Quemby",simpleDateFormat.parse("1964/09/23"),"714-5987 Ipsum Street","1 67 339 7151-8696","id.libero.Donec@eget.edu",206468);
         createClient("Macias","Mohammad",simpleDateFormat.parse("1985/03/26"),"Ap #871-1205 Dui Road","1 40 416 1893-8653","euismod.urna.Nullam@ipsumacmi.ca",678031);
         createClient("Huber","Charity",simpleDateFormat.parse("1956/12/24"),"987-2711 Varius Rd.","1 13 984 3984-6835","mollis@montes.com",112336);


    }
    

}
