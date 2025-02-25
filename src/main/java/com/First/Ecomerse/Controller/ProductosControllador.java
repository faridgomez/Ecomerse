package com.First.Ecomerse.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/Productos")
public class ProductosControllador {
    @GetMapping("")
    public String show() {
        return "Productos/Mostrar";
    }

    @GetMapping("/crearProductos")
    public String create() {
        return "Productos/crear";
    }
    

}
