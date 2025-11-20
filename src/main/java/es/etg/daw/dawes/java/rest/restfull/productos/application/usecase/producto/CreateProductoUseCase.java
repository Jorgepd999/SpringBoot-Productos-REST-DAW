package es.etg.daw.dawes.java.rest.restfull.productos.application.usecase.producto;

import java.time.LocalDateTime;

import es.etg.daw.dawes.java.rest.restfull.productos.application.command.producto.CreateProductoCommand;
import es.etg.daw.dawes.java.rest.restfull.productos.domain.model.Producto;
import es.etg.daw.dawes.java.rest.restfull.productos.domain.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateProductoUseCase {

	private final ProductoRepository productoRepository;

	

	public Producto create(CreateProductoCommand comando) {

		// Se puede usar comando.id() y no getId() por usar @Accessors(fluent = true)
		// en la clase CreateProductoCommand

		Producto producto = Producto.builder()
				.nombre(comando.nombre())
				.precio(comando.precio())
				.categoria(comando.categoriaId())
				.createdAt(LocalDateTime.now()).build();
		
		return productoRepository.save(producto);

	}
}