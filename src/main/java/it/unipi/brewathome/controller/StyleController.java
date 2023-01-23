package it.unipi.brewathome.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/style")
public class StyleController {
    
    @GetMapping(path="/colors")
    public @ResponseBody String getColorTokens() throws IOException {
        
        String path = getClass().getResource("/style/colors.json").toString();
        path = path.substring(6);
        try {
            String content = Files.readString(Path.of(path));
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
