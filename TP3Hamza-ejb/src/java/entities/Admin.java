/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;


public class Admin extends Personne {
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<Client> listeClient = new ArrayList();

    public List<Client> getListeClient() {
        return listeClient;
    }

    public void setListeClient(List<Client> listeClient) {
        this.listeClient = listeClient;
    }

    public List<CompteBancaire> getListeComptes() {
        return listeComptes;
    }

    public void setListeComptes(List<CompteBancaire> listeComptes) {
        this.listeComptes = listeComptes;
    }
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<CompteBancaire> listeComptes = new ArrayList();
    
    public Admin(String name, String prenom, Date date, String adresse, String telephone, String mail,List<Client> listeClient,List<CompteBancaire> listeComptes) {
        super(name, prenom, date, adresse, telephone, mail);
        this.listeClient = listeClient;
        this.listeComptes = listeComptes;
    }
    
}
