package es.etg.daw.dawes.java.rest.restfull.productos.application.service.producto;

import org.springframework.stereotype.Service;

import es.etg.daw.dawes.java.rest.restfull.productos.application.usecase.producto.DeleteProductoUseCase;
import es.etg.daw.dawes.java.rest.restfull.productos.domain.model.ProductoId;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DeleteProductoService{

    private final DeleteProductoUseCase deleteProductoUseCase;

    public void delete(ProductoId id){
        deleteProductoUseCase.delete(id);
    }

}


