/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unipi.brewathome.controller;

import it.unipi.brewathome.jwt.JwtUtils;
import it.unipi.brewathome.models.Ricetta;
import it.unipi.brewathome.repository.RicettaRepository;
import java.sql.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Utente
 */

@Controller
@RequestMapping(path="/recipes")
public class RecipesController {
    
    @Autowired
    private RicettaRepository ricettaRepository;
    @Autowired
    private JwtUtils jwtUtils;
    
    @GetMapping(path="/all")
    public @ResponseBody Iterable<Ricetta> getAccountRecipes(@RequestHeader(name = "Authorization") String token) {
        
        jwtUtils.validateToken(token);
        String account = jwtUtils.getAccountFromToken(token);
        
        return ricettaRepository.findByAccountId(account);
    }
    
    @PutMapping(path="/add")
    public @ResponseBody ResponseEntity<?> addRecipes(@RequestHeader(name = "Authorization") String token) {
        
        jwtUtils.validateToken(token);  
        String account = jwtUtils.getAccountFromToken(token);
        
        Ricetta ricetta = new Ricetta();
        ricetta.setAccountId(account);
        ricetta.setAttrezzaturaId(0);
        ricetta.setUltimaModifica(new Timestamp((new java.util.Date()).getTime()));
        
        System.out.println(ricetta);
        ricettaRepository.save(ricetta);
        return ResponseEntity.ok("ricetta aggiunta");
    }
}
