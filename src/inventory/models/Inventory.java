/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author naber
 */
public class Inventory {
    private ObservableList<Product> products;
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static int partId = 0;
    
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }
    
    public static int incrementPartId() {
        partId++;
        return partId;
    }
    public void addProduct(Product product) {
        this.products.add(product);
    }
    
    public boolean removeProduct(int id) {
        for (int i = 0; i < this.products.size(); i++) {
            if (this.products.get(i).getProductId() == id) {
                this.products.remove(i);
                return true;
            }
        }
        
        return false;
    }
    
    public Product lookupProduct(int id) {
        Product product = null;
        for (int i = 0; i < this.products.size(); i++) {
            if (this.products.get(i).getProductId() == id) {
                product = this.products.get(i);
            }
        }
        
        return product;
    }
    
    public void updateProduct(int id, Product product) {
        for (int i = 0; i < this.products.size(); i++) {
            if (this.products.get(i).getProductId() == id) {
                this.products.set(i, product);
            }
        }
    }
    
    public static void addPart(Part part) {
        allParts.add(part);
    }
    
    public static boolean deletePart(Part part) {
        return allParts.remove(part);
    }
    
    public Part lookupPart(int id) {
        Part part = null;
        for (int i = 0; i < this.allParts.size(); i++) {
            if (this.allParts.get(i).getPartID() == id) {
                part = this.allParts.get(i);
            }
        }
        
        return part;
    }
    
    public void updatePart(int id, Part part) {
        for (int i = 0; i < this.allParts.size(); i++) {
            if (this.allParts.get(i).getPartID()== id) {
                this.allParts.set(i, part);
            }
        }
    }
}
