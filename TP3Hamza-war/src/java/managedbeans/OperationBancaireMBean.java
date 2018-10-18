/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import entities.OperationBancaire;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import session.GestionnaireDeCompteBancaire;

/**
 *
 * @author Hamza
 */
@Named(value = "operationBancaireMBean")
@ViewScoped
public class OperationBancaireMBean  implements Serializable {

    /**
     * Creates a new instance of OperationBancaireMBean
     */
    public OperationBancaireMBean() {
    }
    @EJB
    private GestionnaireDeCompteBancaire gc;

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public List<OperationBancaire> getOperations() {
        return gc.getOperations(id);
    }

}
