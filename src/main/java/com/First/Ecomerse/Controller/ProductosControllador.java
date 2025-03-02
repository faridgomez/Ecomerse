package com.First.Ecomerse.Controller;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.First.Ecomerse.Model.Productos;
import com.First.Ecomerse.Model.Usuario;
import com.First.Ecomerse.Service.ProductoService;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/Productos")
public class ProductosControllador {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductosControllador.class);
    
    @Autowired  //Inyección de dependencias
    private ProductoService productoService;

    @GetMapping("")
    public String show(Model model) {
        model.addAttribute("productos", productoService.findAll());
        return "Productos/Mostrar";
    }

    @GetMapping("/crearProductos")
    public String create() {
        return "Productos/crear";
    }

    @PostMapping("/save")
    public String save(Productos productos) {
        LOGGER.info("este es el producto {}", productos);
        
        Usuario U = new Usuario(1, null, null, null, null, null, null, null, null);
        productos.setUsuarios(U);
        
        
        productoService.save(productos);
        return "redirect:/Productos";
    }

}
