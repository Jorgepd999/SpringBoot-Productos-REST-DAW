package es.etg.daw.dawes.java.rest.restfull.productos.domain.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder


public class Producto {
    
    //Atributtes

    private ProductoId id;
    private String nombre;
    private double precio;
    private LocalDateTime createdAt;


    //Agregamos la categoría
    private CategoriaId categoria;

    
    
}
