package it.unipi.brewathome.controller;

import com.google.gson.Gson;
import it.unipi.brewathome.jwt.JwtUtils;
import it.unipi.brewathome.models.Fermentabile;
import it.unipi.brewathome.models.Luppolo;
import it.unipi.brewathome.models.Ricetta;
import it.unipi.brewathome.repository.LuppoloRepository;
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
@RequestMapping(path="/hops")
public class HopsController {
    
    @Autowired
    private RicettaRepository ricettaRepository;
    
    @Autowired
    private LuppoloRepository luppoloRepository;
        
    @Autowired
    private JwtUtils jwtUtils;
    
    
    @GetMapping(path="/all")
    public @ResponseBody Iterable<Luppolo> getHops(@RequestHeader(name = "Authorization") String token, @RequestParam int recipe) {
        
        jwtUtils.validateToken(token);  
        String account = jwtUtils.getAccountFromToken(token);
        Ricetta ricetta = ricettaRepository.findById(recipe);
        if(!ricetta.getAccountId().equals(account))
            return null;
        
        Iterable<Luppolo> luppolo = luppoloRepository.findByRicettaId(recipe);
        return luppolo;
    }
    
    @PostMapping(path="/add")
    public @ResponseBody ResponseEntity<?> addHop(@RequestHeader(name = "Authorization") String token, @RequestBody String recipe) {
        
        jwtUtils.validateToken(token);  
        String account = jwtUtils.getAccountFromToken(token);
        
        Gson gson = new Gson();
        Luppolo hop = gson.fromJson(recipe, Luppolo.class);
        
        Ricetta ricetta = ricettaRepository.findById(hop.getRicettaId());
        if(!ricetta.getAccountId().equals(account))
            return ResponseEntity.badRequest().body("Questo account non ha i permessi.");
        
        //controlli input
        if(!validateInput(hop))
            return ResponseEntity.badRequest().body("Parametri non validi.");
        
        luppoloRepository.save(hop);
        
        return ResponseEntity.ok().body(hop.getId());
    }
    
    @DeleteMapping(path="/remove")
    public @ResponseBody ResponseEntity<?> removeHop(@RequestHeader(name = "Authorization") String token, @RequestParam int id) {
       
        jwtUtils.validateToken(token);  
        String account = jwtUtils.getAccountFromToken(token);
        
        Luppolo luppolo = luppoloRepository.findById(id);
        Ricetta ricetta = ricettaRepository.findById(luppolo.getRicettaId());
        if(!ricetta.getAccountId().equals(account))
            return ResponseEntity.badRequest().body("Questo account non ha i permessi.");
        
        luppoloRepository.delete(luppolo);
        
        return ResponseEntity.ok().body("Luppolo rimosso!");
    }
    
    private boolean validateInput(Luppolo lup) {
        return !(lup.getQuantita() < 0 || lup.getAlpha() < 0 || lup.getTempo() < 0);
    }
}