package com.First.Ecomerse.Controller;

import java.io.IOException;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.First.Ecomerse.Model.Productos;
import com.First.Ecomerse.Model.Usuario;
import com.First.Ecomerse.Service.ProductoService;
import com.First.Ecomerse.Service.SuvirArchivosService;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/Productos")
public class ProductosControllador {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductosControllador.class);

    @Autowired // Inyección de dependencias
    private ProductoService productoService;

    @Autowired
    private SuvirArchivosService upload;

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
    public String save(Productos productos, @RequestParam("file") MultipartFile file) throws IOException {
        // Log para ver el objeto 'producto'
        LOGGER.info("Este es el objeto producto {}", productos);

        // Crear usuario ficticio (esto debe ser ajustado según tu lógica real)
        Usuario u = new Usuario(1, null, null, null, null, null, null, null, null);
        productos.setUsuarios(u);

        // Verificar si el producto es nuevo o si está actualizando uno existente
        if (productos.getIdProducto() == 0) { // Si el producto es nuevo
            // Guardar la imagen y obtener el nombre del archivo
            String nombreImagen = upload.saveImage(file);
            productos.setImg(nombreImagen); // Asignar el nombre de la imagen al producto
        } else {
            
        }

        // Guardar el producto
        productoService.save(productos);

        // Redirigir a la lista de productos
        return "redirect:/Productos";
    }

    @GetMapping("/Actualizar/{id}")
    public String edit(@PathVariable Integer id, Model model) {

        return "Productos/Actualizar";
    }

    @PostMapping("/update")
    public String update(Productos productos, @RequestParam("file") MultipartFile img)
            throws IOException {
        LOGGER.info("Este es el producto para actualizar {}", productos);

        productoService.update(productos);

        // Redirigir a la lista de productos
        return "redirect:/Productos";
    }

    @GetMapping("/eliminar/{id}")
    public String delete(@PathVariable Integer id) {

        productoService.delete(id);
        return "redirect:/Productos";
    }

}
