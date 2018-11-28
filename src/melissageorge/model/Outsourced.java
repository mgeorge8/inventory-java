/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package melissageorge.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author melissageorge
 */
public class Outsourced extends Part {
    private StringProperty companyName = new SimpleStringProperty();;

    public Outsourced() {
        super();
    }
    public Outsourced(String name, int inStock, double price, int min, int max, String companyName) {
        super(name, inStock, price, min, max);
        this.companyName.set(companyName);
    }
    
    
    
    
    public String getCompanyName() {
        return companyName.get();
    }

    public void setCompanyName(String companyName) {
        this.companyName.set(companyName);
    }
    
    
}
