/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.models;

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
 * @author naber
 */
public class Product {
    private ObservableList<Part> parts = FXCollections.observableArrayList();
    private IntegerProperty productID;
    private StringProperty name;
    private DoubleProperty price;
    private IntegerProperty inStock;
    private IntegerProperty min;
    private IntegerProperty max;
    
    public Product() {
        this.productID = new SimpleIntegerProperty();
        this.name = new SimpleStringProperty();
        this.price = new SimpleDoubleProperty();
        this.inStock = new SimpleIntegerProperty();
        this.min = new SimpleIntegerProperty();
        this.max = new SimpleIntegerProperty();
    }
    
    public void setName(String name) {
        this.name.set(name); 
    }
    
    public String getName() {
        return this.name.get();
    }
    
    public StringProperty getPropertyProductName() {
        return name;
    }
    
    public void setPrice(double price) {
        this.price.set(price);
    }
    
    public double getPrice() {
        return this.price.get();
    }
    
    public DoubleProperty getPropertyPrice() {
        return price;
    }
    
    public void setInStock(int stock) {
        this.inStock.set(stock);
    }
    
    public int getInStock() {
        return this.inStock.get();
    }
    
    public IntegerProperty getPropertyStock() {
        return inStock;
    }
    
    public void setMin(int min) {
        this.min.set(min);
    }
    
    public int getMin() {
        return this.min.get();
    }
    
    public IntegerProperty getPropertyMin() {
        return min;
    }
    
    public void setMax(int max) {
        this.max.set(max);
    }
    
    public int getMax() {
        return this.max.get();
    }
    
    public IntegerProperty getPropertyMax() {
        return max;
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
        this.productID.set(id);
    }
    
    public int getProductId() {
        return this.productID.get();
    }
    
    public IntegerProperty getPropertyProductId() {
        return productID;
    }
}
