package es.etg.daw.dawes.java.rest.restfull.productos.application.command.categoria;

import es.etg.daw.dawes.java.rest.restfull.productos.domain.model.CategoriaId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@AllArgsConstructor
@Accessors(fluent = true)
public class EditCategoriaCommand {
    
    private final CategoriaId id;
    private final String nombre;

}


