/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unipi.brewathome;

import java.io.Serializable;

/**
 *
 * @author Utente
 */
public class AuthRequest implements Serializable {
    
    public String email;   
    public String password;
    
    public AuthRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
        
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
