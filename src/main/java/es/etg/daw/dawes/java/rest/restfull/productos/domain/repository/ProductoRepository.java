package es.etg.daw.dawes.java.rest.restfull.productos.domain.repository;

import java.util.Optional;

import es.etg.daw.dawes.java.rest.restfull.common.domain.repository.CRUDRepository;
import es.etg.daw.dawes.java.rest.restfull.productos.domain.model.Producto;
import es.etg.daw.dawes.java.rest.restfull.productos.domain.model.ProductoId;

public interface ProductoRepository extends CRUDRepository<Producto, ProductoId> {
    
    Optional<Producto> getByName(String nombre);
    
}
