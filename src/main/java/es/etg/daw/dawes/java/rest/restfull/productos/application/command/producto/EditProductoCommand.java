package es.etg.daw.dawes.java.rest.restfull.productos.application.command.producto;

import es.etg.daw.dawes.java.rest.restfull.productos.domain.model.CategoriaId;
import es.etg.daw.dawes.java.rest.restfull.productos.domain.model.ProductoId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

//Esta clase tiene los datos necesarios para editar un Producto
@Getter
@AllArgsConstructor
@Accessors(fluent = true) // Así los getters no llevan prefijo get
public class EditProductoCommand {
    
    private final ProductoId id; //Lo cambiamos
    private final String nombre;
    private final double precio;
    private final CategoriaId categoriaId;
}