/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.lang.String;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author fezai
 */
@Entity
public class Conseiller extends Personne implements Serializable{

    public Conseiller() {
    }
    
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<Client> listeClient = new ArrayList();

    public List<Client> getListeClient() {
        return listeClient;
    }

    public void setListeClient(Client client) {
        this.listeClient.add(client);
    }
    
    public Conseiller(String name, String prenom, Date date, String adresse, String telephone, String mail) {
        super(name,prenom,date,adresse,telephone,mail);
        this.role = Role.CONSEILER;       
    }
}
