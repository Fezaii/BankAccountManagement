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
import javax.persistence.OneToMany;

@Entity
public class Admin extends Personne implements Serializable{

    public Admin() {
    }
    public Admin(String name, String prenom, Date date, String adresse, String telephone, String mail,String identifiant,String motdepasse) {
        super(name, prenom, date, adresse, telephone, mail,identifiant,motdepasse);
        this.role = Role.ADMIN;
    }
    
}
