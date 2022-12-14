/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package it.unipi.brewathome.service;

import it.unipi.brewathome.Account;

/**
 *
 * @author Utente
 */
public interface AccountService {
    
    void save(Account account);
    
    Account findByEmail(String email);
}
