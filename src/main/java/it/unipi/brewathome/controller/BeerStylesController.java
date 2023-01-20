package it.unipi.brewathome.controller;

import it.unipi.brewathome.models.Stile;
import it.unipi.brewathome.repository.StileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Utente
 */

@Controller
@RequestMapping(path="/categories")
public class BeerStylesController {
    
    @Autowired
    private StileRepository stileRepository;
     
    @GetMapping(path="/all")
    public @ResponseBody Iterable<Stile> getAllStyleGuides() {
        return stileRepository.findAll();
    }
    
    @GetMapping(path="/filter")
    public @ResponseBody Iterable<Stile> getStyleGuide(@RequestParam String guide) {
        return stileRepository.findByGuida(guide);
    }
}
