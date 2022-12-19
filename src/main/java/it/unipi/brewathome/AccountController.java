/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unipi.brewathome;

import it.unipi.brewathome.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Utente
 */

@Controller
@RequestMapping(path="/auth")
public class AccountController {
    
    @Autowired
    private AccountRepository accountRepository;
    
    @PostMapping(path="/login")
    public @ResponseBody ResponseEntity<String> login(String email, String password) {
        Account account = accountRepository.findByEmail(email);
        
        if(account == null)
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email errata");
        if(!password.equals(account.getPassword()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password errata");
        return ResponseEntity.ok("logged in");
    }
    
    @PostMapping(path="/register")
    public @ResponseBody String register(String email, String password) {
        
        Account account = new Account(email, password);
        accountRepository.save(account);
        
        return "Registred";
    }
    
}
