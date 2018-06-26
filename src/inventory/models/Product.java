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
public class Product {
    private ObservableList<Part> parts = FXCollections.observableArrayList();
    private int productID;
    private String name;
    private double price;
    private int inStock;
    private int min;
    private int max;
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public double getPrice() {
        return this.price;
    }
    
    public void setInStock(int stock) {
        this.inStock = stock;
    }
    
    public int getInStock() {
        return this.inStock;
    }
    
    public void setMin(int min) {
        this.min = min;
    }
    
    public int getMin() {
        return this.min;
    }
    
    public void setMax(int max) {
        this.max = max;
    }
    
    public int getMax() {
        return this.max;
    }
    
    public void addAssociatedPart(Part part) {
        this.parts.add(part);
    }
    
    public boolean removeAssociatedPart(int id) {
        for (int i = 0; i < this.parts.size(); i++) {
            if (this.parts.get(i).getPartID() == id) {
                this.parts.remove(i);
                return true;
            }
        }
        
        return false;
    }
    
    public Part lookupAssociatedPart(int id) {
        Part part = null;
        for (int i = 0; i < this.parts.size(); i++) {
            if (this.parts.get(i).getPartID() == id) {
                part = this.parts.get(i);
            }
        }
        
        return part;
    }
    
    public void setProductID(int id) {
        this.productID = id;
    }
    
    public int getProductId() {
        return this.productID;
    }
}
