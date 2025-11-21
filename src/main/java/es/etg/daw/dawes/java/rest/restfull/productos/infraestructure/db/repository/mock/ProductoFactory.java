package es.etg.daw.dawes.java.rest.restfull.productos.infraestructure.db.repository.mock;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import es.etg.daw.dawes.java.rest.restfull.productos.domain.model.CategoriaId;
import es.etg.daw.dawes.java.rest.restfull.productos.domain.model.Producto;
import es.etg.daw.dawes.java.rest.restfull.productos.domain.model.ProductoId;

public class ProductoFactory {

    public static final Map<ProductoId, Producto> getDemoData() {
        Map<ProductoId, Producto> datos = new LinkedHashMap<>();

        datos.put(new ProductoId(1),
                new Producto(new ProductoId(1), "Nombre producto 1", 1.01, LocalDateTime.now(), new CategoriaId(1)));
        datos.put(new ProductoId(2),
                new Producto(new ProductoId(2), "Nombre producto 2", 1.02, LocalDateTime.now(), new CategoriaId(1)));
        datos.put(new ProductoId(3),
                new Producto(new ProductoId(3), "Nombre producto 3", 1.03, LocalDateTime.now(), new CategoriaId(2)));
        datos.put(new ProductoId(4),
                new Producto(new ProductoId(4), "Nombre producto 4", 1.04, LocalDateTime.now(), new CategoriaId(2)));

        return datos;
    }

    public static Producto create() {
        return new Producto(new ProductoId(5), "Nuevo", 9.99, LocalDateTime.now(), new CategoriaId(1));
    }

}