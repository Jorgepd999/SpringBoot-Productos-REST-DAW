package es.etg.daw.dawes.java.rest.restfull.productos.infraestructure.web.dto;

import java.time.LocalDateTime;

public record ProductoResponse(int id, String nombre, double precio, LocalDateTime createdAt, int categoria) {
    
}