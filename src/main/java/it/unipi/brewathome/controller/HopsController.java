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
        JsonElement json = gson.fromJson(recipe, JsonElement.class);
        JsonObject hopObj = json.getAsJsonObject();
        
        Ricetta ricetta = ricettaRepository.findById(hopObj.get("ricettaId").getAsInt());
        if(!ricetta.getAccountId().equals(account))
            return ResponseEntity.badRequest().body("Questo account non ha i permessi.");
        
        Luppolo luppolo = new Luppolo();
        luppolo.setId(hopObj.get("id").getAsInt());      
        luppolo.setRicettaId(hopObj.get("ricettaId").getAsInt());
        luppolo.setQuantita(hopObj.get("quantita").getAsInt());
        luppolo.setTempo(hopObj.get("tempo").getAsInt());
        luppolo.setAlpha(hopObj.get("alpha").getAsDouble());
        luppolo.setNome(hopObj.get("nome").getAsString());
        luppolo.setCategoria(hopObj.get("categoria").getAsString());
        luppolo.setFornitore(hopObj.get("fornitore").getAsString());
        luppolo.setProvenienza(hopObj.get("provenienza").getAsString());
        luppolo.setTipo(hopObj.get("tipo").getAsString());
        
        luppoloRepository.save(luppolo);
        
        return ResponseEntity.ok().body("Fermentabile aggiunto/modificato!");
    }
    
    @DeleteMapping(path="/remove")
    public @ResponseBody ResponseEntity<?> removeFermentable(@RequestHeader(name = "Authorization") String token, @RequestParam int id) {
       
        jwtUtils.validateToken(token);  
        String account = jwtUtils.getAccountFromToken(token);
        
        Luppolo luppolo = luppoloRepository.findById(id);
        Ricetta ricetta = ricettaRepository.findById(luppolo.getRicettaId());
        if(!ricetta.getAccountId().equals(account))
            return ResponseEntity.badRequest().body("Questo account non ha i permessi.");
        
        luppoloRepository.deleteById(id);
        
        return ResponseEntity.ok().body("Luppolo rimosso!");
    }
}