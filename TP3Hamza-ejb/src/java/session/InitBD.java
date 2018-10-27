/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author fezai
 */
@Startup
@Singleton
public class InitBD {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @EJB
    private GestionnaireDeCompteBancaire gc;
    @EJB
    private GestionnaireDesClients gg;
    @EJB
    private AdminManager ad;
    @EJB
    private ConseillerManager co;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PostConstruct
    public void initBase(){
        System.out.println("#### BD REMPLIE ###");
        //gc.creerCompteTest();
        try {
            ad.creerAdminTest();
        } catch (ParseException ex) {
            Logger.getLogger(InitBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            co.creerConseillerTest();
        } catch (ParseException ex) {
            Logger.getLogger(InitBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            gg.creerClientTest();
        } catch (ParseException ex) {
            Logger.getLogger(InitBD.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
