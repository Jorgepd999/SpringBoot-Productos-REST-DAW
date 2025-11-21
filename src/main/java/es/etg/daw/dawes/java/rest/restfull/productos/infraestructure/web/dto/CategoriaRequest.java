package es.etg.daw.dawes.java.rest.restfull.productos.infraestructure.web.dto;

import es.etg.daw.dawes.java.rest.restfull.productos.domain.model.Categoria;
import jakarta.validation.constraints.NotBlank;

public record CategoriaRequest(
        @NotBlank(message = "{categoria.valid.nombre.no_vacio}") String nombre) {

     public CategoriaRequest(Categoria c) {
        this(c.getNombre());
    }
}
