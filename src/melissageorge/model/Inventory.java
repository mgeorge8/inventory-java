/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package melissageorge.model;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author melissageorge
 */
public class Inventory {
//    private static Inventory instance = new Inventory();
    
    public static ObservableList<Product> products = FXCollections.observableArrayList();
    public static ObservableList<Part> allParts = FXCollections.observableArrayList();
    
//    public static Inventory getInstance() {
//        return instance;
//    }

    public static void addProduct(Product product) {
        if(!(products.contains(product))) {
            products.add(product);
        }
    }
    
    public static boolean removeProduct(int productID) {
        Product product = lookupProduct(productID);
        if(product != null) {
            products.remove(product);
            return true;
        } else {
            return false;
        }
    }
    
    public static Product lookupProduct(int productID) {
        for(int i = 0; i < products.size(); i++) {
            if ((products.get(i).getProductID())==productID) {
                return products.get(i);
            } 
        }
        return null; 
    }
    
    public void updateProduct(int productID) {
   
        
    }
    
    public static ObservableList<Product> getProducts() {
        return products;
    }
    
    public static ObservableList<Part> getParts() {
        return allParts;
    }
    
    public static void addPart(Part part) {
        if(!(allParts.contains(part))) {
            allParts.add(part);
        }
    }
    
    public static boolean deletePart(int partID) {
        Part part = lookupPart(partID);
        if(part != null) {
            allParts.remove(part);
            return true;
        } else {
            return false;
        }
    }
    
    public static Part lookupPart(int partID) {
        for(int i = 0; i < allParts.size(); i++) {
            if ((allParts.get(i).getPartID()) == partID) {
                return allParts.get(i);
            } 
        }
        return null; 
    }
    
    
}
