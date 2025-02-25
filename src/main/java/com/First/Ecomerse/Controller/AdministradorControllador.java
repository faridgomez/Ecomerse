package com.First.Ecomerse.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("/Admin")
public class AdministradorControllador {
    @GetMapping("")    
    public String home(){
        return "Administrador/home";
    }

}
