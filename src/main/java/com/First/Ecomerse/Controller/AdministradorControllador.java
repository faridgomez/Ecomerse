package com.First.Ecomerse.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.First.Ecomerse.Model.Productos;
import com.First.Ecomerse.Service.ProductoService;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/Admin")
public class AdministradorControllador {
    @Autowired
    private ProductoService productoService;

    @GetMapping("")    
    public String home(Model model){

        List<Productos> productos = productoService.findAll();
        model.addAttribute("producto", productos);

        return "Administrador/home";
    }

}
