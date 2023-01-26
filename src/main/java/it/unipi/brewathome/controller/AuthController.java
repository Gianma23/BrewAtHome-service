package it.unipi.brewathome.controller;

import com.google.gson.Gson;
import it.unipi.brewathome.models.Account;
import it.unipi.brewathome.jwt.JwtUtils;
import it.unipi.brewathome.repository.AccountRepository;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


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
        Account account = gson.fromJson(request, Account.class);
        String email = account.getEmail();
        String password = account.getPassword();
        
        if(!accountRepository.existsByEmail(email))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nessun account esistente con questa email.");
        
        if(!password.equals(account.getPassword()))
            return ResponseEntity.badRequest().body("Password errata.");
        
        String token = jwtUtils.generateToken(email);
        return ResponseEntity.ok().header("Authorization", token).body("accesso riuscito!");
    }
    
    @PostMapping(path="/register")
    public @ResponseBody ResponseEntity<?> register(@RequestBody String request) {
        
        Gson gson = new Gson();
        Account account = gson.fromJson(request, Account.class);
        String email = account.getEmail();
        
        //controlli input
        Pattern pattern = Pattern.compile("^(.+)@(\\S+)$");
        if(!pattern.matcher(email).matches())
            return ResponseEntity.badRequest().body("Email non valida.");
        
        if(accountRepository.existsByEmail(email))
            return ResponseEntity.badRequest().body("Esiste già un account con questa email!");
            
        accountRepository.save(account);
        
        return ResponseEntity.ok("Account registrato con successo!");
    }
    
}
