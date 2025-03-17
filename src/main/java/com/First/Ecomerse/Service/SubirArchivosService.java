package com.First.Ecomerse.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Service
public class SubirArchivosService {
    private final Path carpeta = Paths.get("images"); // Usar Path para rutas
    private final Path imagenpredetermina = Paths.get("images", "principal.jpg");

    // Extensiones de archivo permitidas (en minúsculas)
    private final String[] formatosPermitidos = { ".jpg", ".jpeg", ".png", ".gif", ".bmp" };

    public String saveImage(MultipartFile file) throws IOException {

        if (file.isEmpty()) {
            return imagenpredetermina.getFileName().toString(); // Retornar el nombre por defecto si no hay archivo
        }

        // Validar si el archivo tiene un formato de imagen permitido
        String NomImgOrigin = file.getOriginalFilename();
        if (!esFormatoValido(NomImgOrigin)) {
            throw new IllegalArgumentException("El archivo no es un formato de imagen válido");
        }
        String tipoImagen = "";
        // Obtener la extensión del archivo
        if (NomImgOrigin != null && NomImgOrigin.contains(".")) {
            tipoImagen = NomImgOrigin.substring(NomImgOrigin.lastIndexOf("."));
        } else {
            // Manejar el caso en que no haya un punto o sea nulo
            tipoImagen = "";
        }

        // Generar un nombre único para la imagen
        String NombreUnico = UUID.randomUUID().toString(); // genera un nombre único y aleatorio
        int LargoMax = 8; // Longitud máxima del nombre único

        if (NombreUnico.length() > LargoMax) {
            // Recortamos el UUID a la longitud deseada
            NombreUnico = NombreUnico.substring(0, LargoMax);
        }

        // Crear el nuevo nombre para el archivo
        String NuevoNombreImg = NombreUnico + tipoImagen;

        // Guardar el archivo en la carpeta
        Path filePath = carpeta.resolve(NuevoNombreImg);

        // Escribir el archivo
        try {
            Files.write(filePath, file.getBytes());

            return NuevoNombreImg; // Retornar el nombre del archivo guardado
        } catch (IOException e) {
            // Manejo de excepción: si ocurre un error, lanzar o manejar según sea necesario
            throw new IOException("Error al guardar el archivo", e);
        }
    }

    // Método para verificar si el archivo tiene un formato válido
    private boolean esFormatoValido(String fileName) {
        // Recorremos todos los formatos permitidos y verificamos si el nombre del
        // archivo contiene alguna de las extensiones
        for (String formato : formatosPermitidos) {
            if (fileName.toLowerCase().endsWith(formato)) {
                return true; // Si el archivo tiene una extensión válida, lo aceptamos
            }
        }
        return false; // Si no tiene una extensión válida, lo rechazamos
    }

    public void deleteImage(String nombre) {
        // Eliminar imagen si existe

        Path filePath = carpeta.resolve(nombre);
        File file = filePath.toFile();

        if (nombre != "principal.jpg") {
            if (file.exists()) {
                boolean deleted = file.delete();
                if (!deleted) {
                    System.out.println("No se pudo eliminar el archivo: " + nombre);
                }
            }
            
        }
        
    }
}