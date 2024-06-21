package uniandes.edu.co.bancodelosandes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class BancoController {

    @GetMapping("/")
    public String index() {
        return "index";
    }
    
}
