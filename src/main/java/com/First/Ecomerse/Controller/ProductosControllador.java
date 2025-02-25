package com.First.Ecomerse.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Productos")
public class ProductosControllador {
    @GetMapping("")
    public String show() {
        return "Productos/show";
    }

}
