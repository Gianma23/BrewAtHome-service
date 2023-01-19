/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unipi.brewathome.controller;

import com.google.gson.Gson;
import it.unipi.brewathome.AuthRequest;
import it.unipi.brewathome.models.Account;
import it.unipi.brewathome.jwt.JwtUtils;
import it.unipi.brewathome.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Utente
 */

@Controller
@RequestMapping(path="/auth")
public class AuthController {
    
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private JwtUtils jwtUtils;
    
    @PostMapping(path="/login")
    public @ResponseBody ResponseEntity<?> login(@RequestBody String request) {
         
        Gson gson = new Gson();
        System.out.println(request);
        AuthRequest authRequest = gson.fromJson(request, AuthRequest.class);
        String email = authRequest.getEmail();
        String password = authRequest.getPassword();
        
        if(!accountRepository.existsByEmail(email))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nessun account esistente con questa email.");
            
        Account account = accountRepository.findByEmail(email);
        
        if(!password.equals(account.getPassword()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password errata.");
        
        //TODO log of login
        String token = jwtUtils.generateToken(email);
        System.out.println(token);
        return ResponseEntity.ok().header("Authorization", token).body("accesso riuscito!");
    }
    
    @PostMapping(path="/register")
    public @ResponseBody ResponseEntity<?> register(@RequestBody String request) {
        
        Gson gson = new Gson();
        AuthRequest authRequest = gson.fromJson(request, AuthRequest.class);
        String email = authRequest.getEmail();
        String password = authRequest.getPassword();
        
        if(accountRepository.existsByEmail(email))
            return ResponseEntity.badRequest().body("Esiste già un account con questa email!");
            
        Account account = new Account(email, password);
        accountRepository.save(account);
        
        return ResponseEntity.ok("Account registrato con successo!");
    }
    
}
