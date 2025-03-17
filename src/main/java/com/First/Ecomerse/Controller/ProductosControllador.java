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
import com.First.Ecomerse.Service.SubirArchivosService;

import org.springframework.web.bind.annotation.PostMapping;
import java.util.Optional;

@Controller
@RequestMapping("/Productos")
public class ProductosControllador {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductosControllador.class);

    @Autowired // Inyección de dependencias
    private ProductoService productoService;

    @Autowired
    private SubirArchivosService subirImg;

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
            String nombreImagen = subirImg.saveImage(file);
            productos.setImg(nombreImagen); // Asignar el nombre de la imagen al producto
        } 

        // Guardar el producto
        productoService.save(productos);

        // Redirigir a la lista de productos
        return "redirect:/Productos";
    }

    @GetMapping("/Actualizar/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        // Obtener el producto por su ID
        Optional<Productos> optionalProductos = productoService.get(id);

        // Verificar si el producto existe
        if (optionalProductos.isPresent()) {
            Productos productos = optionalProductos.get();
            LOGGER.info("PRODUCTO DE ACTUALIZAR: {}", productos);

            // Pasar el producto al modelo para que esté disponible en la vista
            model.addAttribute("productos", productos);
        } else {
            LOGGER.warn("PRODUCTO CON ID {} NO ENCONTRADO", id);
            // Puedes manejar el caso en que el producto no exista, por ejemplo,
            // redirigiendo a una página de error
            return "redirect:/error"; // Redirige a una página de error o a la lista de productos
        }

        // Retornar la vista de actualización queda pendiemte logica y vista de
        // actializar productos
        return "Productos/Actualizar";
    }

    @PostMapping("/update")
    public String update(Productos productos, @RequestParam("file") MultipartFile img)
            throws IOException {
        LOGGER.info("Este es el producto para actualizar {}", productos);

        Productos p = new Productos();
        p = productoService.get(productos.getIdProducto()).get();
        productos.setUsuarios(p.getUsuarios()); //para dejar el mismo usuario mientras se establecen usuarios
        
        if(img.isEmpty()){
            productos.setImg(p.getImg()); //Cuando editamos el producto pero no cambiamos la imagen            
        }
        else
        {
        if(!p.getImg().equals("principal.jpg")){
            subirImg.deleteImage(p.getImg());
        }
        String nombreImagen = subirImg.saveImage(img);
        productos.setImg(nombreImagen); 

        }
        productoService.update(productos);

        // Redirigir a la lista de productos
        return "redirect:/Productos";
    }

    @GetMapping("/eliminar/{id}")
    public String delete(@PathVariable int id) {

        // Obtener el producto por su ID
        Optional<Productos> productoOpt = productoService.get(id);

        if (productoOpt.isPresent()) {
            // Si el producto existe, obtenemos el nombre
            String nombre = productoOpt.get().getImg();

            if (!nombre.equals("principal.jpg")) {

                subirImg.deleteImage(nombre);
            } else {

                LOGGER.info("No se puede eliminar la imagen principal {}", nombre);
                // Redirigir a la lista de productos

            }

            // subirImg.deleteImage(nombre);
            // Eliminar el producto
            productoService.delete(id);

            // Redirigir a la lista de productos (puedes cambiar esta redirección a la ruta
            // que prefieras)
            return "redirect:/Productos";
        } else {
            // Si el producto no existe, puedes devolver un mensaje de error o redirigir a
            // otro lugar
            return "redirect:/productos/error"; // O un mensaje de error
        }
    }

}
