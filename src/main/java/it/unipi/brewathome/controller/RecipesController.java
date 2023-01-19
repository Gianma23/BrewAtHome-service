package it.unipi.brewathome.controller;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import it.unipi.brewathome.jwt.JwtUtils;
import it.unipi.brewathome.models.Fermentabile;
import it.unipi.brewathome.models.Ricetta;
import it.unipi.brewathome.repository.FermentabileRepository;
import it.unipi.brewathome.repository.RicettaRepository;
import java.sql.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
@RequestMapping(path="/recipes")
public class RecipesController {
    
    @Autowired
    private RicettaRepository ricettaRepository;
    
    @Autowired
    private FermentabileRepository fermentabileRepository;
    
    @Autowired
    private JwtUtils jwtUtils;
    
    @GetMapping(path="/all")
    public @ResponseBody Iterable<Ricetta> getAccountRecipes(@RequestHeader(name = "Authorization") String token) {
        
        jwtUtils.validateToken(token);
        String account = jwtUtils.getAccountFromToken(token);
        
        return ricettaRepository.findByAccountId(account);
    }
    
    @GetMapping(path="/info")
    public @ResponseBody Ricetta getRecipe(@RequestHeader(name = "Authorization") String token, @RequestParam int recipe) {
        
        jwtUtils.validateToken(token);
        String account = jwtUtils.getAccountFromToken(token);
        
        Ricetta ricetta = ricettaRepository.findById(recipe);
        if(ricetta.getAccountId().equals(account))
            return ricetta;
        else
            return null;
    }
    
    @PostMapping(path="/add")
    public @ResponseBody ResponseEntity<?> addRecipes(@RequestHeader(name = "Authorization") String token) {
        
        jwtUtils.validateToken(token);  
        String account = jwtUtils.getAccountFromToken(token);
        
        Ricetta ricetta = new Ricetta();
        ricetta.setAccountId(account);
        ricetta.setAttrezzaturaId(0);
        ricetta.setUltimaModifica(new Timestamp((new java.util.Date()).getTime()));
        
        Ricetta ricettaAggiunta = ricettaRepository.save(ricetta);
        
        int ricettaId = ricettaAggiunta.getId();
        return ResponseEntity.ok().body(ricettaId);
    }
    
    @GetMapping(path="/fermentables")
    public @ResponseBody Iterable<Fermentabile> getFermentables(@RequestHeader(name = "Authorization") String token, @RequestParam int recipe) {
        
        jwtUtils.validateToken(token);  
        String account = jwtUtils.getAccountFromToken(token);
        Ricetta ricetta = ricettaRepository.findById(recipe);
        if(ricetta.getAccountId().equals(account))
            return null;
        
        Iterable<Fermentabile> fermentabile = fermentabileRepository.findByRicettaId(recipe);
        return fermentabile;
    }
    
    @PostMapping(path="/fermentables/add")
    public @ResponseBody ResponseEntity<?> addFermentable(@RequestHeader(name = "Authorization") String token, @RequestBody String request) {
        
        jwtUtils.validateToken(token);  
        String account = jwtUtils.getAccountFromToken(token);
        
        Gson gson = new Gson();
        JsonElement json = gson.fromJson(request, JsonElement.class);
        JsonObject fermentableObj = json.getAsJsonObject();
        
        Fermentabile fermentabile = new Fermentabile();
        fermentabile.setRicettaId(fermentableObj.get("ricetta_id").getAsInt());
        fermentabile.setQuantita(fermentableObj.get("quantita").getAsInt());
        fermentabile.setColore(fermentableObj.get("colore").getAsInt());
        fermentabile.setPotenziale(fermentableObj.get("potenziale").getAsInt());
        fermentabile.setRendimento(fermentableObj.get("rendimento").getAsInt());
        fermentabile.setNome(fermentableObj.get("nome").getAsString());
        fermentabile.setCategoria(fermentableObj.get("categoria").getAsString());
        fermentabile.setFornitore(fermentableObj.get("fornitore").getAsString());
        fermentabile.setProvenienza(fermentableObj.get("provenienza").getAsString());
        fermentabile.setTipo(fermentableObj.get("tipo").getAsString());
        
        Ricetta ricetta = ricettaRepository.findById(fermentableObj.get("ricetta_id").getAsInt());
        if(!ricetta.getAccountId().equals(account))
            return ResponseEntity.badRequest().body("Questo account non ha i permessi.");
        
        fermentabileRepository.save(fermentabile);
        
        return ResponseEntity.ok().body("Fermentabile aggiunto/modificato!");
    }
}