package es.etg.daw.dawes.java.rest.restfull.productos.application.command.producto;

import es.etg.daw.dawes.java.rest.restfull.productos.domain.model.CategoriaId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@AllArgsConstructor
@Accessors(fluent= true)

public class CreateProductoCommand {
    
    private final String nombre;
    private final double precio;
    private final CategoriaId categoriaId;
}
