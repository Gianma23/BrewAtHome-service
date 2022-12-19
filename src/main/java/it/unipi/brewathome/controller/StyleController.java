/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unipi.brewathome.controller;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Utente
 */

@Controller
@RequestMapping(path="/style")
public class StyleController {
    
    @GetMapping(path="/colors")
    public @ResponseBody String getColorTokens() throws IOException {
        
        String path = getClass().getResource("/style/colors.json").toString();
        path = path.substring(6);
        System.out.println(path);
        try {
            // default StandardCharsets.UTF_8
            String content = Files.readString(Path.of(path));
            
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
