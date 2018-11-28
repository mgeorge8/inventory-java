/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package melissageorge.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author melissageorge
 */
public class Inhouse extends Part {
    private IntegerProperty machineID = new SimpleIntegerProperty();;

    public Inhouse(String name, int inStock, double price, int min, int max, int machineID) {
        super(name, inStock, price, min, max);
        this.machineID.set(machineID);
    }

    public Inhouse() {
        super();
    }
    

    public int getMachineID() {
        return machineID.get();
    }

    public void setMachineID(int machineID) {
        this.machineID.set(machineID);
    }
    
    
}
