/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import tp3.CompteBancaire;

/**
 *
 * @author fezai
 */
@Stateless
@LocalBean
//@NamedQueries({@NamedQuery (name= "CompteBancaire.findall", query = "SELECT c FROM CompteBancaire")})
public class GestionnaireDeCompteBancaire implements Serializable {

    @PersistenceContext(unitName = "TP3BanqueFezai-ejbPU")
    private EntityManager em;

    public List<CompteBancaire> getAllComptes() {
        Query query = em.createNamedQuery("CompteBancaire.findAll");  
        return query.getResultList();
    }

    public void creerCompte(CompteBancaire comptebancaire) {
        em.persist(comptebancaire);
    }
    public void creerComptesTest() {  
       
        creerCompte(new CompteBancaire("John Lennon", 150000));  
        creerCompte(new CompteBancaire("Paul McCartney", 950000));  
        creerCompte(new CompteBancaire("Ringo Starr", 20000));  
        creerCompte(new CompteBancaire("Georges Harrisson", 100000));
    }

}
