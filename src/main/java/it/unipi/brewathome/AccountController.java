/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unipi.brewathome;

import it.unipi.brewathome.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Utente
 */

@Controller
@RequestMapping(path="/account")
public class AccountController {
    
    @Autowired
    private AccountService accountService;
    
    @GetMapping(path="/login")
    public boolean login(String email, String passwordHash) {
        
        Account account = accountService.findByEmail(email);
        if(!passwordHash.equals(account.getPassword()))
            return false;
        
        return true;
    }
    
    
}
