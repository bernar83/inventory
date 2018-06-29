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

/**
 *
 * @author naber
 */
public abstract class Part {
    private IntegerProperty partID;
    private StringProperty name;
    private DoubleProperty price;
    private IntegerProperty inStock;
    private IntegerProperty min;
    private IntegerProperty max;
    
    public Part() {
        partID = new SimpleIntegerProperty();
        name = new SimpleStringProperty();
        price = new SimpleDoubleProperty();
        inStock = new SimpleIntegerProperty();
        min = new SimpleIntegerProperty();
        max = new SimpleIntegerProperty();
    }
    
    public void setName(String name) {
        this.name.set(name);
    }
    
    public String getName() {
        return this.name.get();
    }
    
    public StringProperty getPropertyName() {
        return name;
    }
    
    public void setPrice(Double price) {
        this.price.set(price);
    }
    
    public Double getPrice() {
        return this.price.get();
    }
    
    public DoubleProperty getPropertyPrice() {
        return price;
    }
    
    public void setInStock(Integer stock) {
        this.inStock.set(stock);
    }
    
    public Integer getInStock() {
        return this.inStock.get();
    }
    
    public IntegerProperty getPropertyInStock() {
        return inStock;
    }
    
    public void setMin(Integer min) {
        this.min.set(min);
    }
    
    public Integer getMin() {
        return this.min.get();
    }
    
    public IntegerProperty getPropertyMin() {
        return min;
    }
    
    public void setMax(Integer max) {
        this.max.set(max);
    }
    
    public Integer getMax() {
        return this.max.get();
    }
    
    public IntegerProperty getPropertyMax() {
        return max;
    }
    
    public void setPartID(Integer id) {
        this.partID.set(id);
    }
    
    public Integer getPartID() {
        return this.partID.get();
    }
    
    public IntegerProperty getPropertyPartID() {
        return partID;
    }
}
