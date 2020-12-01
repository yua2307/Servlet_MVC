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
public class Category implements Serializable {
    
    private static final long serialVersionUID =1L;
    
    
    private int idCate;
    private String cateName;

    public Category(int idCate, String cateName) {
        this.idCate = idCate;
        this.cateName = cateName;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public int getIdCate() {
        return idCate;
    }

    public void setIdCate(int idCate) {
        this.idCate = idCate;
    }
    
    
}
