package it.unipi.brewathome.controller;

import com.google.gson.Gson;
import it.unipi.brewathome.jwt.JwtUtils;
import it.unipi.brewathome.models.Ricetta;
import it.unipi.brewathome.repository.RicettaRepository;
import java.sql.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


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
    
    @GetMapping(path="/add")
    public @ResponseBody ResponseEntity<?> addRecipe(@RequestHeader(name = "Authorization") String token) {

        jwtUtils.validateToken(token);  
        String account = jwtUtils.getAccountFromToken(token);
        
        Ricetta ricetta = new Ricetta();
        ricetta.setAccountId(account);
        
        Ricetta ricettaAggiunta = ricettaRepository.save(ricetta);
        
        return ResponseEntity.ok().body(ricettaAggiunta);
    }
    
    @PostMapping(path="/update")
    public @ResponseBody ResponseEntity<?> updateRecipe(@RequestHeader(name = "Authorization") String token, @RequestBody String request) {

        jwtUtils.validateToken(token);  
        String account = jwtUtils.getAccountFromToken(token);
        
        Gson gson = new Gson();
        Ricetta ricettaNuova = gson.fromJson(request, Ricetta.class);
        
        Ricetta ricetta = ricettaRepository.findById(ricettaNuova.getId());
        if(!ricetta.getAccountId().equals(account))
            return ResponseEntity.badRequest().body("Questo account non ha i permessi.");
        
        ricettaNuova.setAccountId(ricetta.getAccountId());
        ricettaNuova.setUltimaModifica(new Timestamp((new java.util.Date()).getTime()));
        
        //controlli input
        if(!validateInput(ricettaNuova))
            return ResponseEntity.badRequest().body("Parametri non validi.");
        
        ricettaRepository.save(ricettaNuova);
        
        return ResponseEntity.ok().body("ricetta salvata con successo!");
    }
    
    @DeleteMapping(path="/remove")
    public @ResponseBody ResponseEntity<?> removeRecipe(@RequestHeader(name = "Authorization") String token, @RequestParam int recipe) {

        jwtUtils.validateToken(token);
        String account = jwtUtils.getAccountFromToken(token);
        
        Ricetta ricetta = ricettaRepository.findById(recipe);
        if(!ricetta.getAccountId().equals(account))
            return ResponseEntity.badRequest().body("Questo account non ha i permessi.");
        
        ricettaRepository.delete(ricetta);
        
        return ResponseEntity.ok().body(ricetta);
    }
    
    private boolean validateInput(Ricetta ric) {
        return !(ric.getVolume() <= 0 || ric.getRendimento() <= 0);
    }
}