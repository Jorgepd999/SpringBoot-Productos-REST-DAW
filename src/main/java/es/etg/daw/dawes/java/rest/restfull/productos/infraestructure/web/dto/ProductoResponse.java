package es.etg.daw.dawes.java.rest.restfull.productos.infraestructure.web.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;

public record ProductoResponse(
    
    @Schema(description = "ID único del producto", example = "23")
    int id, 
    
    @Schema(description = "Nombre comercial del producto", example = "Monitor Gamer")
    String nombre, 
    
    @Schema(description = "Precio unitario en euros", example = "23")
    double precio, 
    
    @Schema(description = "Fecha y hora de creación del registro", example = "2024-05-20T14:30:00")
    LocalDateTime createdAt, 
    
    @Schema(description = "ID de la categoría asociada", example = "1")
    int categoria
) {
}