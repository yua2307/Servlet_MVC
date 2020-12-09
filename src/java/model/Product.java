/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author macbookpro
 */
public class Product implements Serializable {
    
    
    private static final long serialVersionUID =1L;
     
    private int id;
    private String name;
    private String des;
    private int quantity;
    private int unitPrice;
    private Category category;

    public Product(String name, String des, int quantity, int unitPrice, Category category) {
        this.name = name;
        this.des = des;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.category = category;
    }
    
    
      public Product(int id, String name, String des, int quantity, int unitPrice) {
        this.id = id;
        this.name = name;
        this.des = des;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }
    
    
    public Product(int id, String name, String des, int quantity, int unitPrice, Category category) {
        this.id = id;
        this.name = name;
        this.des = des;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    
    
}
