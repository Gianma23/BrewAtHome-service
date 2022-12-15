/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unipi.brewathome;

import it.unipi.brewathome.repository.AccountRepository;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Utente
 */

@Controller
@RequestMapping(path="/account")
public class AccountController {
    
    @Autowired
    private AccountRepository accountRepository;
    
    /*@GetMapping(path="/login")
    public boolean login(String email, String passwordHash) {
        
        Account account = accountService.findByEmail(email);
        if(!passwordHash.equals(account.getPassword()))
            return false;
        
        return true;
    }*/
    
    @PostMapping(path="/add")
    public @ResponseBody String register(String email, String password) {
        
        Account account = new Account(email, password);
        accountRepository.save(account);
        
        return "Registred";
    }
    
}
