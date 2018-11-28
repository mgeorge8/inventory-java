/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package melissageorge.model;

import java.util.ArrayList;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author melissageorge
 */
public class Product {
    private ObservableList<Part> associatedParts;
    private IntegerProperty productID;
    private StringProperty name;
    private DoubleProperty price;
    private IntegerProperty inStock;
    private IntegerProperty min;
    private IntegerProperty max;
    private static int count;

    public Product(ObservableList<Part> associatedParts, String name, double price, int inStock, int min, int max) {
        ++count;
        this.associatedParts = FXCollections.observableArrayList(associatedParts);
        this.productID = new SimpleIntegerProperty(count);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
        this.inStock = new SimpleIntegerProperty(inStock);
        this.min = new SimpleIntegerProperty(min);
        this.max = new SimpleIntegerProperty(max);
    }

    public Product() {
        ++count;
        this.associatedParts = FXCollections.observableArrayList();
        this.productID = new SimpleIntegerProperty(count);
        this.name = new SimpleStringProperty();
        this.price = new SimpleDoubleProperty();
        this.inStock = new SimpleIntegerProperty();
        this.min = new SimpleIntegerProperty();
        this.max = new SimpleIntegerProperty();
    }

    public ObservableList<Part> getAssociatedParts() {
        return associatedParts;
    }

    public void setAssociatedParts(ObservableList<Part> associatedParts) {
        this.associatedParts = associatedParts;
    }

    public int getProductID() {
        return productID.get();
    }

    public void setProductID(int productID) {
        this.productID.set(productID);
    }
    
    public IntegerProperty getProductIDProperty() {
        return productID;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }
    
    public StringProperty getProductNameProperty() {
        return name;
    }

    public double getPrice() {
        return price.get();
    }

    public void setPrice(double price) {
        this.price.set(price);
    }
    
    public DoubleProperty getProductPriceProperty() {
        return price;
    }

    public int getInStock() {
        return inStock.get();
    }

    public void setInStock(int inStock) {
        this.inStock.set(inStock);
    }
    
    public IntegerProperty getProductStockProperty() {
        return inStock;
    }

    public int getMin() {
        return min.get();
    }

    public void setMin(int min) {
        this.min.set(min);
    }

    public int getMax() {
        return max.get();
    }

    public void setMax(int max) {
        this.max.set(max);
    }
    
    public void addAssociatedPart(Part part) {
        if(!associatedParts.contains(part)) {
            associatedParts.add(part);
        }      
    }
    
    public boolean removeAssociatedPart(int partID) {
        Part part = lookupAssociatedPart(partID);
        if(part != null) {
            associatedParts.remove(part);
            return true;
        } else {
            return false;
        }
    }
    
    public Part lookupAssociatedPart(int partID) {
        for(int i = 0; i < this.associatedParts.size(); i++) {
            if ((this.associatedParts.get(i).getPartID())==partID) {
                return this.associatedParts.get(i);
            } 
        }
            return null; 
    }

    @Override
    public String toString() {
        return "" + name.get();
    }
    
    
}
