package com.First.Ecomerse.Controller;

import org.slf4j.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.First.Ecomerse.Model.Productos;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/Productos")
public class ProductosControllador {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductosControllador.class);

    @GetMapping("")
    public String show() {
        return "Productos/Mostrar";
    }

    @GetMapping("/crearProductos")
    public String create() {
        return "Productos/crear";
    }

    @PostMapping("/save")
    public String save(Productos productos) {
        LOGGER.info("este es el producto {}", productos);
        return "redirect:/Productos";
    }

}
