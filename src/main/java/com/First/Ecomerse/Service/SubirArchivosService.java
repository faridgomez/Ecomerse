package com.First.Ecomerse.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

@Service
public class SuvirArchivosService {
    private final Path folder = Paths.get("C:\\Proyectos Java\\Ecomerse\\images"); // Usar Path para rutas
    private final Path defaultImage = Paths.get("C:\\Proyectos Java\\Ecomerse\\images", "principal.jpg");


    public String saveImage(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return defaultImage.getFileName().toString(); // Retornar el nombre por defecto si no hay archivo
        }

        // Generar ruta de destino
        Path filePath = folder.resolve(file.getOriginalFilename());

        // Escribir el archivo
        try {
            Files.write(filePath, file.getBytes());
            return file.getOriginalFilename(); // Retornar el nombre del archivo guardado
        } catch (IOException e) {
            // Manejo de excepción: si ocurre un error, lanzar o manejar según sea necesario
            throw new IOException("Error al guardar el archivo", e);
        }
    }

    public void deleteImage(String nombre) {
        // Eliminar imagen si existe
        Path filePath = folder.resolve(nombre);
        File file = filePath.toFile();
        if (file.exists()) {
            boolean deleted = file.delete();
            if (!deleted) {
                System.out.println("No se pudo eliminar el archivo: " + nombre);
            }
        }
    }
}