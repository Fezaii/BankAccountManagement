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
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author Hamza
 */
@Entity
public class Client extends Personne implements Serializable{

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<CompteBancaire> listeComptes = new ArrayList();

    public Client() {
    }

    public List<CompteBancaire> getListeComptes() {
        return listeComptes;
    }

    public void setListeComptes(CompteBancaire compte) {
        this.listeComptes.add(compte);
    }

    public Client(String name, String prenom, Date date, String adresse, String telephone, String mail) {
        super(name, prenom, date, adresse, telephone, mail);
        this.role = Role.CLIENT;
    }

}
