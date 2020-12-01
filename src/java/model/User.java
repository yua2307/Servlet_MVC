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
public class User implements Serializable {
    
    
     private static final long serialVersionUID =1L;
     
     
    private int id ;
        private String name;
    private String userName;
    private String passWord;
    private String role;

    public User(int id, String name, String userName, String passWord, String role) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.passWord = passWord;
        this.role = role;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    
}
