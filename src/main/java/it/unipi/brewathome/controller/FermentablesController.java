package it.unipi.brewathome.controller;

import com.google.gson.Gson;
import it.unipi.brewathome.jwt.JwtUtils;
import it.unipi.brewathome.models.Fermentabile;
import it.unipi.brewathome.models.Ricetta;
import it.unipi.brewathome.repository.FermentabileRepository;
import it.unipi.brewathome.repository.RicettaRepository;
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
@RequestMapping(path="/fermentables")
public class FermentablesController {
    
    @Autowired
    private RicettaRepository ricettaRepository;
    
    @Autowired
    private FermentabileRepository fermentabileRepository;
        
    @Autowired
    private JwtUtils jwtUtils;
    
    @GetMapping(path="/all")
    public @ResponseBody Iterable<Fermentabile> getFermentables(@RequestHeader(name = "Authorization") String token, @RequestParam int recipe) {
        
        jwtUtils.validateToken(token);  
        String account = jwtUtils.getAccountFromToken(token);
        
        Ricetta ricetta = ricettaRepository.findById(recipe);
        if(!ricetta.getAccountId().equals(account))
            return null;

        Iterable<Fermentabile> fermentabile = fermentabileRepository.findByRicettaId(recipe);
        return fermentabile;
    }
    
    @PostMapping(path="/add")
    public @ResponseBody ResponseEntity<?> addFermentable(@RequestHeader(name = "Authorization") String token, @RequestBody String recipe) {
     
        jwtUtils.validateToken(token);  
        String account = jwtUtils.getAccountFromToken(token);
        
        Gson gson = new Gson();
        Fermentabile fermentable = gson.fromJson(recipe, Fermentabile.class);
        
        Ricetta ricetta = ricettaRepository.findById(fermentable.getRicettaId());
        if(!ricetta.getAccountId().equals(account))
            return ResponseEntity.badRequest().body("Questo account non ha i permessi.");
        
        fermentabileRepository.save(fermentable);
        
        return ResponseEntity.ok().body("Fermentabile aggiunto/modificato!");
    }
    
    @DeleteMapping(path="/remove")
    public @ResponseBody ResponseEntity<?> removeFermentable(@RequestHeader(name = "Authorization") String token, @RequestParam int id) {
     
        jwtUtils.validateToken(token);  
        String account = jwtUtils.getAccountFromToken(token);
        
        Fermentabile fermentabile = fermentabileRepository.findById(id);
        Ricetta ricetta = ricettaRepository.findById(fermentabile.getRicettaId());
        if(!ricetta.getAccountId().equals(account))
            return ResponseEntity.badRequest().body("Questo account non ha i permessi.");
        
        fermentabileRepository.delete(fermentabile);
        
        return ResponseEntity.ok().body(fermentabile);
    }
}