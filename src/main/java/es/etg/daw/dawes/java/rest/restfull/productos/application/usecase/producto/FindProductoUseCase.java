package es.etg.daw.dawes.java.rest.restfull.productos.application.usecase.producto;

import java.util.List;

import es.etg.daw.dawes.java.rest.restfull.productos.domain.error.ProductoNotFoundException;
import es.etg.daw.dawes.java.rest.restfull.productos.domain.model.Producto;
import es.etg.daw.dawes.java.rest.restfull.productos.domain.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FindProductoUseCase {

    private final ProductoRepository productoRepository;
    public List<Producto> findAll(){
        List<Producto> productos = productoRepository.getAll();

        if (productos.isEmpty())
            throw new ProductoNotFoundException();

        return productos;
    }
    
}