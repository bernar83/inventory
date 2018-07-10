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
    private static ObservableList<Product> products = FXCollections.observableArrayList();;
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static int partId = 0;
    private static int productId = 0;
    
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }
    
    public static ObservableList<Product> getAllProducts() {
        return products;
    }
    
    public static int incrementPartId() {
        partId++;
        return partId;
    }
    
    public static int incrementProductId() {
        productId++;
        return productId;
    }
    
    public static void addProduct(Product product) {
        products.add(product);
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
    
    public static ObservableList<Product> lookupProduct(int id) {
        ObservableList<Product> product = FXCollections.observableArrayList();
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductId() == id) {
                product.add(products.get(i));
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
    
    public static ObservableList<Part> lookupPart(int id) {
        ObservableList<Part> searchedParts = FXCollections.observableArrayList();
        
        for (int i = 0; i < allParts.size(); i++) {
            if (allParts.get(i).getPartID() == id) {
                searchedParts.add(allParts.get(i));
            }
        }
        
        return searchedParts;
    }
    
    public static void updatePart(int id, Part part) {
        for (int i = 0; i < allParts.size(); i++) {
            if (allParts.get(i).getPartID()== id) {
                allParts.set(i, part);
            }
        }
    }
}
