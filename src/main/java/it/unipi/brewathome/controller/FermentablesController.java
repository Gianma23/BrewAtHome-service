package it.unipi.brewathome.controller;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import it.unipi.brewathome.jwt.JwtUtils;
import it.unipi.brewathome.models.Fermentabile;
import it.unipi.brewathome.models.Luppolo;
import it.unipi.brewathome.models.Ricetta;
import it.unipi.brewathome.repository.FermentabileRepository;
import it.unipi.brewathome.repository.LuppoloRepository;
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

/**
 *
 * @author Utente
 */

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
        JsonElement json = gson.fromJson(recipe, JsonElement.class);
        JsonObject fermentableObj = json.getAsJsonObject();
        
        Ricetta ricetta = ricettaRepository.findById(fermentableObj.get("ricettaId").getAsInt());
        if(!ricetta.getAccountId().equals(account))
            return ResponseEntity.badRequest().body("Questo account non ha i permessi.");
        
        Fermentabile fermentabile = new Fermentabile();

        fermentabile.setId(fermentableObj.get("id").getAsInt());
        fermentabile.setRicettaId(fermentableObj.get("ricettaId").getAsInt());
        fermentabile.setQuantita(fermentableObj.get("quantita").getAsInt());
        fermentabile.setColore(fermentableObj.get("colore").getAsInt());
        fermentabile.setPotenziale(fermentableObj.get("potenziale").getAsInt());
        fermentabile.setRendimento(fermentableObj.get("rendimento").getAsInt());
        fermentabile.setNome(fermentableObj.get("nome").getAsString());
        fermentabile.setCategoria(fermentableObj.get("categoria").getAsString());
        fermentabile.setFornitore(fermentableObj.get("fornitore").getAsString());
        fermentabile.setProvenienza(fermentableObj.get("provenienza").getAsString());
        fermentabile.setTipo(fermentableObj.get("tipo").getAsString());
        
        fermentabileRepository.save(fermentabile);
        
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
        
        fermentabileRepository.deleteById(id);
        
        return ResponseEntity.ok().body("Fermentabile rimosso!");
    }
}